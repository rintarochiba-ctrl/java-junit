# MavenとJava開発環境が揃った公式イメージを使用
FROM maven:3.9.6-eclipse-temurin-17

# コンテナ内の作業ディレクトリを設定
WORKDIR /app

# プロジェクトファイルをコンテナにコピー
# pom.xmlを先にコピーして依存関係をダウンロードし、キャッシュを効率的に利用
COPY pom.xml .

# 依存関係をダウンロード（このレイヤーをキャッシュしてビルドを高速化）
RUN mvn dependency:go-offline -B

# ソースコードをコピー
COPY src ./src

# テストの実行
RUN mvn clean test