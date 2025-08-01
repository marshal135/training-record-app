# Backend - トレーニング記録アプリ

## 技術構成
- Spring Boot
- Spring Security（簡易ログイン）
- JPA + MySQL
- DTO構成でControllerとEntityを分離

## 主なAPIエンドポイント
| メソッド | パス                  | 説明 |
|----------|-----------------------|------|
| POST     | /user                 | 新規ユーザー登録 |
| POST     | /user/login           | ログイン |
| GET      | /exercisemaster/exercises | 種目一覧取得 |
| POST     | /exercisemaster/exercise | 種目登録 |
| GET      | /workout              | ワークアウト一覧取得（userId, year, month）|
| POST     | /workout              | ワークアウト登録 |
| DELETE   | /workout/{id}         | ワークアウト削除 |

## 起動方法
```bash
./mvnw spring-boot:run
```

## DB接続設定
セットアップ方法
本アプリは MySQL を使用しています。
以下の手順でデータベースを作成してください。

1. MySQL を起動
2. SQL文を実行（`backend/schema.sql` に含まれています）

※`application-example.properties` をコピーして、 `application.properties` を作成するのをお勧めします。



