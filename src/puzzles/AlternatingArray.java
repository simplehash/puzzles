package puzzles;

public class AlternatingArray {
    // Make every odd indexed element bigger than the elements directly adjacent
    // to it
    private int[] list;

    public AlternatingArray(int[] list) {
        if (list != null && list.length > 0) {
            this.list = list;
        }
    }

    public int[] calculate() {
        for (int i = 0; i < list.length - 1; i++) {
            // If odd index and smaller than next, or if even index and greater
            // than next
            if ((i % 2 != 0 && list[i] <= list[i + 1]) || i % 2 == 0 && list[i] >= list[i + 1]) {
                int temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
            }
        }
        return list;
    }
}
