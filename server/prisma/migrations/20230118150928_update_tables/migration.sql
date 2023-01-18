/*
  Warnings:

  - You are about to drop the column `create_at` on the `habits` table. All the data in the column will be lost.
  - A unique constraint covering the columns `[habit_id,week_day]` on the table `habit_week_days` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `created_at` to the `habits` table without a default value. This is not possible if the table is not empty.

*/
-- DropIndex
DROP INDEX "habit_week_days_habit_id_key";

-- RedefineTables
PRAGMA foreign_keys=OFF;
CREATE TABLE "new_habits" (
    "id" TEXT NOT NULL PRIMARY KEY,
    "title" TEXT NOT NULL,
    "created_at" DATETIME NOT NULL
);
INSERT INTO "new_habits" ("id", "title") SELECT "id", "title" FROM "habits";
DROP TABLE "habits";
ALTER TABLE "new_habits" RENAME TO "habits";
PRAGMA foreign_key_check;
PRAGMA foreign_keys=ON;

-- CreateIndex
CREATE UNIQUE INDEX "habit_week_days_habit_id_week_day_key" ON "habit_week_days"("habit_id", "week_day");
