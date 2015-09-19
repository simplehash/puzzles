package puzzles;

public class LargestConnectedComponent {
    private static boolean[][] visited;
    private static int[][] map;

    public static int go(int[][] array, int cellType) {
        if (array == null) {
            return -1;
        }
        if (array.length == 0) {
            return 0;
        }
        map = array;

        // Auto initialized to false
        visited = new boolean[array.length][array[0].length];
        int largestComponent = 0;

        for (int currentRow = 0; currentRow < array.length; currentRow++) {
            for (int currentColumn = 0; currentColumn < array[currentRow].length; currentColumn++) {
                largestComponent = Math.max(largestComponent, find(currentRow, currentColumn, 0, cellType));
            }
        }
        return largestComponent;
    }

    private static int find(int row, int column, int currentMaxComponent, int cellType) {
        if (visited[row][column] || map[row][column] != cellType) {
            return 0;
        }
        currentMaxComponent++; // Account for current cell

        // Recurse up down left right
        if (row > 0) {
            currentMaxComponent += find(row - 1, column, currentMaxComponent, cellType);
        }
        if (row < map.length - 1) {
            currentMaxComponent += find(row + 1, column, currentMaxComponent, cellType);
        }
        if (column > 0) {
            currentMaxComponent += find(row, column - 1, currentMaxComponent, cellType);
        }
        if (column < map[row].length - 1) {
            currentMaxComponent += find(row, column + 1, currentMaxComponent, cellType);
        }
        return currentMaxComponent;
    }
}
