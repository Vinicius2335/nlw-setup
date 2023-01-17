interface HabitProps {
  completed: number
}

export function Habits({ completed }: HabitProps) {
  return (
    <div className="bg-zinc-900 w-10 h-10 text-white rounded m-2 text-center flex items-center justify-center">
      <p>{completed}</p>
    </div>
  )
}

/*
<div className="bg-zinc-900 w-10 h-10 text-white rounded m-2 text-center flex items-center justify-center">
      <p>{completed}</p>
    </div>
*/