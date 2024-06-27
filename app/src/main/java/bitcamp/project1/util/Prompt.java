package bitcamp.project1.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Prompt {

    private Scanner scanner = new Scanner(System.in);

    public int getInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력하세요.");
            }
        }
    }

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public LocalDate getDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("올바른 날짜 형식으로 입력하세요 (YYYY-MM-DD).");
            }
        }
    }
}
