import java.util.*;

public class PathfindingRobot {


    static int[][] A = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void main(String[] args) {
        ArrayList<String> answerList = findPath(A);
        System.out.println(answerList);
        printPath(answerList, A.length, A[0].length);
    }


    public static ArrayList<String> findPath(int[][] map) {
        ArrayList<String> path = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    if (dfs(map, i, j, path, visited)) {
                        return path;
                    } else {
                        path.clear();
                    }
                }
            }
        }
        return path;
    }


    public static boolean dfs(int[][] map, int r, int c, ArrayList<String> path, boolean[][] visited) {
        if (r < 0 || c < 0 || r >= map.length || c >= map[0].length || map[r][c] != 1 || visited[r][c]) {
            return false;
        }
        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        if (isEndOfPath(map, r, c, visited)) return true;

        if (dfs(map, r + 1, c, path, visited)) return true;
        if (dfs(map, r - 1, c, path, visited)) return true;
        if (dfs(map, r, c + 1, path, visited)) return true;
        if (dfs(map, r, c - 1, path, visited)) return true;

        // backtrack
        path.remove(path.size() - 1);
        return false;
    }

    public static boolean isEndOfPath(int[][] map, int r, int c, boolean[][] visited) {
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < map.length && nc < map[0].length) {
                if (map[nr][nc] == 1 && !visited[nr][nc]) count++;
            }
        }
        return count == 0;
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
