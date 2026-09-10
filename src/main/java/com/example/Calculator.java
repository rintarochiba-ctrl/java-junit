package com.example;

/**
 * 簡単な計算を行うクラス
 */
public class Calculator {
    
    /**
     * 2つの数値を足し算する
     * @param a 最初の数値
     * @param b 2番目の数値
     * @return 2つの数値の合計
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * 2つの数値を引き算する
     * @param a 最初の数値
     * @param b 2番目の数値
     * @return 2つの数値の差
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * 2つの数値を掛け算する
     * @param a 最初の数値
     * @param b 2番目の数値
     * @return 2つの数値の積
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * 2つの数値を割り算する
     * @param a 被除数
     * @param b 除数
     * @return 2つの数値の商
     * @throws IllegalArgumentException 除数が0の場合
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数は0にできません");
        }
        return (double) a / b;
    }
}

