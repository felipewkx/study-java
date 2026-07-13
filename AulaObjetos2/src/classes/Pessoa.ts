class Pessoa {
    nome: string;
    idade: number;
    altura: number;
    cpf: string;

    constructor(nome: string, idade: number, altura: number, cpf: string) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.cpf = cpf;
    }

    falar() {
        console.log(`Olá, meu nome é ${this.nome} e tenho ${this.idade} anos.`);
    }
}

let p1 = new Pessoa("João", 30, 1.75, "123.456.789-00");

console.log(p1.nome);
console.log(p1.idade);
console.log(p1.altura);
console.log(p1.cpf);

p1.falar();
