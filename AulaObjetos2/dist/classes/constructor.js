"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Carro {
    marca;
    cor;
    modelo;
    ano;
    constructor(marca, cor, modelo, ano) {
        this.marca = marca;
        this.cor = cor;
        this.modelo = modelo;
        this.ano = ano;
    }
    // Método movido para a classe correta
    idadeDoCarro() {
        const anoAtual = new Date().getFullYear();
        return anoAtual - this.ano;
    }
}
// Testando o Carro
let c1 = new Carro("Toyota", "Vermelho", "Corolla", 2020);
console.log(`Você acessou: Carro modelo ${c1.modelo} - Marca: ${c1.marca} - Cor: ${c1.cor} - Ano: ${c1.ano}.`);
console.log(`Idade do carro: ${c1.idadeDoCarro()} anos.`); // Exemplo de uso
// Animal
class Animal {
    nome;
    tipo;
    constructor(nome, tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
    apresentar() {
        console.log(`O nome do animal é ${this.nome} e seu tipo é ${this.tipo}.`);
    }
}
// Testando os Animais
let a1 = new Animal("Cachorro", "Carnívoro");
a1.apresentar();
let a2 = new Animal("Urso", "Carnívoro");
a2.apresentar();
//# sourceMappingURL=constructor.js.map