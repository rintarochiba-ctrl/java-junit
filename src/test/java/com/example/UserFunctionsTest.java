package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.*;

//基本演習問題

//1，以下に対してのテストファイルを作成すること。
//src\main\java\com\example\UserFunctions.java

//2，@Testやアサーション(assertEqualsなど)を使うために必要なインポート文を記述してください。

//**3，初期化 (`@BeforeEach`)**:
//- **各テストメソッドが実行される直前**に、テスト対象クラスの新しいインスタンスを生成し、フィールドに保持してください。
//- さらに、そのインスタンスの`name`を"TestUser"に設定してください。

//**4，後処理 (`@AfterEach`)**:
//- **各テストメソッドが実行された直後**に、テスト用インスタンスを`null`に設定し、テスト後のリソースを解放する処理を記述してください。

//**5，成人判定ロジックの検証（アサーション）**
//`@Test`メソッドを3つ作成し、以下の要件を検証してください。
//- **テストケース A: 20歳以上男性の合格テスト（最小合格境界値）**
//    - `age`を**20**歳（年齢の境界値）、`fitnessScore`を**60**点（20歳以上男性の最小合格点）に設定してください。
//    - この設定で`isFitnessTestPassed()`メソッドが**真 (`true`)** を返すことを検証してください。

//- **テストケース B: 19歳以下の不合格テスト（最大不合格境界値）**
//    - `age`を**19**歳（年齢の境界値）、`fitnessScore`を**79**点（19歳以下の最大不合格点）に設定してください。
//    - この設定で`isFitnessTestPassed()`メソッドが**偽 (`false`)** を返すことを検証してください。

//- **テストケース C: 20歳以上女性の不合格境界値テスト（最大不合格境界値）**
//    - `age`を**25**歳、`gender`を"FEMALE"に設定し直してください。
//    - `fitnessScore`を**54**点（20歳以上女性の最大不合格点）に設定した場合に、`isFitnessTestPassed()`メソッドが**偽 (`false`)** を返すことを検証してください。
//    - *（このテストは、異なる性別での合格判定境界値（55点）の直前を検証します。）*

public class UserFunctionsTest {
    private UserFunctions testUser;
    private ScoreValidator mockValidator;

    @BeforeEach
    void setUp(){
        testUser = new UserFunctions();
        mockValidator = mock(ScoreValidator.class);
    }

    @AfterEach
    void tearDown(){
        testUser = null;
    }

    @Test
    void isFitnessTestPassed_20歳男性で60点なら_Trueを返す(){

        testUser.setName("tanaka");
        testUser.setAge(20);
        testUser.setGender("MALE");
        testUser.setFitnessScore(60);
        testUser.getName();
        testUser.getAge();
        testUser.getGender();
        testUser.getFitnessScore();

        boolean result = testUser.isFitnessTestPassed();

        assertTrue(result, "20歳以上男性で60点以上の場合、trueとなるべきです");
    }

    @Test
    void isFitnessTestPassed_19歳女性で79点なら_falseを返す(){
        testUser.setAge(19);
        testUser.setGender("FEMALE");
        testUser.setFitnessScore(79);

        boolean result = testUser.isFitnessTestPassed();

        assertFalse(result, "19歳以下女性で80点未満の場合、falseとなるべきです");
    }

    @Test
    void isFitnessTestPassed_25歳女性で55点以上なら_trueを返す(){
        testUser.setAge(25);
        testUser.setGender("FEMALE");
        testUser.setFitnessScore(55);

        boolean result = testUser.isFitnessTestPassed();

        assertTrue(result, "25歳女性で55点以上の場合、trueとなるべきです");
    }

    @Test
    void isFitnessTestPassed_25歳女性で54点なら_falseを返す(){
        testUser.setAge(25);
        testUser.setGender("FEMALE");
        testUser.setFitnessScore(54);

        boolean result = testUser.isFitnessTestPassed();

        assertFalse(result, "25歳女性で55点未満の場合、falseとなるべきです");
    }

    //応用演習問題
    @Test
    void add_1ミリ秒以内に処理が完了するか(){
        assertTimeout(Duration.ofMillis(1), () -> {
            testUser.add(1, 2);
        });
    }

    @Test
    void divide_引数が正常な場合() {
        int result = testUser.divide(1, 1);
        assertEquals(1, result);
    }


    @Test
    void divide_0除算で例外を投げる() {
    ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
        testUser.divide(1, 0);
    });

    assertEquals("/ by zero", exception.getMessage());
    }

    // スタブ設定 A を使ったテスト
    @Test
    void testCheckAndPass_スタブ設定A_常にtrueを返す場合() {
        // スタブ設定 A: 50以上100未満の整数が渡されたら true を返す
        when(mockValidator.validate(intThat(score -> score >= 50 && score <= 100))).thenReturn(true); 
        // スタブ設定 C: 0以上50未満の整数が渡されたら false を返す
        when(mockValidator.validate(intThat(score -> score < 50 || score > 100))).thenReturn(false);
        //テストケース A-1 (合格ブランチ): スタブ設定Aを使用し、点数を50点に設定して、trueを返すことを検証してください。
        boolean resultA = testUser.checkAndPass(50, mockValidator);
        assertTrue(resultA);

        //テストケース A-2 (不合格ブランチ): スタブ設定Aを使用し、点数を49点に設定して、trueを返すことを検証してください。
        boolean resultB = testUser.checkAndPass(49, mockValidator);
        assertFalse(resultB);

        //テストケースC-1 checkAndPass(60, validator)を実行した後、validator.validate()メソッドが正確に1回呼び出されたことをMockito.verify()を使って検証してください。
        testUser.checkAndPass(60, mockValidator);
        verify(mockValidator, times(1)).validate(60);
    }

    // スタブ設定 B を使ったテスト（0のときfalse、それ以外true）
    @Test
    void testCheckAndPass_スタブ設定B_0のときだけfalseを返す場合() {
        // スタブ設定 B:
        // 全般的には true を返しつつ、0の時だけ false に上書き設定する
        when(mockValidator.validate(anyInt())).thenReturn(true);
        when(mockValidator.validate(0)).thenReturn(false);

        // 0を渡した時は false になることを確認
        boolean resultFalse = testUser.checkAndPass(0, mockValidator);
        assertFalse(resultFalse);

        // 0以外を渡した時は true になることを確認
        boolean resultTrue = testUser.checkAndPass(50, mockValidator);
        assertTrue(resultTrue);
    }

}
