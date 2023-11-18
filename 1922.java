import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static int[] parent;
    public static class Edge {
        int start;
        int end;
        int weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    public static class WeightAscending implements Comparator<Edge>{
        public int compare(Edge e1,  Edge e2){
            Integer w1 = e1.weight;
            Integer w2 = e2.weight;

            return w1.compareTo(w2);
        }
    }

    public static int findRoot(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = findRoot(parent[n]);
    }

    public static void union(int a, int b) {
        if (findRoot(a) != findRoot(b)) {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] command;

        int n = Integer.parseInt(br.readLine());    // 컴퓨터 수
        int m = Integer.parseInt(br.readLine());    // 선의 수

        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> graph = new ArrayList<>();

        int a;
        int b;
        int c;
        for (int i = 0; i < m; i++) {
            command = br.readLine().split(" ");
            a = Integer.parseInt(command[0]);
            b = Integer.parseInt(command[1]);
            c = Integer.parseInt(command[2]);

            graph.add(new Edge(a, b, c));
        }

        WeightAscending weightAscending = new WeightAscending();
        graph.sort(weightAscending);

        int selectedEdge = 0;
        int weightSum = 0;

        for (int i = 0; i < m; i++) {
            int rs = findRoot(graph.get(i).start);
            int re = findRoot(graph.get(i).end);
            if (rs != re) {
                weightSum += graph.get(i).weight;
                selectedEdge++;
                union(rs, re);
            }
            if (selectedEdge == n -1) {
                break;
            }
        }
        System.out.println(weightSum);
    }
}