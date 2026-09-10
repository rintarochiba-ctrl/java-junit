package com.example;

public interface ScoreValidator {

    /**
     * 点数が有効な範囲内にあるかを外部システムで検証する。
     * @param score 検証対象の点数
     * @return 有効な点数であればtrue
     */
    boolean validate(int score);
}

