class Solution { 
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        StringBuilder s = new StringBuilder(); 
        int count = 1; 

        for(int i = 0; i < chars.length - 1; i++){ 
            if(chars[i] == chars[i+1]) {
                count++; 
            } else {
                s.append(chars[i]); 
                if(count > 1){ 
                    s.append(count);
                } 
                count = 1; 
            } 
        } 
        s.append(chars[chars.length - 1]); 
        if (count > 1) {
            s.append(count); 
        }
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        return s.length(); 
    } 
}