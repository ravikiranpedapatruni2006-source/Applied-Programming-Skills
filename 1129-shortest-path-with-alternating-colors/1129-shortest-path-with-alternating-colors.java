class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
       
        List<Integer>[][] graph = new ArrayList[2][n];
        for (int i = 0; i < n; i++) {
            graph[0][i] = new ArrayList<>();
            graph[1][i] = new ArrayList<>();
        }
        
        for (int[] edge : redEdges) {
            graph[0][edge[0]].add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            graph[1][edge[0]].add(edge[1]);
        }

        
        int[][] dist = new int[2][n];
        Arrays.fill(dist[0], -1);
        Arrays.fill(dist[1], -1);

        
        Queue<int[]> queue = new LinkedList<>();

        
        dist[0][0] = 0;
        dist[1][0] = 0;
        queue.offer(new int[]{0, 0}); 
        queue.offer(new int[]{0, 1}); 
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int lastColor = curr[1];
            int nextColor = 1 - lastColor; 

            for (int v : graph[nextColor][u]) {
                if (dist[nextColor][v] == -1) {
                    dist[nextColor][v] = dist[lastColor][u] + 1;
                    queue.offer(new int[]{v, nextColor});
                }
            }
        }

       
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int d1 = dist[0][i];
            int d2 = dist[1][i];

            if (d1 == -1 && d2 == -1) {
                result[i] = -1;
            } else if (d1 == -1) {
                result[i] = d2;
            } else if (d2 == -1) {
                result[i] = d1;
            } else {
                result[i] = Math.min(d1, d2);
            }
        }

        return result;
    }
}
