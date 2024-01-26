package exemple.backend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class RandomValueUtil {
    public static String getRandomNumberString(int bound) {
        Random rnd = new Random();
        int number = rnd.nextInt(bound);
        return String.format("%06d", number);
    }

    public static String randomPasswordGenerator(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
    }

    public static LocalDateTime firstDayMonth() {
        return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1).atStartOfDay();
    }

    public static LocalDateTime lastDayMonth() {
        return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth()).atTime(23, 59, 59);
    }

}
