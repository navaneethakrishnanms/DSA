class Solution {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        boolean[] used = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a']--;
            if (used[c - 'a']) {
                continue;
            }
            while (sb.length() > 0 &&
                   sb.charAt(sb.length() - 1) > c &&
                   freq[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                char removed = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                used[removed - 'a'] = false;
            }
            sb.append(c);
            used[c - 'a'] = true;
        }
        return sb.toString();
    }
}