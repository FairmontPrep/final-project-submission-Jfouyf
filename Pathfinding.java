import java.util.*;

public class Pathfinding {
   
    static ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0)),
        new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(0, 0, 0, 1, 2, 0, 0, 0)),
        new ArrayList<>(Arrays.asList(1, 0, 0, 0, 1, 1, 1, 1))
    ));

    private static void printPathAndGraph() {

        List<Pos> path = findPathPositions();


        List<String> coords = new ArrayList<>();
        for (Pos p : path) {
            coords.add("A[" + p.r + "][" + p.c + "]");
        }
        System.out.println("graph：");
        System.out.println(coords);


        int n = A.size(), m = A.get(0).size();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(Collections.nCopies(m, 0)));
        }

        for (Pos p : path) {
            graph.get(p.r).set(p.c, 1);
        }

        System.out.println("\nonly contains 1：");
        for (ArrayList<Integer> row : graph) {
            System.out.println(row);
        }
    }


    private static List<Pos> findPathPositions() {
        int n = A.size(), m = A.get(0).size();
        List<Pos> ends = new ArrayList<>();

 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A.get(i).get(j) == 1) {
                    int cnt = 0;
                    for (int[] d : DIR) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m && A.get(ni).get(nj) == 1) {
                            cnt++;
                        }
                    }
                    if (cnt == 1) ends.add(new Pos(i, j));
                }
            }
        }


        List<Pos> best = new ArrayList<>();
        for (Pos start : ends) {
            Pos prev = null, cur = start;
            List<Pos> path = new ArrayList<>();
            while (cur != null) {
                path.add(cur);
                Pos next = null;
                for (int[] d : DIR) {
                    int ni = cur.r + d[0], nj = cur.c + d[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && A.get(ni).get(nj) == 1) {
                        if (prev == null || !(prev.r == ni && prev.c == nj)) {
                            next = new Pos(ni, nj);
                            break;
                        }
                    }
                }
                prev = cur;
                cur = next;
            }
            if (path.size() > best.size()) {
                best = path;
            }
        }
        return best;
    }


    private static final int[][] DIR = {
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };


    private static class Pos {
        int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
    }
    public static void main(String[] args) {
        printPathAndGraph();
    }
}
