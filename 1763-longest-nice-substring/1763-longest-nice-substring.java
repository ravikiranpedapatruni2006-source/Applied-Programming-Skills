class Solution {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return "";
        }
        char[] chars = s.toCharArray();
        java.util.Set<Character> set = new java.util.HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean hasLower = set.contains(Character.toLowerCase(c));
            boolean hasUpper = set.contains(Character.toUpperCase(c));
            if (!hasLower || !hasUpper) {
                String left = longestNiceSubstring(s.substring(0, i));
                String right = longestNiceSubstring(s.substring(i + 1));
                return left.length() >= right.length() ? left : right;
            }
        }
        return s;
    }
}
