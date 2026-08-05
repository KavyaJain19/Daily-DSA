class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int[] arr : invocations) {
            graph[arr[0]].add(arr[1]);
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> q = new LinkedList<>();
        q.add(k);
        visited[k] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : graph[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        for (int[] arr : invocations) {
            int u = arr[0];
            int v = arr[1];

            if (!visited[u] && visited[v]) {
                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}