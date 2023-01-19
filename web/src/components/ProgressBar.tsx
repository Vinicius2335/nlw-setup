import * as Progress from "@radix-ui/react-progress";

interface ProgressBarProps {
    progress: number
}

export function ProgressBar({ progress }: ProgressBarProps) {
    const progressStyles = {
        width: `${progress}%`
    }

    return (
        <Progress.Root value={progress} className={"h-3 rounded-xl bg-zinc-700 w-full mt-4"}>
            <Progress.Indicator aria-label={"Progresso de Hábitos completados nesse dia"}
                                className={"h-3 rounded-xl bg-violet-600"} style={progressStyles}/>
        </Progress.Root>
    );
}

/*  Antiga progress bar
                    <div className={"h-3 rounded-xl bg-zinc-700 w-full mt-4"}>
                        <div role={"progressbar"} aria-label={"Progresso de Hábitos completados nesse dia"}
                             aria-valuenow={75}
                             className={"h-3 rounded-xl bg-violet-600 w-3/4"}/>
                    </div>
 */