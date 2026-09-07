class Solution {
    public int climbStairs(int n) {
        int[] m = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            m[i] = -1;
        }

        return solve(n, m);
    }

    private int solve(int n, int[] m) {

        if (n == 0 || n == 1) {
            return 1;
        }

        if (m[n] != -1) {
            return m[n];
        }

        m[n] = solve(n - 1, m)
                + solve(n - 2, m);

        return m[n];
    }
}