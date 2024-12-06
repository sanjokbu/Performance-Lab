import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Введите размер массива:");
		int n = scanner.nextInt();

		System.out.println("Введите длину обхода:");
		int m = scanner.nextInt();

		StringBuilder path = new StringBuilder();

		int i = 1;
		int j = 1;

		while (path.isEmpty() || i != 1 || j != m) {

			if (path.isEmpty()) {
				path.append(1);
			}

			if (j == m) {
				path.append(i);
				j = 1;
				continue;
			} else {
				j++;
			}

			if (i != n) {
				i++;
			} else {
				i = 1;
			}

		}

		System.out.println("Полученный путь: " + path);
	}
}