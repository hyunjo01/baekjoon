import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;
    public static int findRoot(int n){
        if (parent[n] == n) {
            return n;
        }
        else {
            return parent[n] = findRoot(parent[n]);
        }
    }

    public static void union(int a, int b) {
        if (findRoot(a) != findRoot(b)) {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] command;

        command = br.readLine().split(" ");
        int n = Integer.parseInt(command[0]);   // 0 ~ n 까지의 집합
        int m = Integer.parseInt(command[1]);   // 연산의 개수

        int o;  // 0: 합치기  1: 포함 유무
        int a;
        int b;
        int p1;
        int p2;

        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        while (m-- > 0) {
            command = br.readLine().split(" ");
            o = Integer.parseInt(command[0]);
            a = Integer.parseInt(command[1]);
            b = Integer.parseInt(command[2]);

            if (a==b) {
                if (o==1) {
                    sb.append("YES").append("\n");
                }
            }
            else {
                p1 = findRoot(a);
                p2 = findRoot(b);

                // a, b 집합 합침
                if (o==0) {
                    union(p1,p2);
                }

                // a, b가 한 집합에 있는 지 => yes or no
                else {
                    if (p1 == p2) {
                        sb.append("YES").append("\n");
                    }
                    else {
                        sb.append("NO").append("\n");
                    }
                }
            }
        }

        System.out.printf(String.valueOf(sb));
    }
}