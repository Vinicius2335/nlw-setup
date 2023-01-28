import { Header } from "./components/layout/Header"
import { SummaryTable } from "./components/SummaryTable"
import { api } from "./lib/axios"
import "./lib/dayjs"

// window.Notification.requestPermission(permission => {
//   if (permission === "granted"){
//     new window.Notification("Habits", {
//       body: "Texto"
//     })
//   }
// })

navigator.serviceWorker.register("service-worker.js")
  .then(async serviceWorker => {
    // assinatura do usuario com o serviço de notificaçao
    let subscription = await serviceWorker.pushManager.getSubscription()

    if (!subscription){
      const publickKeyResponse = await api.get("/push/public_key")

      subscription = await serviceWorker.pushManager.subscribe({
        userVisibleOnly: true,
        applicationServerKey: publickKeyResponse.data.publicKey
      })
    }

    // console.log(subscription);
    await api.post("/push/register", {
      subscription
    })

    await api.post("/push/send", {
      subscription
    })
    
  })

export function App() {

  return (
    <div className="w-screen h-screen flex justify-center items-center">
      <div className="w-full max-w-5xl px-6 flex flex-col gap-16">
        <Header />
        <SummaryTable />
      </div>
    </div>
  )
}
