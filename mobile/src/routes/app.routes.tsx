import { createNativeStackNavigator } from "@react-navigation/native-stack"
import { Home } from "../screens/Home"
import { NewHabit } from "../screens/NewHabit"
import { Habit } from "../screens/Habit"

// Navigator -> criar o escopo das nossas rotas
// Screen -> definir para onde ir
const { Navigator, Screen } = createNativeStackNavigator()

export function AppRoutes() {
  return (
    // por padrao aparece o header, e essa config retira o header de todos os componentes
    <Navigator screenOptions={{ headerShown: false }}>
      <Screen name={"home"} component={Home} />
      <Screen name={"newHabit"} component={NewHabit} />
      <Screen name={"habit"} component={Habit} />
    </Navigator>
  )
}
