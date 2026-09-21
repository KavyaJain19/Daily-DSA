class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            for (int r = 0; r < k; r++) {
                if (dp[r] != 0) {
                    int nr = (int)((r * (long)num) % k);
                    next[nr] += dp[r];
                }
            }

            int r = num % k;
            next[r]++;

            for (int r2 = 0; r2 < k; r2++) {
                ans[r2] += next[r2];
            }

            dp = next;
        }

        return ans;
    }
}