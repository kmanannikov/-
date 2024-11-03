import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserDataApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");

        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        // Проверка количества введенных данных
        if (parts.length != 6) {
            System.err.println("Ошибка: неверное количество данных. Ожидается 6 элементов.");
            return;
        }

        try {
            // Парсинг данных
            String lastName = validateName(parts[0]);
            String firstName = validateName(parts[1]);
            String middleName = validateName(parts[2]);
            LocalDate birthDate = validateDate(parts[3]);
            long phoneNumber = validatePhone(parts[4]);
            char gender = validateGender(parts[5]);

            // Форматирование строки для записи
            String data = String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate, phoneNumber, gender);

            // Запись данных в файл
            writeToFile(lastName, data);

            System.out.println("Данные успешно сохранены.");

        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.err.println("Ошибка валидации данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }

    private static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя, фамилия или отчество не может быть пустым.");
        }
        return name;
    }

    private static LocalDate validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    private static long validatePhone(String phone) {
        try {
            return Long.parseLong(phone);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер телефона должен быть целым числом.");
        }
    }

    private static char validateGender(String gender) {
        if (gender.length() == 1 && (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m"))) {
            return gender.toLowerCase().charAt(0);
        } else {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
        }
    }

    private static void writeToFile(String lastName, String data) throws IOException {
        Path filePath = Path.of(lastName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}