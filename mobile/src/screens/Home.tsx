import { useNavigation, useFocusEffect } from "@react-navigation/native"
import dayjs from "dayjs"
import { useCallback, useState } from "react"
import { Alert, ScrollView, Text, View } from "react-native"

import { DAY_SIZE, HabitDay } from "../components/HabitDay"
import { Header } from "../components/Header"
import { Loading } from "../components/Loading"
import { api } from "../lib/axios"
import { Summary } from "../models/Summary"
import { generateDatesFromYearBeginning } from "../utils/generate-dates-from-year-beginning"

const weekDays = ["D", "S", "T", "Q", "Q", "S", "S"]
const datesFromYearStart = generateDatesFromYearBeginning()
const minimumSummaryDatesSizes = 18 * 5
const amountOfDaysToFill = minimumSummaryDatesSizes - datesFromYearStart.length

export function Home() {
  const { navigate } = useNavigation()
  const [loading, setLoading] = useState(true)
  const [summary, setSummary] = useState<Summary[] | null>(null)

  async function fetchData() {
    try {
      setLoading(true)
      const response = await api.get("/summary")
      console.log(response.data)
      setSummary(response.data)
    } catch (error) {
      Alert.alert("Ops", "Não foi possivel carregar o sumário de hábitos")
      console.error(error)
    } finally {
      setLoading(false)
    }
  }

  // useFocusEffect -> quando o foco voltar para essa tela, ela recarrega dnv
  // por recomendaçao da documentação precisamos do useCallback
  useFocusEffect(
    useCallback(() => {
      fetchData()
    }, [])
  )

  if (loading) {
    return <Loading />
  }

  return (
    <View className="flex-1 bg-background px-8 py-16">
      <Header />

      <View className="flex-row mt-6 mg-2">
        {weekDays.map((weekDay, i) => (
          <Text
            key={`${weekDay}-${i}`}
            className="text-zinc-400 text-xl font-bold text-center mx-1"
            style={{ width: DAY_SIZE }}
          >
            {weekDay}
          </Text>
        ))}
      </View>

      <ScrollView
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={{ paddingBottom: 100 }}
      >
        {summary && (
          <View className="flex-row flex-wrap">
            {datesFromYearStart.map(date => {
              const dayWithHabits = summary.find(day => {
                return dayjs(date).isSame(day.date, "day")
              })

              return (
                <HabitDay
                  key={date.toISOString()}
                  date={date}
                  amountCompleted={dayWithHabits?.completed}
                  amountOfHabits={dayWithHabits?.amount}
                  onPress={() => navigate("habit", { date: date.toISOString() })}
                />
              )
            })}

            {amountOfDaysToFill > 0 &&
              Array.from({ length: amountOfDaysToFill }).map((_, i) => (
                <View
                  key={i}
                  className="bg-zinc-900 rounded-lg border-2 m-1 border-zinc-800 opacity-40"
                  style={{ width: DAY_SIZE, height: DAY_SIZE }}
                />
              ))}
          </View>
        )}
      </ScrollView>
    </View>
  )
}
