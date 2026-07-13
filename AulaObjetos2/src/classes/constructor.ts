class Carro {
    marca: string;
    cor: string;
    modelo: string;
    ano: number;

    constructor(marca: string, cor: string, modelo: string, ano: number) {
        this.marca = marca;
        this.cor = cor;
        this.modelo = modelo;
        this.ano = ano;
    }

    // Método 
    idadeDoCarro(): number {
        const anoAtual = new Date().getFullYear();
        return anoAtual - this.ano;
    }
}

// Testando o Carro
let c1 = new Carro("Toyota", "Vermelho", "Corolla", 2020);
console.log(`Você acessou: Carro modelo ${c1.modelo} - Marca: ${c1.marca} - Cor: ${c1.cor} - Ano: ${c1.ano}.`);
console.log(`Idade do carro: ${c1.idadeDoCarro()} anos.`);


// Animal
class Animal {
    nome: string;
    tipo: string;

    constructor(nome: string, tipo: string) {
        this.nome = nome;
        this.tipo = tipo;
    }

    apresentar() {
        console.log(`O nome do animal é ${this.nome} e seu tipo é ${this.tipo}.`);
    }
}

// Testando os Animais
let a1 = new Animal("Rex", "Cachorro");
a1.apresentar();

let a2 = new Animal("Frederico Primeiro", "Gato");
a2.apresentar();

