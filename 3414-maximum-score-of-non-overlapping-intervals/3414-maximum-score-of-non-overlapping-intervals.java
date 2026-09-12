class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[3] - b[3];
        });

        long[][] dp = new long[5][n + 1];
        int[][][] ids = new int[5][n + 1][];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                ids[k][i] = new int[0];
            }
        }

        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = arr[i][1];
        }

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {

                dp[k][i] = dp[k][i - 1];
                ids[k][i] = ids[k][i - 1];

                int start = arr[i - 1][0];
                int weight = arr[i - 1][2];
                int index = arr[i - 1][3];

                int p = findPrevious(ends, i - 1, start);

                long newWeight = dp[k - 1][p] + weight;

                int[] newIds = add(ids[k - 1][p], index);

                if (newWeight > dp[k][i] ||
                    (newWeight == dp[k][i] &&
                     smaller(newIds, ids[k][i]))) {

                    dp[k][i] = newWeight;
                    ids[k][i] = newIds;
                }
            }
        }

        return ids[4][n];
    }

    private int findPrevious(int[] ends, int right, int start) {
        int left = 0;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (ends[mid] < start) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int[] add(int[] a, int x) {
        int[] result = new int[a.length + 1];

        int i = 0;

        while (i < a.length && a[i] < x) {
            result[i] = a[i];
            i++;
        }

        result[i] = x;

        while (i < a.length) {
            result[i + 1] = a[i];
            i++;
        }

        return result;
    }

    private boolean smaller(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}