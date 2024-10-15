package com.aakash.dsa.dynamic.programming;

import java.util.Arrays;

public class NthFibonacci {
    public static void main(String[] args) {
        System.out.println(fib(10));
        int n = 10;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println(fibMemo(n, memo));
        System.out.println(fibTab(n));
        System.out.println(fibOptimised(n));
    }

    public static Integer fib(int n){
        if (n == 0 || n == 1){
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static Integer fibMemo(int n, int[] memo){
        if (n == 0 || n == 1){
            memo[n] = n;
            return n;
        }

        if (memo[n] != -1){
            return memo[n];
        }

        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);

        return memo[n];
    }

    public static Integer fibTab(int n){
        int[] dp = new int[n + 1];
        if (n == 0 || n == 1){
            return n;
        }

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static Integer fibOptimised(int n){
        if (n == 0 || n == 1){
            return n;
        }

        int prev1 = 0;
        int prev2 = 1;

        for (int i = 2; i <=n; i++) {
            int current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }

        return prev2;
    }
}
