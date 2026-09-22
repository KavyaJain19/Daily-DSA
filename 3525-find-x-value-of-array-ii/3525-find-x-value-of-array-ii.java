class Solution {
    static class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;

        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.cnt[x];
        }

        return ans;
    }

    void build(int p, int l, int r, int[] nums) {
        if (l == r) {
            tree[p] = new Node(k);
            int v = nums[l] % k;
            tree[p].product = v;
            tree[p].cnt[v] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(p * 2, l, mid, nums);
        build(p * 2 + 1, mid + 1, r, nums);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    void update(int p, int l, int r, int index, int value) {
        if (l == r) {
            tree[p] = new Node(k);
            int v = value % k;
            tree[p].product = v;
            tree[p].cnt[v] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid)
            update(p * 2, l, mid, index, value);
        else
            update(p * 2 + 1, mid + 1, r, index, value);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    Node query(int p, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[p];

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(p * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(p * 2 + 1, mid + 1, r, ql, qr);

        Node left = query(p * 2, l, mid, ql, qr);
        Node right = query(p * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    Node merge(Node left, Node right) {
        Node res = new Node(k);

        res.product = (int) ((long) left.product * right.product % k);

        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }

        for (int r = 0; r < k; r++) {
            int rem = (int) ((long) left.product * r % k);
            res.cnt[rem] += right.cnt[r];
        }

        return res;
    }
}