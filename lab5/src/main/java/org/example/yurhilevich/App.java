package org.example.yurhilevich;

import java.math.BigInteger;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        Random random = new Random();
        RSAMethod method = new RSAMethod();
        for (int i=1;i<11;i++) {
            System.out.println("Тест " + i);
            method.setValueM(Math.abs(random.nextInt()%1000000));
            System.out.println("\nRSA метод\n");
            method.encryption();
            method.RSAEncryption();
            BigInteger output = method.RSADecryption();
            System.out.println("Результат расшифровки RSA: " + "\u001B[33m" +output + "\u001B[0m");
            System.out.println("\nDS метод\n");
            method.digitalSignature();
            method.createDigitalSignature();
            BigInteger output2 = method.signatureDecryption();
            System.out.println("Результат расшифровки DS: " + "\u001B[33m" +output2 + "\u001B[0m");
            System.out.println("\n\n");
        }
    }
}
