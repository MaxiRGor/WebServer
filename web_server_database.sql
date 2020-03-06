-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Мар 06 2020 г., 12:22
-- Версия сервера: 5.7.25-0ubuntu0.16.04.2
-- Версия PHP: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `leonard`
--

-- --------------------------------------------------------

--
-- Структура таблицы `answers`
--

CREATE TABLE `answers` (
  `id_answer` int(11) NOT NULL,
  `id_question` int(11) NOT NULL DEFAULT '1',
  `right_answer` int(11) NOT NULL DEFAULT '0',
  `answer_one` int(11) NOT NULL DEFAULT '0',
  `answer_two` int(11) NOT NULL DEFAULT '0',
  `answer_three` int(11) NOT NULL DEFAULT '0',
  `answer_four` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `daily_quests`
--

CREATE TABLE `daily_quests` (
  `id` int(11) NOT NULL,
  `description` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `goal` int(11) NOT NULL,
  `reward_amount` int(11) NOT NULL,
  `reward_image_type` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `reward_type` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `friends_confirmed`
--

CREATE TABLE `friends_confirmed` (
  `id` int(11) NOT NULL,
  `user_id_first` int(11) NOT NULL,
  `user_id_second` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `friends_unconfirmed`
--

CREATE TABLE `friends_unconfirmed` (
  `id` int(11) NOT NULL,
  `user_id_first` int(11) NOT NULL,
  `user_id_second` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `friend_games`
--

CREATE TABLE `friend_games` (
  `id` int(11) NOT NULL,
  `id_user_who` int(11) NOT NULL,
  `status_who` smallint(6) NOT NULL,
  `number_who` smallint(6) NOT NULL,
  `id_user_to` int(11) NOT NULL,
  `status_to` smallint(6) NOT NULL,
  `number_to` smallint(6) NOT NULL,
  `difficulty` smallint(6) NOT NULL,
  `cost` bigint(20) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `game_status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `game_variables`
--

CREATE TABLE `game_variables` (
  `id` int(11) NOT NULL,
  `single_difficulty_costs` int(11) NOT NULL,
  `multi_rating_rewards` int(11) NOT NULL,
  `multi_rating_costs` int(11) NOT NULL,
  `multi_rating_min` int(11) NOT NULL,
  `single_difficulty_rewards` int(11) NOT NULL,
  `theme_costs` int(11) NOT NULL,
  `icons_costs` int(11) NOT NULL,
  `energy_time_to_refill` int(11) NOT NULL,
  `crystals_for_full` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `leaderboard`
--

CREATE TABLE `leaderboard` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT '0',
  `unique_number` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `icon` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `rank` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `crystals` int(11) NOT NULL,
  `easy_percent` int(11) NOT NULL,
  `medium_percent` int(11) NOT NULL,
  `hard_percent` int(11) NOT NULL,
  `rating_percent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `leaderboard_new`
--

CREATE TABLE `leaderboard_new` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `place` int(11) NOT NULL,
  `icon` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `rank` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `match_game_questions`
--

CREATE TABLE `match_game_questions` (
  `id` int(11) NOT NULL,
  `game_type` tinyint(1) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `multi_games`
--

CREATE TABLE `multi_games` (
  `id` int(11) NOT NULL,
  `game_range` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `user_first_id` int(11) NOT NULL,
  `user_first_unique` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_first_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_fisrt_status` int(11) NOT NULL,
  `user_first_count` int(11) NOT NULL,
  `user_second_id` int(11) NOT NULL,
  `user_second_unique` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_second_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_second_status` int(11) NOT NULL,
  `user_second_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `new_questions`
--

CREATE TABLE `new_questions` (
  `id` int(11) NOT NULL,
  `question_ru` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `question_en` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_1_ru` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_1_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_2_ru` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_2_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_3_ru` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_3_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_4_ru` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer_4_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `in_book` tinyint(1) DEFAULT NULL,
  `in_serial` tinyint(1) DEFAULT NULL,
  `right_answer` int(11) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `questions`
--

CREATE TABLE `questions` (
  `id_question` int(11) NOT NULL,
  `question_text` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `answer_one` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `answer_two` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `answer_three` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `answer_four` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `right_answer` int(3) DEFAULT NULL,
  `level` int(3) DEFAULT NULL,
  `difficulty` int(11) NOT NULL,
  `in_book` tinyint(1) DEFAULT NULL,
  `in_serial` tinyint(1) DEFAULT NULL,
  `category` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `uuid_bytes` binary(16) NOT NULL,
  `unique_number` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `uuid` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `current_icon` int(11) NOT NULL,
  `user_name` varchar(16) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Player Name',
  `user_money` bigint(20) NOT NULL DEFAULT '1425',
  `easy_games` int(11) NOT NULL DEFAULT '0',
  `medium_games` int(11) NOT NULL DEFAULT '0',
  `hard_games` int(11) NOT NULL DEFAULT '0',
  `easy_winnings` int(11) NOT NULL DEFAULT '0',
  `medium_winnings` int(11) NOT NULL DEFAULT '0',
  `hard_winnings` int(11) NOT NULL DEFAULT '0',
  `is_adv` tinyint(4) NOT NULL DEFAULT '1',
  `is_books` tinyint(4) NOT NULL DEFAULT '0',
  `is_films` tinyint(4) NOT NULL DEFAULT '1',
  `current_theme` int(11) NOT NULL DEFAULT '0',
  `is_credit` tinyint(4) NOT NULL DEFAULT '0',
  `credit_time` bigint(20) NOT NULL DEFAULT '0',
  `credit_sum` bigint(20) NOT NULL DEFAULT '0',
  `is_debit` tinyint(4) NOT NULL DEFAULT '0',
  `debit_time` bigint(20) NOT NULL DEFAULT '0',
  `debit_sum` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_daily_quests`
--

CREATE TABLE `users_daily_quests` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `progress` int(11) NOT NULL,
  `is_reward_taken` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_emails`
--

CREATE TABLE `users_emails` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_icons_purchases`
--

CREATE TABLE `users_icons_purchases` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ic_0` tinyint(1) NOT NULL,
  `ic_1` tinyint(1) NOT NULL,
  `ic_2` tinyint(1) NOT NULL,
  `ic_3` tinyint(1) NOT NULL,
  `ic_4` tinyint(1) NOT NULL,
  `ic_5` tinyint(1) NOT NULL,
  `ic_6` tinyint(1) NOT NULL,
  `ic_7` tinyint(1) NOT NULL,
  `ic_8` tinyint(1) NOT NULL,
  `ic_9` tinyint(1) NOT NULL,
  `ic_10` tinyint(1) NOT NULL,
  `ic_11` tinyint(1) NOT NULL,
  `ic_12` tinyint(1) NOT NULL,
  `ic_13` tinyint(1) NOT NULL,
  `ic_14` tinyint(1) NOT NULL,
  `ic_15` tinyint(1) NOT NULL,
  `ic_16` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_multi_rating`
--

CREATE TABLE `users_multi_rating` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `crystals` int(11) NOT NULL,
  `games` int(11) NOT NULL,
  `winnings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_single_points`
--

CREATE TABLE `users_single_points` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `max_points` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `time_when_full` bigint(20) NOT NULL,
  `time_to_full` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users_skins_purchases`
--

CREATE TABLE `users_skins_purchases` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `skin_0_t` tinyint(1) NOT NULL,
  `skin_1_s` tinyint(1) NOT NULL,
  `skin_2_l` tinyint(1) NOT NULL,
  `skin_3_n` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id_answer`),
  ADD KEY `id_answer` (`id_answer`),
  ADD KEY `id_question` (`id_question`);

--
-- Индексы таблицы `daily_quests`
--
ALTER TABLE `daily_quests`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- Индексы таблицы `friends_confirmed`
--
ALTER TABLE `friends_confirmed`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `user_id_first` (`user_id_first`),
  ADD KEY `user_id_second` (`user_id_second`);

--
-- Индексы таблицы `friends_unconfirmed`
--
ALTER TABLE `friends_unconfirmed`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `user_id_first` (`user_id_first`),
  ADD KEY `user_id_second` (`user_id_second`);

--
-- Индексы таблицы `friend_games`
--
ALTER TABLE `friend_games`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_user_who` (`id_user_who`),
  ADD KEY `id_user_to` (`id_user_to`);

--
-- Индексы таблицы `game_variables`
--
ALTER TABLE `game_variables`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `leaderboard`
--
ALTER TABLE `leaderboard`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `leaderboard_new`
--
ALTER TABLE `leaderboard_new`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_2` (`id`);

--
-- Индексы таблицы `match_game_questions`
--
ALTER TABLE `match_game_questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `multi_games`
--
ALTER TABLE `multi_games`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `user_first_id` (`user_first_id`),
  ADD KEY `user_second_id` (`user_second_id`);

--
-- Индексы таблицы `new_questions`
--
ALTER TABLE `new_questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id_question`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uuid_bytes`),
  ADD UNIQUE KEY `id_user` (`id_user`),
  ADD KEY `unique_number` (`unique_number`),
  ADD KEY `uuid` (`uuid`);

--
-- Индексы таблицы `users_daily_quests`
--
ALTER TABLE `users_daily_quests`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `users_emails`
--
ALTER TABLE `users_emails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `email` (`email`);

--
-- Индексы таблицы `users_icons_purchases`
--
ALTER TABLE `users_icons_purchases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `users_multi_rating`
--
ALTER TABLE `users_multi_rating`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `users_single_points`
--
ALTER TABLE `users_single_points`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `users_skins_purchases`
--
ALTER TABLE `users_skins_purchases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `answers`
--
ALTER TABLE `answers`
  MODIFY `id_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1024;
--
-- AUTO_INCREMENT для таблицы `daily_quests`
--
ALTER TABLE `daily_quests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `friends_confirmed`
--
ALTER TABLE `friends_confirmed`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT для таблицы `friends_unconfirmed`
--
ALTER TABLE `friends_unconfirmed`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT для таблицы `friend_games`
--
ALTER TABLE `friend_games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT для таблицы `game_variables`
--
ALTER TABLE `game_variables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT для таблицы `leaderboard`
--
ALTER TABLE `leaderboard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1129;
--
-- AUTO_INCREMENT для таблицы `leaderboard_new`
--
ALTER TABLE `leaderboard_new`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26511023;
--
-- AUTO_INCREMENT для таблицы `match_game_questions`
--
ALTER TABLE `match_game_questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4614;
--
-- AUTO_INCREMENT для таблицы `multi_games`
--
ALTER TABLE `multi_games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=639;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=502;
--
-- AUTO_INCREMENT для таблицы `users_daily_quests`
--
ALTER TABLE `users_daily_quests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT для таблицы `users_emails`
--
ALTER TABLE `users_emails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;
--
-- AUTO_INCREMENT для таблицы `users_icons_purchases`
--
ALTER TABLE `users_icons_purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=501;
--
-- AUTO_INCREMENT для таблицы `users_multi_rating`
--
ALTER TABLE `users_multi_rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=501;
--
-- AUTO_INCREMENT для таблицы `users_single_points`
--
ALTER TABLE `users_single_points`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=501;
--
-- AUTO_INCREMENT для таблицы `users_skins_purchases`
--
ALTER TABLE `users_skins_purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=501;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
