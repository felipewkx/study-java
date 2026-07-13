"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Conta {
    saldo;
    constructor(saldoInicial) {
        this.saldo = saldoInicial;
    }
    get saldoAtual() {
        console.log("metodo get");
        return this.saldo;
    }
}
let c = new Conta(1000);
console.log(`Você acessou: Saldo atual da conta é R$ ${c.saldoAtual}.`);
//# sourceMappingURL=getter.js.map