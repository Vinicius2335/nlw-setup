
-- Table Habits
CREATE TABLE IF NOT EXISTS habits (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL
)engine=InnoDB default charset=utf8;

-- Table HabitWeekDays
CREATE TABLE IF NOT EXISTS habit_week_days (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    week_Day INTEGER NOT NULL,
    habit_id VARCHAR(36) NOT NULL
)engine=InnoDB default charset=utf8;

-- Constraint HabitWeekDays -> Habits
ALTER TABLE habit_week_days ADD CONSTRAINT fk_habit_week_days_habits
FOREIGN KEY (habit_id) REFERENCES habits (id);

-- Table Days
CREATE TABLE IF NOT EXISTS days (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    date DATETIME NOT NULL
)engine=InnoDB default charset=utf8;

-- Table DayHabits
CREATE TABLE IF NOT EXISTS day_habits(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    habit_id VARCHAR(36) NOT NULL,
    day_id VARCHAR(36) NOT NULL
)engine=InnoDB default charset=utf8;

-- Constraint DayHabit -> Habit
ALTER TABLE day_habits ADD CONSTRAINT fk_day_habit_habit
FOREIGN KEY (habit_id) REFERENCES habits (id);

-- Constraint DayHabit -> Day
ALTER TABLE day_habits ADD CONSTRAINT fk_day_habit_day
FOREIGN KEY (day_id) REFERENCES days (id);