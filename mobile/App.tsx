import {
    Inter_400Regular,
    Inter_600SemiBold,
    Inter_700Bold,
    Inter_800ExtraBold,
    useFonts
} from '@expo-google-fonts/inter';
import {StatusBar} from 'react-native';

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
