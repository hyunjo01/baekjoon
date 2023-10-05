import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] isVisitedDfs;
    static boolean[] isVisitedBfs;
    static Queue<Integer> queue =  new LinkedList<>();

    static public void dfs(int i) {
        sb.append(i).append(" ");
        isVisitedDfs[i] = true;

        for(int j = 0; j < graph.get(i).size(); j++) {
            if (!isVisitedDfs[graph.get(i).get(j)]) {
                dfs(graph.get(i).get(j));
            }
        }
    }

    static public void bfs(int start) {
        int data;
        int temp;
        queue.add(start);

        while (!queue.isEmpty()) {
            data = queue.poll();
            if (!isVisitedBfs[data]) {
                sb.append(data).append(" ");
                isVisitedBfs[data] = true;
                for (int i = 0; i < graph.get(data).size(); i++) {
                    temp = graph.get(data).get(i);
                    if (!isVisitedBfs[temp]) {
                        queue.add(temp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] command = br.readLine().split(" ");
        int n = Integer.parseInt(command[0]);   // 정점의 개수
        int m = Integer.parseInt(command[1]);   // 간선의 개수
        int v = Integer.parseInt(command[2]);   // 탐색 시작 번호 (정점의 번호: 1~n)

        isVisitedDfs = new boolean[n+1]; // false: 기본 값
        isVisitedBfs = new boolean[n+1]; // false: 기본 값

        for (int i = 0; i <= n; i ++) {
            graph.add(new ArrayList<Integer>());
        }

        /* 간선 입력 */
        for (int i = 0; i < m; i++) {
            command = br.readLine().split(" ");
            int n1 = Integer.parseInt(command[0]);
            int n2 = Integer.parseInt(command[1]);

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        for (int i = 0; i <= n; i ++) {
            Collections.sort(graph.get(i));
        }

        dfs(v);
        sb.append("\n");
        bfs(v);
        System.out.println(sb);
    }
}