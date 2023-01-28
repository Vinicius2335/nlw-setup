import { FastifyInstance } from "fastify"
import WebPush from "web-push"
import { z } from "zod"

// console.log(WebPush.generateVAPIDKeys());
// para pegar a publicKey + privateKey

const publicKey =
  "BI1u48L8rBbhHtwRb41Zk40xjHXYkwwNX2HLvUBo0FOFEF9nrp4sOoffj5U-_BqkKesxqvzPOOmCFGn6gWGAGG0"
const privateKey = "cW822REYrOmpVnyvcnJmyzU_MZiEv0UFPOsuYHAdrSc";

WebPush.setVapidDetails("http://localhost:3333", publicKey, privateKey);

export async function notificationRoutes(app: FastifyInstance) {
  app.get("/push/public_key", () => {
    return {
      publicKey,
    }
  });

  app.post("/push/register", (request, reply) => {
    console.log(request.body)
    // caso tivesse um sistema de login aki entraria o prisma

    return reply.status(201).send();
  })

  app.post("/push/send", async (request, reply) => {
    console.log(request.body)
    const sendPushBody = z.object({
      subscription: z.object({
        endpoint: z.string(),
        keys: z.object({
          p256dh: z.string(),
          auth: z.string()
        })
      })
    });

    const { subscription } = sendPushBody.parse(request.body);

    WebPush.sendNotification(subscription, "Hello do Backend");

    return reply.status(201).send();
  })
}
