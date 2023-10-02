import javax.swing.plaf.SplitPaneUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static class SegmentTree {
        static Node[] st;
        static long[] longArr;
        double height;
        int length;
        static int longArrIndex = 0;
        long sum;

        public SegmentTree(long[] longArr) {
            SegmentTree.longArr = longArr;
            this.height = Math.ceil(Math.log(longArr.length) / Math.log(2));
            this.length = (int) Math.pow(2, height+1);
            // sumArr = new long[length];
            st = new Node[length];

            int start = 0;
            int end = longArr.length - 1;
            setSum(start, end, 1);
        }

        public void setSum(int s, int e, int i) {
            if (s == e) {
                st[i] = new Node(s, e, longArr[longArrIndex]);
                // System.out.println(i+": "+st[i].start+"~"+st[i].end+"=>"+st[i].sum);
                longArrIndex++;
            }
            else {
                int mid = (s+e)/2;
                setSum(s, mid, i*2);
                setSum(mid + 1, e, i*2+1);
                st[i] = new Node(s, e, st[i * 2].sum + st[i * 2 + 1].sum);
                // System.out.println(i+": "+st[i].start+"~"+st[i].end+"=>"+st[i].sum);

            }
        }

        public long resultSum(int s, int e) {
            sum = 0;
            calcSum(s, e, 1);
            return (long) sum;
        }

        public void calcSum(int s, int e, int i) {
            if (s==st[i].start && e==st[i].end) {
                sum += st[i].sum;
            }
            else {
                int mid = (st[i].start+st[i].end)/2;
                if(e <= mid) {
                    calcSum(s, e, i*2);
                }
                else if (s <= mid) {
                    calcSum(s, mid, i*2);
                    calcSum(mid +1, e, i*2+1);
                }
                else {
                    calcSum(s, e, i*2+1);
                }
            }
        }

        // b번째 => n = b-1;
        public void change(int n, long x, int i) {
            if (st[i].start == n && st[i].end == n) {
                st[i].sum = x;
            }
            else {
                int mid = (st[i].start + st[i].end)/2;
                if (n <= mid) {
                    change(n, x, i*2);
                }
                else {
                    change(n, x, i*2+1);
                }
                st[i].sum = st[i*2].sum + st[i*2+1].sum;
            }


        }

        public static class Node {
            int start;
            int end;
            long sum;

            public Node(int start, int end, long sum){
                this.start = start;
                this.end = end;
                this.sum =sum;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n, m, k, a, b;
        long[] longArr;
        String[] strArr;

        // 첫 줄에서 입력받는 수들
        strArr = br.readLine().split(" ");
        n = Integer.parseInt(strArr[0]);    // 수의 개수
        m = Integer.parseInt(strArr[1]);    // 수의 변경이 일어나는 횟수
        k = Integer.parseInt(strArr[2]);    // 구간 합을 구하는 횟수
        longArr = new long[n];
        // 수 입력
        for (int i = 0; i < n; i++) {
            longArr[i] = Long.parseLong(br.readLine());
        }
        SegmentTree st = new SegmentTree(longArr);

        while (m>0 || k>0) {
            strArr = br.readLine().split(" ");
            a = Integer.parseInt(strArr[0]);    // 수 변경(=1) or 구간 합(=2)
            b = Integer.parseInt(strArr[1]);    // b번째 수를 or b번째 부터

            if (a == 1) {
                long c = Long.parseLong(strArr[2]);    // c로 변경
                st.change(b-1, c, 1);
                m--;
            }
            else if (a == 2) {
                int c = Integer.parseInt(strArr[2]);    // c번째 까지의 합
                sb.append(st.resultSum(b-1, c-1)).append("\n");
                k--;
            }
        }
        System.out.printf(String.valueOf(sb));

    }
}