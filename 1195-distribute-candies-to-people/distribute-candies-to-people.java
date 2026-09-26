class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] arr=new int[num_people];
        int i=0,j=1,sum=0;
        while(candies>0){
            arr[i%(num_people)]+=Math.min(j, candies);
            sum+=j;
            candies-=j;
            j++;
            i++;
        }
        return arr;
    }
}