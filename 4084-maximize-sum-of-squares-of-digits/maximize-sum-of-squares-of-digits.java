class Solution {
    public String maxSumOfSquares(int num, int sum) {

       

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);

            result.append(digit);
            sum -= digit;
        }

        if (sum > 0) {
            return "";
        }

        return result.toString();
    }
}