class Solution {
    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        Map<String, Integer> emailToIndex = new HashMap<>();

        
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (emailToIndex.containsKey(email)) {
                    uf.union(i, emailToIndex.get(email));
                } else {
                    emailToIndex.put(email, i);
                }
            }
        }

        
        Map<Integer, List<String>> rootToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToIndex.entrySet()) {
            String email = entry.getKey();
            int rootIndex = uf.find(entry.getValue());
            rootToEmails.computeIfAbsent(rootIndex, k -> new ArrayList<>()).add(email);
        }

        
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rootToEmails.entrySet()) {
            int rootIndex = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);

            List<String> account = new ArrayList<>();
            account.add(accounts.get(rootIndex).get(0)); 
            account.addAll(emails);
            mergedAccounts.add(account);
        }

        return mergedAccounts;
    }
}