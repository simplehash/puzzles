package puzzles;

public class MaxProfit {
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}

		// Assumes short selling is not allowed and you can only buy once and
		// sell once
		int currentMaxProfit = 0;
		int currentSellPrice = prices[prices.length - 1];
		int i = prices.length - 1;
		while (i > 0) {
			if (prices[i - 1] > currentSellPrice) {
				currentSellPrice = prices[i - 1];
			} else {
				currentMaxProfit = Math.max(currentMaxProfit, currentSellPrice - prices[i - 1]);
			}
			i--;
		}
		return currentMaxProfit;
	}
}
