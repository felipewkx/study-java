class Conta {
    private saldo: number;

    constructor(saldoInicial: number) {
        this.saldo = saldoInicial;
    }

    get saldoAtual() {
        console.log("metodo get");
        return this.saldo;
    }
}

let c = new Conta(1000);

console.log(`Você acessou: Saldo atual da conta é R$ ${c.saldoAtual}.`);