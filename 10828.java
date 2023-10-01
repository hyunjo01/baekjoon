import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n =  Integer.parseInt(br.readLine());

        int[] stack = new int[n];
        int length = 0;

        String command;

        while (n > 0) {
            command = br.readLine();

            if (command.equals("pop")) {
                if(length == 0) {
                    sb.append(-1);
                }
                else {
                    length--;
                    sb.append(stack[length]);
                }
                sb.append('\n');
            }
            else if (command.equals("size")) {
                sb.append(length);
                sb.append('\n');
            }
            else if (command.equals("empty")) {
                if (length == 0) {
                    sb.append(1);
                }
                else {
                    sb.append(0);
                }
                sb.append('\n');
            }
            else if (command.equals("top")) {
                if (length == 0) {
                    sb.append(-1);
                }
                else {
                    sb.append(stack[length-1]);
                }
                sb.append('\n');
            }
            else {
                String numString = command.substring(command.lastIndexOf(" ")+1);
                int x = Integer.parseInt(numString);
                stack[length] = x;
                length++;
            }

            n--;
        }

        System.out.println(sb);

    }
}