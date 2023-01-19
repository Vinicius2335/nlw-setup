import { View } from "react-native"
import  { NavigationContainer } from "@react-navigation/native"
import { AppRoutes } from "./app.routes";

export function Routes() {
    return (
        // bg-background -> por causa da transiçao de uma tela pra outra, nao aparecer um fundo branco
        <View className={"flex-1 bg-background"}>
            <NavigationContainer>
                <AppRoutes />
            </NavigationContainer>
        </View>
    )
}