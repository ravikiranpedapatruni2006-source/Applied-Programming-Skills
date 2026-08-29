/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map =
        new TreeMap<>();
        traverse(root, 0, 0, map);

        List<List<Integer>> result = new ArrayList<>();

        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {

            List<Integer> column = new ArrayList<>();

            for (PriorityQueue<Integer> nodes : rows.values()) {

                while (!nodes.isEmpty()) {
                    column.add(nodes.poll());
                }
            }

            result.add(column);
        }

        return result;
    }

    private void traverse(
            TreeNode node,
            int row,
            int col,
            TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {

        if (node == null) {
            return;
        }

        map.putIfAbsent(col, new TreeMap<>());
        map.get(col).putIfAbsent(row, new PriorityQueue<>());

        map.get(col).get(row).offer(node.val);

        traverse(node.left, row + 1, col - 1, map);
        traverse(node.right, row + 1, col + 1, map);
    }
}  
 