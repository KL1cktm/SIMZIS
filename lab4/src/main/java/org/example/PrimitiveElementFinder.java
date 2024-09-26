package org.example;

import java.util.HashSet;
import java.util.Set;

public class PrimitiveElementFinder {

    public static int modularExponentiation(int base, int exp, int mod) {
        int result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            exp = exp / 2;
            base = (base * base) % mod;
        }
        return result;
    }

    public static boolean isPrimitiveElement(int g, int p) {
        Set<Integer> values = new HashSet<>();
        for (int i = 1; i < p; i++) {
            int val = modularExponentiation(g, i, p);
            if (values.contains(val)) {
                return false;
            }
            values.add(val);
        }
        return values.size() == (p - 1);
    }

    public static void main(String[] args) {
        int p = 1123;
        System.out.println("Поиск примитивного элемента для P = " + p);

        for (int g = 2; g < p; g++) {
            if (isPrimitiveElement(g, p)) {
                System.out.println("Найден примитивный элемент: g = " + g);
                break;
            }
        }
    }
}
