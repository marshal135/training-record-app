CREATE DATABASE IF NOT EXISTS training_app DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE training_app;

-- ユーザー情報
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 種目マスタ
CREATE TABLE exercise_master (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50),
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- トレーニング記録
CREATE TABLE workouts (
    workout_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    exercise_id INT NOT NULL,
    workout_date DATE,
    reps INT,
    sets INT,
    weight FLOAT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (exercise_id) REFERENCES exercise_master(exercise_id)
);
