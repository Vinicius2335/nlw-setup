// Back-end API RESTFUL
import fastify from "fastify";
import cors from "@fastify/cors"
import { appRoutes } from "./routes";

const app = fastify()

// somente localhost:3000 poderá consumir os dados da api
/*
app.register(cors, {
  origin: "http://localhost:3000"
})
*/
// qualquer aplicaçao consuma os dados do backend
app.register(cors)
app.register(appRoutes)

app.listen({
  port: 3333,
}).then(() => console.log("Servidor na porta 3333"))


