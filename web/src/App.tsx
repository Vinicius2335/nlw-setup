import { Habits } from "./components/Habit"
import "./styles/global.css"

function App() {
  return (
    <div>
      <h1>Olá Mundo</h1>
      <Habits completed={3}/>
      <Habits completed={6}/>
      <Habits completed={9}/>
      <Habits completed={12}/>
    </div>
  )
}

export default App
