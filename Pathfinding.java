import java.util.*;

public class Pathfinding {
   
    static int[][] A = {
        {1,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,1,0,0},
        {9,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,1,2,0,0,0,0,0,0},
        {1,0,0,1,1,1,1,1,1,1,1}
    };

    public static void main(String[] args) {
       
        printPathAndGraph();
    }

    private static void printPathAndGraph() {
       
        List<Pos> path = findPathPositions();

        
        List<String> formatted = new ArrayList<>();
        for (Pos p : path) {
            formatted.add(format(p));
        }
        System.out.println("Graph:");
        System.out.println(formatted);

        
        int n = A.length, m = A[0].length;
        int[][] graph = new int[n][m];
        for (Pos p : path) {
            graph[p.r][p.c] = 1;
        }

        
        System.out.println("\nonly contains 1");
        for (int i = 0; i < n; i++) {
           
            System.out.println(Arrays.toString(graph[i]));
        }
    }

 
    private static List<Pos> findPathPositions() {
        int n = A.length, m = A[0].length;
        List<Pos> ends = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) if (A[i][j] == 1) {
                int cnt = 0;
                for (int[] d : DIR) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni>=0 && ni<n && nj>=0 && nj<m && A[ni][nj]==1) cnt++;
                }
                if (cnt == 1) ends.add(new Pos(i,j));
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
                    if (ni>=0 && ni<n && nj>=0 && nj<m && A[ni][nj]==1) {
                        if (prev == null || !(prev.r==ni && prev.c==nj)) {
                            next = new Pos(ni,nj);
                            break;
                        }
                    }
                }
                prev = cur;
                cur = next;
            }
            if (path.size() > best.size()) best = path;
        }
        return best;
    }

    private static final int[][] DIR = {
        {-1,0}, {0,1}, {1,0}, {0,-1}
    };

    private static class Pos {
        int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
    }

    private static String format(Pos p) {
        return "A[" + p.r + "][" + p.c + "]";
    }
}

