import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static class Tree {
        Node[] nodes;
        Node root;

        public Tree(Node[] nodes) {
            this.nodes = nodes;
            this.root = nodes[0];
        }

        public void preorder(Node node) {
            sb.append(node.node);
            if(node.left != null) {
                preorder(node.left);
            }
            if(node.right != null) {
                preorder(node.right);
            }
        }
        public void inorder(Node node) {
            if(node.left != null) {
                inorder(node.left);
            }
            sb.append(node.node);
            if(node.right != null) {
                inorder(node.right);
            }
        }
        public void postorder(Node node) {
            if(node.left != null) {
                postorder(node.left);
            }
            if(node.right != null) {
                postorder(node.right);
            }
            sb.append(node.node);
        }


    }
    public static class Node {
        Object node;
        Node left;
        Node right;

        public Node(Object node) {
            this.node = node;
            this.left = null;
            this.right = null;
        }

        public void addLeft(Node left) {
            this.left = left;
        }
        public void addRight(Node right) {
            this.right = right;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node((char)(i+65));
        }

        for (int i = 0; i < n; i++) {
            char[] charArr = br.readLine().toCharArray();
            int root = (int)charArr[0]-65;
            int left = (int)charArr[2]-65;
            int right = (int)charArr[4]-65;

            if (left > 0) {
                nodes[root].addLeft(nodes[left]);
            }
            if (right > 0) {
                nodes[root].addRight(nodes[right]);
            }
        }

        Tree tree = new Tree(nodes);

        tree.preorder(tree.root);
        sb.append("\n");
        tree.inorder(tree.root);
        sb.append("\n");
        tree.postorder(tree.root);
        System.out.println(sb);
    }
}