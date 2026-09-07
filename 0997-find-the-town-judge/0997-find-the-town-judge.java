class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] trustScores = new int[n + 1];
        for (int[] relation : trust) {
            int personA = relation[0];
            int personB = relation[1];
            trustScores[personA]--;
            trustScores[personB]++;
        }
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}

      