import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int LOG = 18;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        int[][] up = new int[n][LOG];

        int left = 0;
        for (int right = 0; right < n; right++) {
            while (arr[right][0] - arr[left][0] > maxDiff) {
                up[left][0] = right - 1;
                left++;
            }
        }

        while (left < n) {
            up[left][0] = n - 1;
            left++;
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int a = pos[queries[i][0]];
            int b = pos[queries[i][1]];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b) {
                ans[i] = 0;
                continue;
            }

            int cur = a;
            int jumps = 0;

            for (int j = LOG - 1; j >= 0; j--) {
                if (up[cur][j] < b) {
                    jumps += (1 << j);
                    cur = up[cur][j];
                }
            }

            if (up[cur][0] >= b)
                ans[i] = jumps + 1;
            else
                ans[i] = -1;
        }

        return ans;
    }
}