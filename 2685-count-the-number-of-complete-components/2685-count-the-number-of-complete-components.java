class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            ds.union(u, v);
        }

        Map<Integer, Integer> vertexCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = ds.findParent(i);
            int count = vertexCount.getOrDefault(root, 0);
            vertexCount.put(root, count + 1);
        }

        Map<Integer, Integer> edgeCount = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int root = ds.findParent(u);
            int count = edgeCount.getOrDefault(root, 0);
            edgeCount.put(root, count + 1);
        }

        int answer = 0;

        for (int root : vertexCount.keySet()) {
            int vertices = vertexCount.get(root);
            int totalEdges = edgeCount.getOrDefault(root, 0);
            int requiredEdges = vertices * (vertices - 1) / 2;

            if (totalEdges == requiredEdges) {
                answer++;
            }
        }

        return answer;
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int findParent(int node) {
            if (node == parent[node]) {
                return node;
            }

            int root = findParent(parent[node]);
            parent[node] = root;

            return root;
        }

        public void union(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);

            if (parentU == parentV) {
                return;
            }

            if (rank[parentU] > rank[parentV]) {
                parent[parentV] = parentU;
            } else if (rank[parentU] < rank[parentV]) {
                parent[parentU] = parentV;
            } else {
                parent[parentV] = parentU;
                rank[parentU]++;
            }
        }
    }
}