// estamos declarando quais parametros queremos passar de uma tela pra outra
// undefined quando nao passamos nada
// não é recomendado passar muitas informaçoes, objetos complexos, somente dados simples

export declare global {
    namespace ReactNavigation {
        interface RootParamList {
            home: undefined,
            newHabit: undefined,
            habit: {
                date: string
            }
        }
    }
}