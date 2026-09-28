class Solution {
    public int maximumSum(int[] arr) {

        int noDelete = arr[0];
        int oneDelete = Integer.MIN_VALUE / 2;
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int prevNoDelete = noDelete;

            noDelete = Math.max(arr[i], noDelete + arr[i]);

            oneDelete = Math.max(
                oneDelete + arr[i],
                prevNoDelete
            );

            max = Math.max(max, Math.max(noDelete, oneDelete));
        }

        return max;
    }
}