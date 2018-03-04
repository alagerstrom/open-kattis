import java.util.Scanner;

public class QuadrantSelection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        QuadrantSelection quadrantSelection = new QuadrantSelection();
        quadrantSelection.solve(x, y);
    }

    private void solve(int x, int y) {
        if (x >= 0 && y >= 0)
            System.out.println(1);
        else if (x >= 0 && y < 0)
            System.out.println(4);
        else if (x < 0 && y >= 0)
            System.out.println(2);
        else
            System.out.println(3);
    }
}
