import {
    Inter_400Regular,
    Inter_600SemiBold,
    Inter_700Bold,
    Inter_800ExtraBold,
    useFonts
} from '@expo-google-fonts/inter';
import {StatusBar} from 'react-native';
import * as Notifications from "expo-notifications"

Notifications.setNotificationHandler({
    handleNotification: async () => ({
        shouldShowAlert: true,
        shouldPlaySound: false,
        shouldSetBadge: false
    })
})

import {Loading} from './src/components/Loading';
import "./src/lib/dayjs"
import {Routes} from "./src/routes";

export default function App() {
    // Precisamos garantir que as fontes sejam carregadas antes do app ser exibido
    const [fontsLoader] = useFonts({
        Inter_400Regular,
        Inter_600SemiBold,
        Inter_700Bold,
        Inter_800ExtraBold
    })

    // Notifications
    async function scheduleNotification(){
        const trigger = new Date(Date.now())
        trigger.setMinutes(trigger.getMinutes() + 1)

        await Notifications.scheduleNotificationAsync({
            content: {
                title: "Olá, Vinicius!",
                body: "Você praticou seus hábitos hoje ?"
            },
            trigger
        })
    }

    // se as fontes nao estiverem carregadas, impede que siga o fluxo da aplicação
    if (!fontsLoader) {
        return <Loading/>
    }

    // renderiza na tela
    return (
        <>
            <Routes/>
            <StatusBar barStyle="light-content" backgroundColor="transparent" translucent/>
        </>
    )
}

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     backgroundColor: "#09090A",
//     alignItems: "center",
//     justifyContent: "center"
//   },
//   text: {
//     color: "white",
//     fontFamily: "Inter_800ExtraBold"
//   }
// })
