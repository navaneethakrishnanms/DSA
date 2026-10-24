class Solution {
    private int[] days = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice.compareTo(arriveBob) < 0 ? arriveBob : arriveAlice;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        
        int startDay = toDayOfYear(start);
        int endDay = toDayOfYear(end);
        
        return Math.max(endDay - startDay + 1, 0);
    }

    private int toDayOfYear(String s) {
        int month = Integer.parseInt(s.substring(0, 2));
        int day = Integer.parseInt(s.substring(3, 5));
        int totalDays = day;
        for (int i = 0; i < month - 1; ++i) {
            totalDays += days[i];
        }
        return totalDays;
    }
}
