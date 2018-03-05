import java.util.Scanner;

public class NumberTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        String path = scanner.nextLine();
        new NumberTree().solve(height, path.trim());
    }

    private void solve(int height, String path) {
        int numberOfNodes = 0;
        for (int i = 0; i <= height; i++)
            numberOfNodes += (numberOfNodes + 1);

        // label the nodes backwards compared to the description
        // the root node is 1
        int node = 1;

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'L')
                node *= 2;
            else if (path.charAt(i) == 'R')
                node = node * 2 + 1;
        }
        // calculate the node label given in the description
        int result = numberOfNodes - node + 1;
        System.out.println(result);
    }
}
