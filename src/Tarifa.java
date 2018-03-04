import java.util.Scanner;

public class Tarifa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int n = scanner.nextInt();
        int p[] = new int[n];
        for (int i = 0; i < p.length; i++)
            p[i] = scanner.nextInt();
        Tarifa tarifa = new Tarifa();
        tarifa.solve(x, n, p);
    }

    private void solve(int x, int n, int[] p) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += p[i];
        int total = (n + 1) * x;
        System.out.println(total - sum);
    }
}
