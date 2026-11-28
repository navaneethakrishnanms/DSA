import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cntFactor = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            cntFactor[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] cntMulti = new int[n + 1];
        TreeMap<Long, Long> deals = new TreeMap<>();

        for (int[] item : items) {
            long factor = item[0];
            long price = item[1];

            if (price >= minPrice * 2) {
                continue;
            }

            if (cntMulti[(int) factor] == 0) {
                for (int j = (int) factor; j <= n; j += factor) {
                    cntMulti[(int) factor] += cntFactor[j];
                }
            }

            long freeCopies = cntMulti[(int) factor] - 1;
            if (freeCopies > 0) {
                deals.merge(price, freeCopies, Long::sum);
            }
        }

        long totalItems = 0;
        long remBudget = budget;

        for (Map.Entry<Long, Long> entry : deals.entrySet()) {
            long price = entry.getKey();
            long availableDeals = entry.getValue();

            long canBuy = Math.min(availableDeals, remBudget / price);
            totalItems += canBuy * 2;
            remBudget -= canBuy * price;

            if (remBudget < price) {
                break;
            }
        }

        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}
