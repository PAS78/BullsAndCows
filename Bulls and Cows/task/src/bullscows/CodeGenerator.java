package bullscows;

import java.util.Random;
import java.util.Scanner;

public class CodeGenerator {
    private final static String CHARACTERS_ALLOWED = "0123456789abcdefghijklmnopqrstuvwxyz";

    private SecretCode secretCode;
    private int codeLength;
    private int scope;

    public CodeGenerator() {
        runGenerator();
    }

    public SecretCode getSecretCode() {
        return secretCode;
    }

    public int getCodeLength() {
        return codeLength;
    }

    private void printScope() {
        StringBuilder message = new StringBuilder("The secret is prepared: ");
        message.append("*".repeat(Math.max(0, codeLength)));

        if (scope <= 10) {
            message.append(" (0-").append(CHARACTERS_ALLOWED.charAt(scope - 1)).append(").");
        } else if (scope <= 36) {
            message.append(" (0-9, a-").append(CHARACTERS_ALLOWED.charAt(scope - 1)).append(").");
        }

        message.append("\nOkay, let's start a game!");
        System.out.println(message);
    }

    private void runGenerator() {
        System.out.println("Input the length of the secret code:");
        codeLength = input();

        if (codeLength != -1) {
            if (!isLengthValid()) {
                System.out.println("Error: maximum length of the secret code 36.");
                codeLength = -1;
            } else {
                System.out.println("Input the number of possible symbols in the code:");
                scope = input();

                if (scope == -1) {
                    codeLength = -1;
                } else if (scope < codeLength) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", codeLength, scope);
                    codeLength = -1;
                } else if (!isScopeValid()) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    codeLength = -1;
                } else {
                    secretCode = generateNumber();
                    printScope();
                }
            }

        }
    }

    private int input() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        if (string.matches("[0-9]+")) {
            return Integer.parseInt(string);
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.", string);
            return -1;
        }
    }

    private boolean isLengthValid() {
        return codeLength > 0 && codeLength <= 36;
    }

    private boolean isScopeValid() {
        return scope > 0 && scope <= 36;
    }

    private SecretCode generateNumber() {
        Random random = new Random();
        StringBuilder secretCode = new StringBuilder();
        StringBuilder charScope = new StringBuilder(CHARACTERS_ALLOWED.substring(0, scope));

        for (int i = 0; i < codeLength; i++) {
            int number = random.nextInt(charScope.length());
            secretCode.append(charScope.charAt(number));
            charScope.deleteCharAt(number);
        }

        if (secretCode.length() < codeLength) {
            generateNumber();
        }
        return new SecretCode(secretCode.toString());
    }

}