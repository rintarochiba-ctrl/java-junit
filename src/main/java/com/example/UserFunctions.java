package com.example;

public class UserFunctions {

    // 既存のテストで使われている基本的な計算機能もここに含める
    public int add(int a, int b) {
        return a + b;
    }
    
    // 5章の例外テストで使用する除算機能
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return a / b;
    }
    
    // --- 演習問題 4章で追加する User 機能 ---
    private String name;
    private int age;
    private String gender; // "MALE", "FEMALE", "OTHER"
    private int fitnessScore; // 体力テストの点数（0-100点）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    /**
     * 年齢、性別、体力テストの点数を考慮して体力テストの合格判定を行う。
     * 3階層の入れ子構造で4つのパターンに分けて判定ロジックを実装。
     * @return 体力テストに合格した場合true
     */
    public boolean isFitnessTestPassed() {
        // 第1階層: 20歳以上の場合
        if (this.age >= 20) {
            // 第2階層: 性別による分岐
            if ("MALE".equals(this.gender)) {
                // 第3階層: パターン1 - 20歳以上の男性
                if (this.fitnessScore >= 60) {
                    return true;
                } else {
                    return false;
                }
            } else if ("FEMALE".equals(this.gender)) {
                // 第3階層: パターン2 - 20歳以上の女性
                if (this.fitnessScore >= 55) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // 第3階層: パターン3 - 20歳以上のその他/未設定
                return false;
            }
        } else {
            // 第1階層: 19歳以下の場合
            // 第2階層: 性別による分岐なし、一律で判定
            // 第3階層: パターン4 - 19歳以下は一律80点以上で合格
            return this.fitnessScore >= 80;
        }
    }

    /**
     * 点数を外部バリデーターでチェックし、その後内部ロジックで合格判定を行う。
     * @param score チェックする点数
     * @param validator 外部バリデーター
     * @return 最終的な合格判定
     */
    public boolean checkAndPass(int score, ScoreValidator validator) {
        // 外部バリデーターがfalseを返したら即座に不合格 (ブランチ1)
        if (!validator.validate(score)) { 
            return false; 
        }
        
        // 内部の合格ロジック (点数が50点以上で合格)
        if (score >= 50) { // ブランチ2 (合格)
            return true;
        } else { // ブランチ3 (不合格)
            return false;
        }
    }
}

