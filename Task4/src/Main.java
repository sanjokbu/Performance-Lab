import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		int sum = 0;
		int avg;
		int countIterator = 0;

		ArrayList<Integer> numList;

		try {

			List<String> list = Files.readAllLines(Path.of(args[0]));

			numList = new ArrayList<>();

			for (String s: list) {
				numList.add(Integer.parseInt(s));
				sum += Integer.parseInt(s);
			}

			avg = sum / numList.size();

			for (int num : numList) {
				countIterator += Math.abs(num - avg);
			}

			System.out.println("Минимальное количество ходов: " + countIterator);

		} catch (IOException e) {

			System.out.println("Файл не найден");

		}
	}
}