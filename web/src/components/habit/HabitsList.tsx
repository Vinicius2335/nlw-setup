import * as Checkbox from "@radix-ui/react-checkbox"
import dayjs from "dayjs"
import { Check } from "phosphor-react"
import { useEffect, useState } from "react"
import { api } from "../../lib/axios"
import { Habit } from "../../models/Habit"

interface HabitsListProps {
  date: Date
  onCompletedChange: (completed: number) => void
}

interface HabitsInfo {
  possigleHabits: Habit[]
  completedHabits: string[]
}

export function HabitsList({ date, onCompletedChange }: HabitsListProps) {
  const [habitsInfo, setHabitsInfo] = useState<HabitsInfo>()
  const isDateInPast = dayjs(date).endOf("day").isBefore(new Date())

  useEffect(() => {
    api
      .get("day", {
        params: {
          date: date.toISOString()
        }
      })
      .then(response => setHabitsInfo(response.data))
  }, [])
  

  async function handleToggleHabit(habitId: string){
    await api.patch(`/habits/${habitId}/toggle`)
    const isHabitAlreadyCompleted = habitsInfo!.completedHabits.includes(habitId)
    let completedHabits: string[] = []

    if (isHabitAlreadyCompleted){
      // remover da lista
      completedHabits = habitsInfo!.completedHabits.filter(id => id !== habitId)
    } else {
      // adicionar na lista
      completedHabits = [...habitsInfo!.completedHabits, habitId]
    }

    setHabitsInfo({
      possigleHabits: habitsInfo!.possigleHabits,
      completedHabits: completedHabits
    })

    onCompletedChange(completedHabits.length)
  }

  return (
    <div className={"mt-6 flex flex-col gap-3"}>
      {habitsInfo?.possigleHabits.map(habit => {
        return (
          <Checkbox.Root className={"flex items-center gap-3 group focus:outline-none"} 
          key={habit.id} 
          checked={habitsInfo.completedHabits.includes(habit.id)}
          onCheckedChange={() => handleToggleHabit(habit.id)}
          disabled={isDateInPast}
          >
            <div
              className={
                "h-8 w-8 rounded-lg flex items-center justify-center bg-zinc-900 border-2 border-zinc-800 group-data-[state=checked]:bg-green-500 group-data-[state=checked]:border-green-500 transition-colors group-focus:ring-2 group-focus:ring-violet-600 group-focus:ring-offset-2 group-focus:ring-offset-background"
              }
            >
              <Checkbox.Indicator>
                <Check size={20} className={"text-white"} />
              </Checkbox.Indicator>
            </div>

            <span
              className={
                "font-semibold text-xl text-white leading-tight group-data-[state=checked]:line-through group-data-[state=checked]:text-zinc-400"
              }
            >
             {habit.title} 
            </span>
          </Checkbox.Root>
        )
      })}
    </div>
  )
}
