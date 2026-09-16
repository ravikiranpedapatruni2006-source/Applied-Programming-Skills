import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        int groupGroupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupGroupId++;
            }
        }

       
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < groupGroupId; i++) groupGraph.add(new ArrayList<>());

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[groupGroupId];

        
        for (int curr = 0; curr < n; curr++) {
            for (int prev : beforeItems.get(curr)) {
                
                itemGraph.get(prev).add(curr);
                itemIndegree[curr]++;

                
                if (group[prev] != group[curr]) {
                    groupGraph.get(group[prev]).add(group[curr]);
                    groupIndegree[group[curr]]++;
                }
            }
        }

        
        List<Integer> itemOrder = topologicalSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topologicalSort(groupGraph, groupIndegree, groupGroupId);

        
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }

        
        Map<Integer, List<Integer>> itemsByGroup = new HashMap<>();
        for (int item : itemOrder) {
            itemsByGroup.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        
        int[] result = new int[n];
        int index = 0;
        for (int grp : groupOrder) {
            List<Integer> items = itemsByGroup.getOrDefault(grp, new ArrayList<>());
            for (int item : items) {
                result[index++] = item;
            }
        }

        return result;
    }

    private List<Integer> topologicalSort(List<List<Integer>> graph, int[] indegree, int totalCount) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int neighbor : graph.get(curr)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        
        return order.size() == totalCount ? order : new ArrayList<>();
    }
}