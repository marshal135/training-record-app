# トレーニング記録アプリ

## 概要
このアプリは、ユーザーが筋トレの記録を簡単に登録・一覧・削除できるWebアプリです。

- フロントエンド: React + TypeScript + Tailwind CSS
- バックエンド: Spring Boot + MySQL
- ユーザー認証: Spring Security（簡易）
- 記録の月別表示・種目登録などの機能あり


## 画面イメージ

### ログイン画面
![Login Screen](./photo/login_screen.png)

### 新規ユーザー登録画面
![Register Form](./photo/newuser_regster.png)

### トレーニング登録画面
![Workout Form](./photo/workout_register.png)

### トレーニング一覧画面
![Workout List](./photo/workoutlist.png)

## ディレクトリ構成

trainingapp/
├── backend/    # Spring Boot APIサーバー
├── frontend/   # React フロントエンド