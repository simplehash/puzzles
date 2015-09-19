package puzzles;

import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSumEqualTo {
    public static List<Integer> go(int[] numbers, int sum) {
        if (numbers == null) {
            return null;
        }
        List<Integer> answer = new ArrayList<>();
        if (numbers.length > 0) {
            int currentSum = numbers[0];
            int first = 0;
            int last = 0;
            while (first <= last && last < numbers.length) {
                if (currentSum == sum) {
                    break;
                } else if (currentSum > sum) {
                    sum -= numbers[first];
                    first++;
                } else if (currentSum < sum) {
                    last++;
                    sum += numbers[last];
                }
            }
        }
        return answer;
    }
}
