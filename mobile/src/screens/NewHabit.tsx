import { View, ScrollView, Text, TextInput, TouchableOpacity, Alert } from "react-native"
import { BackButton } from "../components/BackButton"
import { Checkbox } from "../components/Checkbox"
import { useState } from "react"
import { Feather } from "@expo/vector-icons"
import colors from "tailwindcss/colors"
import { api } from "../lib/axios"

const avaliableWeekDay = [
  "Domingo",
  "Segunda-Feira",
  "Terça-Feira",
  "Quarta-Feira",
  "Quinta-Feira",
  "Sexta-Feira",
  "Sabado"
]

export function NewHabit() {
  const [weekDays, setWeekDays] = useState<number[]>([])
  const [title, setTitle] = useState("")

  function handleToggleWeekDay(weekDayIndex: number) {
    if (weekDays.includes(weekDayIndex)) {
      setWeekDays(prevState => prevState.filter(weekDay => weekDay != weekDayIndex))
    } else {
      setWeekDays(prevState => [...prevState, weekDayIndex])
    }
  }

  async function handleCreateNewHabit() {
    try {
      if (!title.trim() || weekDays.length === 0) {
        Alert.alert("Novo Hábito", "Informe o nome do hábito e escolha a periodicidade")
      }

      await api.post("/habits", {
        title,
        weekDays
      })
      .then(() => Alert.alert("Sucesso", "Hábito criado com sucesso!"))
      .catch(() => Alert.alert("Ops", "Erro, tente novamente!"))

      setTitle("")
      setWeekDays([])

    } catch (error) {
        console.error(error);
        Alert.alert("Ops", "Não foi possivel criar o novo hábito")
    }
  }

  return (
    <View className={"flex-1 bg-background px-8 pt-16"}>
      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={{ paddingBottom: 100 }}
      >
        <BackButton />

        <Text className={"mt-6 text-white font-extrabold text-3xl"}>Criar Hábito</Text>

        <Text className={"mt-6 text-white font-semibold text-base"}>Qual seu comprometimento?</Text>

        <TextInput
          className={
            "h-12 pl-4 rounded-lg mt-3 bg-zinc-900 text-white border-2 border-zinc-800 focus:border-green-600"
          }
          placeholder={"ex.: exercicios, dormir, etc..."}
          placeholderTextColor={colors.zinc[400]}
          onChangeText={setTitle}
          value={title}
        />

        <Text className={"font-semibold mt-4 mb-3 text-white text-base"}>Qual a recorrência?</Text>

        {avaliableWeekDay.map((weekDay, i) => (
          <Checkbox
            key={weekDay}
            title={weekDay}
            checked={weekDays.includes(i)}
            onPress={() => handleToggleWeekDay(i)}
          />
        ))}

        <TouchableOpacity
          className={
            "w-full h-14 flex-row items-center justify-center bg-green-600 rounded-md mt-6"
          }
          activeOpacity={0.7}
          onPress={handleCreateNewHabit}
        >
          <Feather name={"check"} size={20} color={colors.white} />
          <Text className={"font-semibold text-base text-white ml-2"}>Confirmar</Text>
        </TouchableOpacity>
      </ScrollView>
    </View>
  )
}