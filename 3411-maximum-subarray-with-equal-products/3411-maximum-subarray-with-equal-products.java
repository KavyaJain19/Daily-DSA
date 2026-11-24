class Solution {
    public int maxLength(int[] nums) {
        int left = 0, product = 1, max = 2;

        for (int right = 0; right < nums.length; right++) {

            while (gcd(product, nums[right]) > 1) {
                product /= nums[left];
                left++;
            }

            product *= nums[right];
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}