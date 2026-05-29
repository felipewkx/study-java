// Exercício 2
// Fazer um programa que informe um saldo e imprima o saldo com reajuste de 1%.

import java.util.Scanner;

public class exercicio2 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        double saldo;
        double saldoReajustado;

        System.out.println();
        System.out.println("[Exercicio 2. Aluno: Felipe]");
        System.out.println();
        System.out.println("Digite o saldo:");
        System.out.println();
        saldo = entrada.nextDouble();

        saldoReajustado = saldo + (saldo * 0.01);

        System.out.println();
        System.out.println("Saldo com reajuste de 1%: " + saldoReajustado);
        System.out.println();
        System.out.println("[FIM DO EXERCICIO 2]");

        entrada.close();
    }
}