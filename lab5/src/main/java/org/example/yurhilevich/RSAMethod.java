package org.example.yurhilevich;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RSAMethod {
    private BigInteger p,q,n,fiN,d,m,c,s;
    private BigInteger e = BigInteger.valueOf(65537);

    public void generatePrimeNumbers(){
        int bitLength = 1024;
        SecureRandom random = new SecureRandom();
        this.p = BigInteger.probablePrime(bitLength,random);
        this.q = BigInteger.probablePrime(bitLength,random);

        while (this.p.equals(this.q)){
            this.q = BigInteger.probablePrime(bitLength,random);
        }

        System.out.println("Первое сгенерированное простое число: " + this.p);
        System.out.println("Второе сгенерированное простое число: " + this.q);
        System.out.println("Длина первого числа в битах: " + this.p.bitLength());
        System.out.println("Длина второго числа в битах: " + this.q.bitLength());
    }

    public void interimCalculations(){
        n = this.p.multiply(this.q);
        BigInteger pMinusOne,qMinusOne;
        pMinusOne = this.p.subtract(BigInteger.ONE);
        qMinusOne = this.q.subtract(BigInteger.ONE);

        this.fiN = pMinusOne.multiply(qMinusOne);
        this.d = this.e.modInverse(this.fiN);

        setValueM();
    }

    public void writeInFile(String filename,List<BigInteger> list){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (BigInteger var: list) {
                writer.write(var.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<BigInteger> readFromFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<BigInteger> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null){
                list.add(new BigInteger(line));
            }
            return list;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void writeDataInFile(){
        List<BigInteger> list1 = new ArrayList<>();
        list1.add(this.e);
        list1.add(this.n);
        writeInFile("open_key.txt",list1);
        List<BigInteger> list2 = new ArrayList<>();
        list2.add(this.d);
        list2.add(this.n);
        writeInFile("secret_key.txt",list2);
        list1.clear();
        list1.add(this.m);
        writeInFile("source_message.txt",list1);
    }

    public void Encryption(){
        interimCalculations();
        writeDataInFile();
        this.c = modularExponential(this.m,this.e,this.n);
        List<BigInteger> list = new ArrayList<>();
        list.add(this.c);
        writeInFile("encryption_message.txt",list);
        BigInteger outputM = modularExponential(this.c,this.d,this.n);
        System.out.println("Открытый ключ (e): " + this.e);
        System.out.println("Закрытый ключ (d): " + this.d);
        System.out.println("Значение n: " + this.n);
        System.out.println("Зашифрованное сообщение: " + this.c);
        System.out.println("Расшифрованное сообщение: " + outputM);
    }

    public void digitalSignature() {
        interimCalculations();
        this.s = modularExponential(this.m,this.d,this.n);
        List<BigInteger> list = new ArrayList<>();
        writeDataInFile();
        list.add(this.s);
        writeInFile("encryption_message.txt",list);
        BigInteger outputM = modularExponential(this.s,this.e,this.n);
        System.out.println("Исходный ключ (e): " + this.e);
        System.out.println("Секретный ключ (d): " + this.d);
        System.out.println("Значение n: " + this.n);
        System.out.println("Исходный текст (m): " + this.m);
        System.out.println("Значение цифровой подписи s: " + this.s);
        System.out.println("Прообраз подписи m*: " + outputM);
    }

    public void setValueM(){
        System.out.print("Введите число M: ");
        Scanner scanner = new Scanner(System.in);
        this.m = scanner.nextBigInteger();
    }

    private BigInteger modularExponential(BigInteger base, BigInteger exp, BigInteger mod){
        BigInteger result = BigInteger.ONE;
        BigInteger baseTWO = BigInteger.valueOf(2);
        while (exp.compareTo(BigInteger.ZERO) > 0) {
            if (exp.mod(baseTWO).equals(BigInteger.ONE)) {
                result = (result.multiply(base)).mod(mod);
            }
            exp = exp.divide(baseTWO);
            base = (base.multiply(base)).mod(mod);
        }
        return result;
    }

    public List<BigInteger> getEncryptionMessage(){
        List<BigInteger> list = readFromFile("encryption_message.txt");
        System.out.println("Зашифрованный текст: " + list.get(0));
        return list;
    }

    public List<BigInteger> getOpenKey(){
        List<BigInteger> list = readFromFile("open_key.txt");
        System.out.println("Исходный ключ (e): " + list.get(0));
        System.out.println("Исходное ключ (n): " + list.get(1));
        return list;
    }

    public List<BigInteger> getSecretKey(){
        List<BigInteger> list = readFromFile("secret_key.txt");
        System.out.println("Секретный ключ (d): " + list.get(0));
        System.out.println("Секретный ключ (n): " + list.get(1));
        return list;
    }

    public List<BigInteger> getSourceMessage(){
        List<BigInteger> list = readFromFile("source_message.txt");
        System.out.println("Исходный текст: " + list.get(0));
        return list;
    }
}