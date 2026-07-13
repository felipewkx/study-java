class Conta {
    // É prática padrão usar um sublinhado antes da propriedade real
    private _saldo: number; 

    constructor(saldoInicial: number) {
        this._saldo = saldoInicial;
    }

    // Getter para ler o saldo
    get saldoAtual(): number {
        return this._saldo;
    }

    // Setter para depositar
    set depositar(valor: number) {
        if (valor > 0) {
            this._saldo += valor;
        } else {
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


