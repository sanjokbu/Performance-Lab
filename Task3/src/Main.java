import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String jsonValues = "";
		StringBuilder jsonReport = null;

		try {

			System.out.println("Введите путь к файлу со структурой отчета:");
			String jsonTests = Files.readString(Path.of(scanner.nextLine()));

			System.out.println("Введите путь к файлу с результатами тестов:");
			jsonValues = Files.readString(Path.of(scanner.nextLine()));

			jsonReport = new StringBuilder(jsonTests);

		} catch (IOException e) {

			System.out.println("Файл не найден");
			System.exit(0);

		}

		HashMap<String, String> idValues = new HashMap<>();

		String regex_id_start = "\"id\": ";
		Pattern pattern1 = Pattern.compile(regex_id_start);
		Matcher matcher1 = pattern1.matcher(jsonValues);

		String regex_id_end= "\"id\":\\s*[0-9]+";
		Pattern pattern2 = Pattern.compile(regex_id_end);
		Matcher matcher2 = pattern2.matcher(jsonValues);

		String regex_value_start = "\"value\":\\s*\"";
		Pattern pattern3 = Pattern.compile(regex_value_start);
		Matcher matcher3 = pattern3.matcher(jsonValues);

		String regex_value_end= "\"value\":\\s*\"[A-z]*";
		Pattern pattern4 = Pattern.compile(regex_value_end);
		Matcher matcher4 = pattern4.matcher(jsonValues);

		while (matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find()) {
			idValues.put(jsonValues.substring(matcher1.end(), matcher2.end()), jsonValues.substring(matcher3.end(), matcher4.end()));
		}

			System.out.println("Полученные id и value:");
			System.out.println(idValues);


		for (String s: idValues.keySet()) {

			regex_value_start = "\"id\":\\s*" + s + ",\\s*\"title\":\\s*\".*\",\\s*\"value\":\\s*\"";
			Pattern pattern5 = Pattern.compile(regex_value_start);
			Matcher matcher5 = pattern5.matcher(jsonReport);

			if (matcher5.find()) {
				jsonReport.insert(matcher5.end(), idValues.get(s));
			}

		}

		try {

			System.out.println("Введите путь куда сохранить файл отчета:");
			Files.writeString(Path.of(scanner.nextLine()), jsonReport);

		} catch (IOException e) {

			System.out.println("Не верный путь к файлу");
			System.exit(0);

		}

		scanner.close();

	}
}