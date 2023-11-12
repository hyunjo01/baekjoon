import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;  // 배열 크기
        int m;  // 원하는 합의 수
        int[] a;

        int count = 0;  // 경우의 수
        int sum = 0;    // 합에 이용되는 변수

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++) {
                sum += a[j];
                if(sum >= m){
                    if(sum == m){
                        count++;
                    }
                    break;
                }
            }
            sum = 0;
        }
        System.out.println(count);
    }

}
