
// Exercício 4
// Crie uma classe Java ComparaNumero que contenha um método que receba dois
// números e indique se são iguais ou diferentes. Mostre o maior e o menor.

import java.util.Scanner;

public class exercicio4 {

    public static void comparar(int num1, int num2) {

        if (num1 == num2) {

            System.out.println("Os numeros sao iguais.");

        } else {

            System.out.println("Os numeros sao diferentes.");

            if (num1 > num2) {

                System.out.println("Maior numero: " + num1);
                System.out.println("Menor numero: " + num2);

            } else {

                System.out.println("Maior numero: " + num2);
                System.out.println("Menor numero: " + num1);

            }

        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("[Exercicio 4. Aluno: Felipe]");
        System.out.println();
        System.out.print("Digite o primeiro número: ");
        int n1 = scanner.nextInt();

        System.out.println();
        System.out.print("Digite o segundo número: ");
        int n2 = scanner.nextInt();

        System.out.println();
        comparar(n1, n2);
        System.out.println();
        System.out.println("[FIM DO EXERCICIO 4]");

        scanner.close();
    }
}