
// Exercício 3
// Crie uma classe Java MaiorNumero que contenha um método que receba dois
// números inteiros e imprima o maior entre eles.

import java.util.Scanner;

public class exercicio3 {

    public static void mostrarMaior(int num1, int num2) {
        if (num1 > num2) {
            System.out.println("O maior numero é: " + num1);
        } else if (num2 > num1) {
            System.out.println("O maior numero é: " + num2);
        } else {
            System.out.println("Os números são iguais.");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("[Exercicio 3. Aluno: Felipe]");
        System.out.println();
        System.out.print("Digite o primeiro número: ");
        int n1 = scanner.nextInt();

        System.out.println();
        System.out.print("Digite o segundo número: ");
        int n2 = scanner.nextInt();

        System.out.println();
        mostrarMaior(n1, n2);
        System.out.println();
        System.out.println("[FIM DO EXERCICIO 2]");

        scanner.close();
    }
}
