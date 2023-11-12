import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long k;

        long[] arr = new long[n+1];

        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= n; i++){
            arr[i] = arr[i-2] + arr[i-1];
        }
        k = arr[n];

        System.out.println(k);
    }
}