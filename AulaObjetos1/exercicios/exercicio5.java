
// Exercício 5
// Desenvolva um algoritmo em Java que leia um número inteiro e imprima o seu
// antecessor e seu sucessor.

import java.util.Scanner;

public class exercicio5 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int numero;
        int antecessor;
        int sucessor;

        System.out.println();
        System.out.println("[Exercicio 5. Aluno: Felipe]");
        System.out.println();
        System.out.println("Digite um numero inteiro:");
        numero = entrada.nextInt();

        antecessor = numero - 1;
        sucessor = numero + 1;

        System.out.println();
        System.out.println("Antecessor: " + antecessor);
        System.out.println("Sucessor: " + sucessor);
        System.out.println();
        System.out.println("[FIM DO EXERCICIO 5]");

        entrada.close();
    }
}