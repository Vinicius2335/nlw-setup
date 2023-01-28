import {generateDatesFromYearBeginning} from "../utils/generate-dates-from-year-beginning"
import {HabitDay} from "./habit/HabitDay"
import {useEffect, useState} from "react";
import {api} from "../lib/axios";
import {Summary} from "../models/Summary";
import dayjs from "dayjs";

const weekDays = ["D", "S", "T", "Q", "Q", "S", "S"]

const summaryDates = generateDatesFromYearBeginning()
const minimumSummaryDatesSize = 18 * 7
const amountOfDaysToFill = minimumSummaryDatesSize - summaryDates.length

export function SummaryTable() {
    const [summary, setSummary] = useState<Summary[]>([]);

    useEffect(() => {
        api.get("/summary")
            .then(response => setSummary(response.data))

    }, []);


    return (
        <div className="w-full flex">
            <div className="grid grid-rows-7 grid-flow-row gap-3">
                {weekDays.map((weekDay, i) => (
                    <div key={`${weekDay}-${i}`}
                         className="text-zinc-400 text-xl font-bold h-10 w-10 flex items-center justify-center">
                        {weekDay}
                    </div>
                ))}
            </div>

            <div className="grid grid-rows-7 grid-flow-col gap-3 transi">
                {summary.length> 0 && summaryDates.map(date => {
                    const dayInSummary = summary.find(day => {
                        return dayjs(date).isSame(day.date, "day")
                    })

                    return (
                        <HabitDay key={date.toDateString()}
                                  date={date}
                                  amount={dayInSummary?.amount}
                                  defaultCompleted={dayInSummary?.completed}/>
                    )
                })}

                {amountOfDaysToFill > 0 && Array.from({length: amountOfDaysToFill}).map((_, i) => (
                    <div key={i}
                         className="h-10 w-10 bg-zinc-900 border-2 border-zinc-800 rounded-lg opacity-40 cursor-not-allowed">
                    </div>
                ))}
            </div>
        </div>
    )
}