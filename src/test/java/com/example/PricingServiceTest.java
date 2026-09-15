package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//**1. 価格計算サービス (`PricingService`) のテスト実装**
//`PricingService.java`に対するテストファイルを新規作成し、以下の要件を満たすテストを実装してください。

// **1-A. 例外テストとタグ付け（割引率の境界値検証）**
// `calculateDiscount`メソッドは、割引率が0%から100%の範囲外の場合に`IllegalArgumentException`をスローするように実装されています。

// - **テストケース**:
//     - 割引率として-1（最小不合格境界値）を設定した場合に、`IllegalArgumentException`がスローされることを検証するテストを記述してください。
//     - 割引率として**101**（最大不合格境界値）を設定した場合に、`IllegalArgumentException`がスローされることを検証するテストを記述してください。
// - **タグ付け**:
//     - これらの例外テストを含むメソッドに、`@Tag("Guard_Boundary")`を適用してください。

// **1-B. パラメータ化テストの実装（計算ロジックの網羅）**
// 割引計算ロジックが、複数の入力データで一貫して正しい結果を返すことを検証します。

// - **データソースの作成**: 以下のデータセットを提供する**静的メソッド**を`PricingServiceTest`内に作成してください。
//     - **正常な境界値**: (元の価格: 1000, 割引率: **0**, 期待される割引額: **0**)
//     - **正常な境界値**: (元の価格: 1000, 割引率: **100**, 期待される割引額: **1000**)
//     - **計算検証**: (元の価格: 999, 割引率: 10, 期待される割引額: **99**) （小数点以下切り捨てを確認）
// - **テストメソッド**: 上記データソースを使用し、`calculateDiscount`メソッドの計算結果が期待値と一致することを検証する**1つの`@ParameterizedTest`メソッド**を実装してください。
// - **タグ付け**:
//     - このパラメータ化テストメソッドに、`@Tag("Calculation_Full")`を適用してください。

public class PricingServiceTest {
    private PricingService pricingService;

    @BeforeEach
    void setUp(){
        pricingService = new PricingService();
    }

    @AfterEach
    void resetData(){
        pricingService = null;
    }

    @Tag("Guard_Boundary")
    @Test
    void calculateDiscount_割引率がマイナス1の時は_エラーを投げる() {
        assertThrows(IllegalArgumentException.class, () -> {
            pricingService.calculateDiscount(1000, -1);
        });
    }

    @Tag("Guard_Boundary")
    @Test
    void calculateDiscount_割引率が101の時は_エラーを投げる() {
        assertThrows(IllegalArgumentException.class, () -> {
            pricingService.calculateDiscount(1000, 101);
        });
    }

    static Stream<Arguments> provideCalculationTestData() {
        return Stream.of(
            // Arguments.of(元の価格, 割引率, 期待される割引額)
            Arguments.of(1000, 0, 0),     // 正常な境界値 (0%)
            Arguments.of(1000, 100, 1000), // 正常な境界値 (100%)
            Arguments.of(999, 10, 99)      // 計算検証 (99.9 -> 切捨てで99)
        );
    }

    @Tag("Calculation_Full")
    @ParameterizedTest
    @MethodSource("provideCalculationTestData")
    void calculateDiscount_正常な値と計算の検証(int originalPrice, int discountRate, int expectedDiscount) {
        int actualDiscount = pricingService.calculateDiscount(originalPrice, discountRate);

        // 期待される割引額と実際の計算結果が一致するか検証
        assertEquals(expectedDiscount, actualDiscount, "割引額の計算結果が期待値と異なります");
    }
}
