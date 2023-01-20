import { Feather } from "@expo/vector-icons"
import { TouchableOpacity, View, Text } from "react-native"
import colors from "tailwindcss/colors"

import Logo from "../assets/logo.svg"
import { useNavigation } from "@react-navigation/native"

export function Header() {
    const { navigate } = useNavigation()

  return (
    <View className="w-full flex-row items-center justify-between">
      <Logo />

      <TouchableOpacity
        activeOpacity={0.7}
        className="flex-row h-11 px-4 border border-violet-500 rounded-lg items-center"
        onPress={() => navigate("newHabit")}
      >
        <Feather name="plus" color={colors.violet[500]} size={20} />
      </TouchableOpacity>
    </View>
  )
}
