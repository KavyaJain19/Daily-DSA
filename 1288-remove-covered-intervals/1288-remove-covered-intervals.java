class Solution {
    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        int count = 1;

        int[] curr = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] >= curr[0] &&
                intervals[i][1] <= curr[1]) {
                continue;
            }

            count++;
            curr = intervals[i];
        }

        return count;
    }
}