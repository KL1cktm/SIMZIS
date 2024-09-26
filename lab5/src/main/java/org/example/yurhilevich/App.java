package org.example.yurhilevich;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RSAMethod method = new RSAMethod();
        method.generatePrimeNumbers();
        method.Encryption();
//        method.digitalSignature();
        System.out.println("\nДанные прочтённые из файла\n");
        method.getSourceMessage();
        method.getEncryptionMessage();
        method.getOpenKey();
        method.getSecretKey();
    }
}
