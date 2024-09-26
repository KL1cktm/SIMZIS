package org.example;

import java.util.Scanner;

public class DiffieHellman {

    public static int modularExponentiation(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        int p = 1123;
        int g = 2;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число а: ");
        int a = scanner.nextInt();
        System.out.print("Введите число b: ");
        int b = scanner.nextInt();

        int A = modularExponentiation(g, a, p);
        int B = modularExponentiation(g, b, p);

        int secretA = modularExponentiation(B, a, p);
        int secretB = modularExponentiation(A, b, p);

        System.out.println("Приватный ключ Алисы: " + a);
        System.out.println("Публичный ключ Алисы (A): " + A);
        System.out.println("Приватный ключ Боба: " + b);
        System.out.println("Публичный ключ Боба (B): " + B);
        System.out.println("Общий секрет, вычисленный Алисой: " + secretA);
        System.out.println("Общий секрет, вычисленный Бобом: " + secretB);
    }
}

