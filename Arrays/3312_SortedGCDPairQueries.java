class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums)
            freq[x]++;

        long[] cnt = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long total = 0;

            for (int j = g; j <= max; j += g)
                total += freq[j];

            cnt[g] = total * (total - 1) / 2;

            for (int j = 2 * g; j <= max; j += g)
                cnt[g] -= cnt[j];
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++)
            prefix[i] = prefix[i - 1] + cnt[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i] + 1;

            int l = 1, r = max;
            while (l < r) {
                int mid = l + (r - l) / 2;

                if (prefix[mid] >= q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;

    }
}