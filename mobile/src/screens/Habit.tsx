import { useRoute } from "@react-navigation/native"
import clsx from "clsx"
import dayjs from "dayjs"
import { useEffect, useState } from "react"
import { Alert, ScrollView, Text, View } from "react-native"

import { BackButton } from "../components/BackButton"
import { Checkbox } from "../components/Checkbox"
import { HabitsEmpty } from "../components/HabitsEmpty"
import { Loading } from "../components/Loading"
import { ProgressBar } from "../components/ProgressBar"
import { api } from "../lib/axios"
import { generatedProgressPercentage } from "../utils/generated-progress-percentage"
import { HabitModel } from "../models/HabitModel"

interface HabitParams {
  date: string
}

interface DayInfoProps {
  completedHabits: string[]
  possigleHabits: HabitModel[]
}

export function Habit() {
  const route = useRoute()
  const { date } = route.params as HabitParams
  const [isLoading, setIsLoading] = useState(true)
  const [dayInfo, setDayInfo] = useState<DayInfoProps>({} as DayInfoProps)
  const [completedHabits, setCompletedHabits] = useState<string[]>([])

  const parseDate = dayjs(date)
  const dayOfWeek = parseDate.format("dddd")
  const dayAndMonth = parseDate.format("DD/MM")
  const isDateInPast = parseDate.endOf("day").isBefore(new Date())

  const habitsProgress = dayInfo?.possigleHabits?.length ?
   generatedProgressPercentage(dayInfo.possigleHabits.length, completedHabits.length) : 0

  console.log(habitsProgress)

  async function fetchHabits() {
    try {
      setIsLoading(true)

      const response = await api.get<DayInfoProps>("/day", {
        params: {
          date: date
        }
      })
      setDayInfo(response.data)
      setCompletedHabits(response.data.completedHabits)
    } catch (error) {
      console.error(error)
      Alert.alert("Ops", "Não foi possivel carregar as informações do hábito")
    } finally {
      setIsLoading(false)
    }
  }

  async function handleToggleHabit(habitId: string) {
    try {
      await api.patch(`/habits/${habitId}/toggle`)

      if (completedHabits.includes(habitId)) {
        setCompletedHabits(prevState => prevState.filter(habit => habit !== habitId))
      } else {
        setCompletedHabits(prevStage => [...prevStage, habitId])
      }
    } catch (error) {
      console.error(error)
      Alert.alert("Ops", "Não foi possivel atualizar o status do hábito.")
    }
  }

  useEffect(() => {
    fetchHabits()
  }, [])

  if (isLoading) {
    return <Loading />
  }

  return (
    <View className={"flex-1 bg-background px-8 pt-16"}>
      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={{ paddingBottom: 100 }}
      >
        <BackButton />

        <Text className={"mt-6 text-zinc-400 font-semibold text-base lowercase"}>{dayOfWeek}</Text>

        <Text className={"text-white font-extrabold text-3xl"}>{dayAndMonth}</Text>

        <ProgressBar progress={habitsProgress} />

        {/* TODO: Fazer na Web */}
        <View
          className={clsx("mt-6", {
            ["opacity-50"]: isDateInPast
          })}
        >
          {dayInfo.possigleHabits ? (
            dayInfo.possigleHabits.map(habit => (
              <Checkbox
                key={habit.id}
                title={habit.title}
                checked={completedHabits?.includes(habit.id)}
                disabled={isDateInPast}
                onPress={() => handleToggleHabit(habit.id)}
              />
            ))
          ) : (
            // TODO: Fazer isso na Web tbm
            <HabitsEmpty />
          )}
        </View>

        {
          // TODO: Colocar isso na Web tbm
          isDateInPast && (
            <Text className="text-white mt-10 text-center">
              Você não pode editar hábitos de uma data passada.
            </Text>
          )
        }
      </ScrollView>
    </View>
  )
}
