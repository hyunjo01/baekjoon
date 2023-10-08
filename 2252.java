import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static ArrayList<LinkedList<Integer>> graph = new ArrayList<>();
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    // a -> b (a가 우선순위)
    static public void addEdge(int a, int b) {
        graph.get(a).add(b);
        inDegree[b]++;
    }

    static public void topoSort() {
        boolean[] isVisited = new boolean[inDegree.length];
        Queue<Integer> queue = new LinkedList<>();
        int count = inDegree.length - 1; // n

        while (count > 0) {
            for (int i = 1; i < inDegree.length; i++) {
                if (inDegree[i] == 0 && !isVisited[i]) {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int element: graph.get(node)) {
                    inDegree[element]--;
                }
                isVisited[node] = true;
                sb.append(node).append(" ");
                count--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] command;

        command = br.readLine().split(" ");

        int n = Integer.parseInt(command[0]);   // 학생 n명
        int m = Integer.parseInt(command[1]);   // 비교 회수

        inDegree = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
            inDegree[i] = 0;
        }

        int a;
        int b;

        while (m-- > 0) {
            command = br.readLine().split(" ");
            a = Integer.parseInt(command[0]);
            b = Integer.parseInt(command[1]);

            addEdge(a, b);
        }

        topoSort();
        System.out.println(sb);
    }
}