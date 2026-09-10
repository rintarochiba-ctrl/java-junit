# JUnit Docker環境

このプロジェクトは、Dockerを使用してJUnitテストを実行する簡単な環境です。

## プロジェクト構成

```
.
├── .dockerignore      # Dockerビルド時に除外するファイル
├── Dockerfile          # Dockerイメージの定義
├── pom.xml            # Mavenプロジェクト設定
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               ├── Calculator.java      # 計算を行うメインクラス
│   │               ├── PricingService.java  # 4と5章の基本演習用：価格計算サービス
│   │               ├── UserFunctions.java   # 6，4章の応用演習用：ユーザー機能クラス
│   │               └── ScoreValidator.java  # 6章の演習用：点数検証インターフェース
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── CalculatorTest.java  # Calculatorクラスのテスト
└── README.md
```

## 必要な環境

- Docker Desktop（またはDocker Engine）

## 使い方

### 1. Dockerイメージをビルドしてテストを実行

```bash
docker build -t junit-test .
```

このコマンドで、Dockerイメージをビルドし、自動的にJUnitテストが実行されます。

**注意**: ビルド時に指定したイメージ名（`-t` オプションの値）を覚えておいてください。以下のコマンドでも同じイメージ名を使用する必要があります。

### 2. テスト結果を確認

ビルド時にテストが実行され、結果が表示されます。すべてのテストが成功すると、ビルドが完了します。

### 3. コンテナを実行して対話的にテストを実行（オプション）

```bash
docker run -it --rm junit-test mvn test
```

**重要**: 上記のコマンドの `junit-test` は、ビルド時に `-t` オプションで指定したイメージ名と一致させる必要があります。異なる名前でビルドした場合は、その名前を使用してください。

例：`docker build -t junit-test-runner .` でビルドした場合
```bash
docker run -it --rm junit-test-runner mvn test
```

## テストの追加

新しいテストを追加する場合は、`src/test/java/` ディレクトリに新しいJavaファイルを作成してください。

例：
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnotherTest {
    @Test
    void myNewTest() {
        assertTrue(true);
    }
}
```

## ファイル説明

### 演習用クラス

以下のクラスは、JUnitの演習問題で使用するためのサンプルコードです。テストコードは含まれていません。

#### `com.example.Calculator`
計算を行うメインクラス。基本的な四則演算機能を提供します。
- `add()` - 足し算
- `subtract()` - 引き算
- `multiply()` - 掛け算
- `divide()` - 割り算（0除算の例外処理あり）

#### `com.example.PricingService`
価格計算サービスクラス。割引額の計算機能を提供します。
- `calculateDiscount()` - 元の価格と割引率から割引額を計算

#### `com.example.UserFunctions`
ユーザー機能クラス。基本的な計算機能とユーザー情報管理機能を提供します。
- `add()` - 足し算
- `divide()` - 割り算（0除算の例外処理あり）
- `isFitnessTestPassed()` - 体力テストの合格判定（年齢、性別、点数を考慮）
- `checkAndPass()` - 外部バリデーターを使用した点数チェックと合格判定（6章の演習用）

#### `com.example.ScoreValidator`
点数検証インターフェース。外部システムで点数が有効な範囲内にあるかを検証するためのインターフェースです（6章の演習用）。
- `validate()` - 点数が有効な範囲内にあるかを検証

### .dockerignore

Dockerビルド時にコンテナにコピーしないファイルやディレクトリを指定します。これにより、ビルド時間の短縮とイメージサイズの削減が可能です。

除外される主な項目：
- `target/` - Mavenのビルド成果物
- `.git/` - Gitリポジトリ情報
- IDE設定ファイル（`.idea/`, `.vscode/`など）
- コンパイル済みファイル（`*.class`）

## 技術スタック

- Java 17
- Maven 3.9.6
- JUnit 5.10.0