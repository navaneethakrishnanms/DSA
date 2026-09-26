class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] s = sentence.split(" ");
        for (int i = 0; i < s.length; i++) {
            isindictionary(s, i, dictionary);
        }
        return String.join(" ", s);
    }
    public static void isindictionary(String[] s, int index, List<String> dictionary) {
        String shortest = s[index];
        for (int i = 0; i < dictionary.size(); i++) {
            String root = dictionary.get(i);
            if (s[index].startsWith(root)) {
                if (root.length() < shortest.length()) {
                    shortest = root;
                }
            }
        }
        s[index] = shortest;
    }
}