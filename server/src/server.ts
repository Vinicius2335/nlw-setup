// Back-end API RESTFUL
import fastify from "fastify";
import cors from "@fastify/cors"
import { appRoutes } from "./routes";
import { notificationRoutes } from "./notification-routes";

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
app.register(notificationRoutes)

// host: "0.0.0.0" -> pra não dar problemo na conexao mobile
app.listen({
  port: 3333,
  host: "0.0.0.0"
}).then(() => console.log("Servidor na porta 3333"))


