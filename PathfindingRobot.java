import java.util.*;

public class PathfindingRobot {

    static ArrayList<ArrayList<Integer>> map;
    static int startRow = -1, startCol = -1;

    public static void main(String[] args) {
        runPathfinding();
    }

    public static void runPathfinding() {
        // Sample input - replace with any test array from Client
        map = new ArrayList<>();
        map.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 0, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 1, 0)));
        map.add(new ArrayList<>(Arrays.asList(9, 0, 0, 1, 0, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 2, 0, 0, 0)));
        map.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1)));

        int rows = map.size(), cols = map.get(0).size();
        int[][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = map.get(i).get(j) == 1 ? 1 : 0;

        locateStartingPoint(grid);

        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];

        if (startRow != -1 && dfs(grid, startRow, startCol, path, visited)) {
            System.out.println(path);
            printPath(path, rows, cols);
        } else {
            System.out.println("No valid path found.");
        }
    }

    public static void locateStartingPoint(int[][] map) {
        int rows = map.length, cols = map[0].length;
        for (int i = 0; i < rows; i++) {
            if (map[i][0] == 1) { startRow = i; startCol = 0; return; }
            if (map[i][cols - 1] == 1) { startRow = i; startCol = cols - 1; return; }
        }
        for (int j = 0; j < cols; j++) {
            if (map[0][j] == 1) { startRow = 0; startCol = j; return; }
            if (map[rows - 1][j] == 1) { startRow = rows - 1; startCol = j; return; }
        }
    }

    public static boolean dfs(int[][] map, int r, int c, ArrayList<String> path, boolean[][] visited) {
        if (r < 0 || c < 0 || r >= map.length || c >= map[0].length || map[r][c] != 1 || visited[r][c])
            return false;

        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        if ((r != startRow || c != startCol) && isWall(r, c, map)) return true;

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] d : dirs)
            if (dfs(map, r + d[0], c + d[1], path, visited)) return true;

        path.remove(path.size() - 1);
        return false;
    }

    public static boolean isWall(int r, int c, int[][] map) {
        return r == 0 || r == map.length - 1 || c == 0 || c == map[0].length - 1;
    }

    public static void printPath(ArrayList<String> path, int rows, int cols) {
        String[][] display = new String[rows][cols];
        for (String coord : path) {
            String[] parts = coord.replace("A[", "").replace("]", "").split("\\[|\\]");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            display[r][c] = "1";
        }

        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                System.out.print((display[i][j] != null ? display[i][j] : " ") + " , ");
            }
            System.out.println("]");
        }
    }
}