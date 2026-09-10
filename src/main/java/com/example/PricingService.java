package com.example;

public class PricingService {

    /**
     * 元の価格から割引率に応じた割引額を計算する。
     * 割引率は0%から100%の範囲外の場合、例外をスローする。
     * * @param originalPrice 元の価格
     * @param discountRate 割引率 (パーセント 0〜100)
     * @return 割引額
     */
    public int calculateDiscount(int originalPrice, int discountRate) {
        if (discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("割引率は0%から100%の範囲内で設定してください。");
        }
        
        // 割引額を計算 (小数点以下は切り捨て)
        return (int) (originalPrice * ((double) discountRate / 100));
    }
}

