class ThroneInheritance {
    private String king;
    private Map<String, List<String>> childrenMap;
    private Set<String> dead;


    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.childrenMap = new HashMap<>();
        this.dead = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        childrenMap.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);

        
    }
    
    public void death(String name) {
        dead.add(name);

        
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;

        
    }
    private void dfs (String current, List<String> order) {
        if (!dead.contains(current)) {
            order.add(current);
        }
        List<String> children = childrenMap.get(current);
        if (children != null) {
            for (String child : children) {
                dfs(child, order);
            }
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */