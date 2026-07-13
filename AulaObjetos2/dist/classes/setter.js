"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Conta {
    // É prática padrão usar um sublinhado antes da propriedade real
    _saldo;
    constructor(saldoInicial) {
        this._saldo = saldoInicial;
    }
    // Getter para ler o saldo
    get saldoAtual() {
        return this._saldo;
    }
    // Setter para depositar
    set depositar(valor) {
        if (valor > 0) {
            this._saldo += valor;
        }
        else {
            console.log("Valor inválido!");
        }
    }
}
// Corrigido 'conta' (minúsculo) para 'Conta' (maiúsculo)
let conta1 = new Conta(100);
// Para utilizar um setter, chamamos como uma atribuição de propriedade, não como função
conta1.depositar = 500;
// Para utilizar um getter, lemos como propriedade, sem usar parênteses
console.log("Saldo atual: " + conta1.saldoAtual);
//# sourceMappingURL=setter.js.map