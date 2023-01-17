// Back-end API RESTFUL
import fastify from "fastify";
import { PrismaClient } from "@prisma/client"
import cors from "@fastify/cors"

const app = fastify()
const prisma = new PrismaClient()

// somente localhost:3000 poderá consumir os dados da api
/*
app.register(cors, {
  origin: "http://localhost:3000"
})
*/
// qualquer aplicaçao consuma os dados do backend
app.register(cors)

// findMany() retorna varios elementos do banco de dados
app.get("/", async () => {
  const habits = await prisma.habit.findMany({})

  return habits
})

app.listen({
  port: 3333,
}).then(() => console.log("Servidor na porta 3333"))


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