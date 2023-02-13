import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double sqrt = sqrt(pow(b, 2) - 4 * a * c);
        double x1 = (-b + sqrt) / (2 * a);
        double x2 = (-b - sqrt) / (2 * a);

        System.out.print(Math.min(x1, x2) + " ");
        System.out.print(Math.max(x1, x2));
    }
}