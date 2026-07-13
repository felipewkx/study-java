// Exercício 1
// Fazer um programa que imprima:

// a média aritmética dos números 8, 9 e 7

// a média dos números 4, 5 e 6

// a soma das duas médias

// a média das médias

public class exercicio1 {

    public static void main(String[] args) {

        double media1;
        double media2;
        double somaMedias;
        double mediaDasMedias;

        media1 = (8 + 9 + 7) / 3.0;
        media2 = (4 + 5 + 6) / 3.0;

        somaMedias = media1 + media2;

        mediaDasMedias = (media1 + media2) / 2;

        System.out.println();
        System.out.println("[Exercicio 1. Aluno: Felipe]");
        System.out.println();
        System.out.println("Qual a media dos numeros 8, 9 e 7? É: " + media1);
        System.out.println();
        System.out.println("Qual a media dos numeros 4, 5 e 6? É " + media2);
        System.out.println();
        System.out.println("Qual a soma das medias? É " + somaMedias);
        System.out.println();
        System.out.println("Qual a media das medias? É " + mediaDasMedias);
        System.out.println();
        System.out.println("[FIM DO EXERCICIO 1]");
    }
}
