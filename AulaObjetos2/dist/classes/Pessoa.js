"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Pessoa {
    nome;
    idade;
    altura;
    cpf;
    constructor(nome, idade, altura, cpf) {
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
//# sourceMappingURL=Pessoa.js.map