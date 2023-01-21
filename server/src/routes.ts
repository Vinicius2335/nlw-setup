import {prisma} from "./lib/prisma"
import {FastifyInstance} from "fastify"
import {z} from "zod"
import dayjs from "dayjs"


export async function appRoutes(app: FastifyInstance) {
    /**
     * Cria um Habito
     */
    app.post("/habits", async (request) => {
        // request.params = "/habits/1" -> pegar parametros da url
        // request.body = "/habits" -> pegar os dados que está vindo no corpo da requisição
        // request.query = "/habits/?name=beber"

        const createHabitBody = z.object({
            // title: z.string().nullable() caso ela não seja obrigatorio
            title: z.string(),
            weekDays: z.array(z.number().min(0).max(6)) //[0, 1, 2, 3, 4, 5, 6]
        })

        const {title, weekDays} = createHabitBody.parse(request.body)
        const today = dayjs().startOf("day").toDate() // retorna uma data com horas:min:seg zerados

        await prisma.habit.create({
            data: {
                title,
                created_at: today,
                weekDays: {
                    create: weekDays.map((weekDay) => {
                        return {
                            week_day: weekDay,
                        }
                    })
                }
            }
        })
    })

    /**
     * Retorna o detalhe do dia
     * http://localhost:333/day?date=2023-01-31T00:00:00
     */
    app.get("/day", async (request) => {
        const getDayParams = z.object({
            // como vamos receber a data como uma string o zod possui uma funçao =- coerce  que converte essa string em date
            date: z.coerce.date()
        })

        const {date} = getDayParams.parse(request.query)
        const parsedDate = dayjs(date).startOf("day")
        const weekDay = parsedDate.get("day")

        // todos os habitos possiveis
        const possigleHabits = await prisma.habit.findMany({
            where: {
                created_at: {
                    lte: date, // se a data atual é <= que a data do dia
                },
                // every= todo os dado atende o requisito
                // none=nenhum dado atende o requisito
                // some= ou pelo menos 1 dado atende o requisito
                weekDays: {
                    some: {
                        week_day: weekDay,
                    }
                }
            }
        })

        // habitos que já foram completados
        const day = await prisma.day.findUnique({
            where: {
                date: parsedDate.toDate()
            },
            include: {
                dayHabits: true
            }
        })

        // pegando somente os ids dos habitos completados
        const completedHabits = day?.dayHabits.map(dayHabit => {
            return dayHabit.habit_id
        }) ?? []

        return {
            possigleHabits,
            completedHabits
        }
    })

    /**
     * Completar / não-completar um hábito
     */
    app.patch("/habits/:id/toggle", async (request) => {
        // route param => parâmetro de identificação
        const toggleHabitsParams = z.object({
            id: z.string().uuid()
        })

        const {id} = toggleHabitsParams.parse(request.params)
        const today = dayjs().startOf("day").toDate()

        let day = await prisma.day.findUnique({
            where: {
                date: today
            }
        })

        if (!day) {
            day = await prisma.day.create({
                data: {
                    date: today
                }
            })
        }

        const dayHabit = await prisma.dayHabit.findUnique({
            where: {
                day_id_habit_id: {
                    day_id: day.id,
                    habit_id: id
                }
            }
        })

        if (dayHabit) {
            // Remove a marcação de completo
            await prisma.dayHabit.delete({
                where: {
                    id: dayHabit.id
                }
            })
        } else {
            // completar o habito
            await prisma.dayHabit.create({
                data: {
                    day_id: day.id,
                    habit_id: id
                }
            })
        }

    })

    /**
     * Retorna um resumo
     */
    app.get("/summary", async () => {
        // deu bug no count pq o prisma nao serializa BigInt
        // por isso precisamos dar um cast no count

        const summary = await prisma.$queryRaw`
            SELECT 
                D.id,
                D.date,
                (
                    SELECT cast(count(*) as float) 
                    FROM day_habits DH
                    WHERE DH.day_id = D.id
                ) as completed,
                (
                    SELECT cast(count(*) as float)
                    FROM habit_week_days HWD
                    JOIN habits H
                        ON H.id = HWD.habit_id
                    WHERE
                        HWD.week_day = cast(strftime('%w', D.date/1000, 'unixepoch') as int)
                        AND H.created_at <= D.date
                ) as amount
            FROM days D
        `
        return summary
    })

}


// buscando na tabela todos os elementos que comecem com o titulo "beber"
/*
const habits = await prisma.habit.findMany({
    where: {
      title: {
        startsWith: "Beber"
      }
    }
  })
*/

// findMany() retorna varios elementos do banco de dados
/*
  app.get("/", async () => {
    const habits = await prisma.habit.findMany({})
  
    return habits
  })
*/