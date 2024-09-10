import java.util.Scanner;

public class CaesarEncryption {
    private String alphabet;
    private String sourceText;
    private int shift;
    private String encryption;

    public void startProgram() {
        chooseAlphabet();
        setSourceText();
        processEncryption();
        System.out.println("Result: " + this.encryption);
    }

    public void chooseAlphabet() {
        System.out.println("Choose the alphabet type:");
        System.out.println("1 - Русский\n2 - English");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n > 2 || n < 1) {
            System.out.println("Incorrect data");
            System.exit(0);
        }
        if (n == 1) {
            this.alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        } else {
            this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
    }

    public void setSourceText() {
        System.out.println("Enter the text that you want to encrypt:");
        Scanner scanner = new Scanner(System.in);
        this.sourceText = scanner.nextLine();
    }

    public void processEncryption() {
        this.setShift();
        String encryptionText = "";
        for (char c : this.sourceText.toCharArray()) {
            if (c == ' ') {
                encryptionText += c;
                continue;
            }
            int index = 0;
            for (int i = 0; i < this.alphabet.length(); i++) {
                if (c == this.alphabet.charAt(i)) {
                    index = (i + this.shift) % this.alphabet.length();
                    break;
                }
            }
            encryptionText += this.alphabet.charAt(index);
        }
        this.encryption = encryptionText;
    }

    public void setShift() {
        System.out.print("Enter shift: ");
        Scanner scanner = new Scanner(System.in);
        this.shift = scanner.nextInt();
        while (this.shift > this.alphabet.length() - 1) {
            System.out.println("Going beyond the boundaries of the alphabet");
            this.shift = scanner.nextInt();
        }
    }

    public void decoding() {
        for (int i = this.alphabet.length(); i > 0; i--) {
            String decode_str = "";
            for (int j = 0; j < this.encryption.length(); j++) {
                if (this.encryption.charAt(j) == ' ') {
                    decode_str += ' ';
                    continue;
                }
                int index_alphabet = this.alphabet.indexOf(this.encryption.charAt(j));
                decode_str += this.alphabet.charAt((index_alphabet + i - 1)%this.alphabet.length());
            }
            System.out.println(decode_str);
            if (decode_str.equals(this.sourceText)) {
                System.out.println("Shift: " + (this.alphabet.length() - (i - 1)));
                return;
            }
        }
    }
}
