import java.util.Scanner;

public class Main {
    static int m;  // 가져가려고 하는 나무의 길이
    static int[] a;    // 나무 길이의 배열
    static int height;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = 0;    // 가장 긴 나무 토막 길이

        int n = sc.nextInt();  // 나무의 수
        m = sc.nextInt();

        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            if(a[i] > max){
                max = a[i];
            }
        }

        int start = 0;
        int end = max;

        while (start + 1 <= end) {
            int mid = (start + end) / 2;
            if(checkSum(mid)){
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println((start + end) / 2);
    }
    public static boolean checkSum(int h){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] > h) {
                sum += a[i] - h;
            }
        }
        if(sum>=m) {return true;}
        else {return false;}
    }
}