class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] seen = new boolean[1000];
        int ans = 0;

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int l = 0; l < digits.length; l++) {
                    if (i == j || j == l || i == l)
                        continue;

                    if (digits[i] == 0)
                        continue;

                    if (digits[l] % 2 != 0)
                        continue;

                    int num = digits[i] * 100 + digits[j] * 10 + digits[l];

                    if (!seen[num]) {
                        seen[num] = true;
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}