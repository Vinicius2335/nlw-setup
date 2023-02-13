-- 0 = domingo, 1 = segunda, 2 = terça, 3 = quarta, 4 = quinta, 5 = sexta, 6 = sabado

INSERT INTO habits (id_habit, title, created_at)
VALUES ("0730ffac-d039-4194-9571-01aa2aa0efbd", "Beber 2L água", "2022-12-31T03:00:00.000");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("69f401f8-e41b-408f-9b43-c0b7270bfef6", 1, "0730ffac-d039-4194-9571-01aa2aa0efbd");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("f7a3e868-3e74-4878-b81a-57100639e3ae", 2, "0730ffac-d039-4194-9571-01aa2aa0efbd");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("f39ac223-4059-4358-8e10-1475a2cf7496", 3, "0730ffac-d039-4194-9571-01aa2aa0efbd");

-- inserindo segundo exercicio
INSERT INTO habits (id_habit, title, created_at)
VALUES ("00880d75-a933-4fef-94ab-e05744435297", "Exercitar", "2023-01-03T03:00:00.000");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("9ff85eec-22e3-465a-988c-4fda3a2b35b6", 3, "00880d75-a933-4fef-94ab-e05744435297");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("6922915e-6a32-4afc-b327-5af856520355", 4, "00880d75-a933-4fef-94ab-e05744435297");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("4f5068e9-1d46-44a6-8a7f-54b05d88b7e4", 5, "00880d75-a933-4fef-94ab-e05744435297");

-- inserindo o terceiro exercicio
INSERT INTO habits (id_habit, title, created_at)
VALUES ("fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00", "Dormir 8h", "2023-01-08T03:00:00.000");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("bc073433-9a7d-4394-b477-7d0dcec0db4a", 1, "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("ea4a356f-d94d-4be1-890e-50d82143d1a8", 2, "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("4f7054be-2e2e-46c9-8d76-b607f1f63132", 3, "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("a487e254-4721-4799-83a2-15ed79caea70", 4, "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00");

INSERT INTO habit_week_days (id_habit_week_day, week_day, habit_id)
VALUES ("598b6b6b-24a0-444d-a65c-e90bd556a7a3", 5, "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00");

-- habitos completados
INSERT INTO days (id_day, date)
VALUES ("a7755dda-eab8-400c-8eca-d434ef790c73", "2023-01-01T03:00:00.000");

INSERT INTO day_habits (id_day_habit, habit_id, day_id)
VALUES ("1bfef9a0-1e1e-43d8-9d9a-5ff6aa17e0a6", "0730ffac-d039-4194-9571-01aa2aa0efbd", "a7755dda-eab8-400c-8eca-d434ef790c73");

INSERT INTO days (id_day, date)
VALUES ("81b8a315-60ad-46b0-b02b-4a5765cc54d4", "2023-01-06T03:00:00.000");

INSERT INTO day_habits (id_day_habit, habit_id, day_id)
VALUES ("590189a5-0ef8-4c85-b4fb-d552df731302", "0730ffac-d039-4194-9571-01aa2aa0efbd", "81b8a315-60ad-46b0-b02b-4a5765cc54d4");

INSERT INTO days (id_day, date)
VALUES ("354fa601-0c46-4ac0-9907-b4ac5037071d", "2023-01-03T03:00:00.000");

INSERT INTO day_habits (id_day_habit, habit_id, day_id)
VALUES ("26bbd7cf-f564-422c-a85d-ad49e23219f0", "00880d75-a933-4fef-94ab-e05744435297", "354fa601-0c46-4ac0-9907-b4ac5037071d");

INSERT INTO day_habits (id_day_habit, habit_id, day_id)
VALUES ("b446a2a8-e5f6-4731-86ee-0967171a4b46", "fa1a1bcf-3d87-4626-8c0d-d7fd1255ac00", "354fa601-0c46-4ac0-9907-b4ac5037071d");