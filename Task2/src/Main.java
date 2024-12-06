import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		float centerX;
		float centerY;
		double radius;

		List<String> list = List.of();
		List<String> coordinatesList = List.of();

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Введите путь к файлу с координатами центра и радиусом:");
			Path path = Path.of(scanner.nextLine());
			list = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("Файл с координатами центра и радиусом не найден");
			System.exit(0);
		}

		String[] str = list.getFirst().split(" ");
		centerX = Float.parseFloat(str[0]);
		centerY = Float.parseFloat(str[1]);

		radius = Double.parseDouble(list.getLast());

		try {
			System.out.println("Введите путь к координатами точек:");
			Path path = Path.of(scanner.nextLine());
			coordinatesList = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("Фаил с координатами точек не найден");
			System.exit( 0);
		}

		for (String s: coordinatesList) {

			str = s.split(" ");

			float x0 = Float.parseFloat(str[0]);
			float y0 = Float.parseFloat(str[1]);

			float x1 = x0 - centerX;
			float y1 = y0 - centerY;

			double c = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));

			if (c == radius ) {
				System.out.println("0");
			} else if (c < radius) {
				System.out.println("1");
			} else {
				System.out.println("2");
			}

		}

		scanner.close();

	}
}