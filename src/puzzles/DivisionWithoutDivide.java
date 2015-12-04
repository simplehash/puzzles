package puzzles;

public class DivisionWithoutDivide {
	public static double divide(int numerator, int denominator) {
		if (denominator == 1) {
			return numerator;
		}
		if (denominator == 0) {
			throw new IllegalArgumentException("Division by 0!");
		}

		boolean negative = false;
		// IFF 1 of the #s is negative, answer will be negative
		if (!(numerator < 0 && denominator < 0) && (numerator < 0 || denominator < 0)) {
			negative = true;
		}
		if (numerator < 0) {
			numerator = Math.abs(numerator);
		}
		if (denominator < 0) {
			denominator = Math.abs(denominator);
		}

		double quotient = 0;
		// Essentially binary search here
		while (quotient * denominator != numerator) {
			if (quotient * denominator > numerator) {
				quotient /= 2;
			} else if (quotient * denominator < numerator) {
				quotient += (numerator - quotient) / 2;
			} else {
				break;
			}
		}
		if (negative) {
			quotient *= -1;
		}
		return quotient;
	}
}
