import { prisma } from "./lib/prisma"
import { FastifyInstance } from "fastify"
import { z } from "zod"
import dayjs from "dayjs"


export async function appRoutes(app: FastifyInstance){
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

    const { title, weekDays } = createHabitBody.parse(request.body)
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
      date: z.coerce.date() // como vamos receber a data como uma string o zod possui uma funçao =- coerce  que converte essa string em date
    })

    const { date } = getDayParams.parse(request.query)
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
    const completedHabits = day?.dayHabits.map(dayHabit => dayHabit.habit_id) 

    return {
      possigleHabits,
      completedHabits
    }
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