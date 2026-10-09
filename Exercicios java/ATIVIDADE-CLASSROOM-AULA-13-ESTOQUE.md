# Atividade — Sprint da Loja 2.0: compra protegida por estoque

**UC4 — Aula 13: Sprint de funcionalidades**

Preencha esta ficha durante a atividade e entregue sua cópia pelo Google Classroom. Você vai trabalhar no `LojaService` que a turma já construiu. Cadastro, busca de produtos, abertura de pedidos e validações básicas da compra já existem. Não recrie esses métodos. Sua missão é acrescentar a regra de estoque e comprovar que o restante do fluxo continua funcionando.

**Estudante(s):** Felipe Walker  
**Turma:** TDS  
**Data:** 08/10/26

---

## Missão

Impedir que uma compra aprovada deixe o estoque negativo.

Ao final, o sistema deverá conferir o estoque de todos os itens antes de processar o pagamento. Se a compra for válida, deverá processar o pagamento, atualizar os saldos e registrar o pedido. Se alguma regra falhar, não poderá haver pagamento processado, saldo alterado ou pedido registrado.

---

## O que já existe no projeto

Antes de programar, localize estes elementos:

| Classe ou método                   | Responsabilidade atual                                           |
| :--------------------------------- | :--------------------------------------------------------------- |
| `LojaService.finalizarCompra(...)` | Valida regras básicas, processa o pagamento e registra o pedido. |
| `Pedido.getItens()`                | Devolve os itens que fazem parte do pedido.                      |
| `ItemPedido.getProduto()`          | Devolve o produto de uma linha do pedido.                        |
| `ItemPedido.getQuantidade()`       | Devolve a quantidade pedida naquela linha.                       |
| `Produto.getQuantidade()`          | Informa o saldo atual do produto.                                |
| `Produto.setQuantidade(...)`       | Atualiza o saldo do produto.                                     |
| `ItemPedido`                       | Já impede produto nulo e quantidade menor ou igual a zero.       |

**Não remova as validações que já existem em `finalizarCompra`.** Complete o fluxo mantendo as regras atuais de cliente, pedido, produto cadastrado e valor do pagamento.

---

## Exercício 1 — Reproduza a falha

Antes de editar o Service, use a `Main` para simular uma compra acima do saldo. Cadastre um produto com 5 unidades e tente finalizar um pedido com 6. Observe o que o programa faz.

| O que observar                   | Resultado atual                                                        |
| :------------------------------- | :--------------------------------------------------------------------- |
| A compra foi aceita ou recusada? | Foi aceita direto, não deu nenhum erro.                                |
| O pagamento foi processado?      | Sim, printou no terminal que o cartão foi aprovado.                    |
| O saldo do produto mudou?        | Não mudou nada, continuou com 5 porque o código não diminui o estoque. |
| O pedido entrou no histórico?    | Sim, foi parar na lista de pedidos da loja.                            |

**Explique qual regra falta no `LojaService` e em que parte de `finalizarCompra` você procuraria inserir a validação:**

Falta colocar um `for` com um `if` para checar se a quantidade que o cliente pediu é maior do que tem no estoque do produto. Eu colocaria essa parte dentro do método `finalizarCompra`, logo antes de chamar o `pagamento.processar()`. Porque se não tiver produto suficiente no estoque, o programa já tem que dar erro e parar ali mesmo, sem cobrar o cliente nem registrar nada.

---

## Exercício 2 — Valide o estoque

Acrescente a validação ao método existente `finalizarCompra`. Preserve as verificações que já estão funcionando. Reutilize `ItemPedido` e `Produto`; não crie outro cadastro nem duplique a quantidade em uma nova lista.

Complete a ideia da regra:

> Para cada item, a quantidade pedida deve ser **menor ou igual** à quantidade disponível no produto.

Registre os testes feitos:

| Saldo antes | Quantidade pedida | Resultado esperado            | Resultado obtido                                   |
| :---------: | :---------------: | :---------------------------- | :------------------------------------------------- |
|      5      |         3         | Passar normal e sobrar 2      | Passou a compra e o saldo foi pra 2                |
|      4      |         4         | Passar no limite e zerar      | Passou certinho e o saldo ficou 0                  |
|      3      |         4         | Dar erro e não deixar comprar | Caiu na exception de estoque insuficiente e barrou |

Registre onde fez a alteração:

- **Arquivo:** `LojaService.java`
- **Método:** `finalizarCompra`

Cole abaixo o trecho de validação que você acrescentou:

```java
for (ItemPedido item : pedido.getItens()) {
    if (item.getQuantidade() > item.getProduto().getQuantidade()) {
        throw new IllegalArgumentException("Estoque insuficiente para o produto: " + item.getProduto().getNome());
    }
}
```

---

## Exercício 3 — Atualize depois da validação

Integre a baixa do estoque ao fluxo que já existe. Primeiro, todas as validações do pedido precisam passar. Depois, processe o pagamento, atualize os saldos e registre o pedido. Uma falha não pode deixar cobrança, saldo alterado ou pedido no histórico.

Organize as etapas na ordem escolhida:

| Etapa                              | Número da ordem |
| :--------------------------------- | :-------------: |
| Conferir o saldo de todos os itens |        1        |
| Processar o pagamento              |        2        |
| Atualizar o saldo dos produtos     |        3        |
| Registrar o pedido finalizado      |        4        |

Agora teste uma falha. Para a situação abaixo, registre o estado antes e depois da tentativa:

| Situação                     | Antes da tentativa                                      | Depois da tentativa                                     |
| :--------------------------- | :------------------------------------------------------ | :------------------------------------------------------ |
| Saldo: 3; pedido: 4 unidades | Saldo: 3; pagamento: não cobrou; pedidos registrados: 0 | Saldo: 3; pagamento: não cobrou; pedidos registrados: 0 |

**Como você confirmou que a tentativa recusada não alterou o estoque nem registrou o pedido?**

> Eu rodei o teste na `Main` dentro de um `try/catch`. Quando caiu no `catch`, eu dei um `System.out.println` no `livro.getQuantidade()` e vi que continuava com 3, e printei o tamanho da lista com `loja.listarPedidos().size()` e continuou 0. Além disso, no console nem apareceu a mensagem de pagamento aprovado.

## Exercício 4 — Teste de regressão

Execute cada cenário na sua própria `Main`. Além da nova regra de estoque, confirme que a validação de pagamento da Aula 12 continua funcionando. Registre o resultado esperado e o observado.

| Cenário                                     | Resultado esperado               | Resultado observado                                  | Passou? |
| :------------------------------------------ | :------------------------------- | :--------------------------------------------------- | :-----: |
| Saldo 5; pedido 3                           | Aprovar e saldo ir pra 2         | Aprovou e o saldo foi pra 2                          |   Sim   |
| Saldo 4; pedido 4                           | Aprovar e saldo ir pra 0         | Aprovou e o saldo zerou                              |   Sim   |
| Saldo 3; pedido 4                           | Dar erro de estoque insuficiente | Deu IllegalArgumentException de estoque              |   Sim   |
| Pagamento com valor-base diferente do total | Dar erro de pagamento diferente  | Deu IllegalArgumentException de pagamento divergente |   Sim   |
| Quantidade zero ao criar `ItemPedido`       | Dar erro de quantidade inválida  | Deu IllegalArgumentException no ItemPedido           |   Sim   |

Se algum teste falhou, descreva o comportamento encontrado e a correção necessária:

> Não falhou nenhum teste. Todos os testes novos de estoque passaram e os testes antigos de pagamento e de item zerado continuaram funcionando normal.

### Desafio extra — O mesmo produto em duas linhas

Considere um produto com saldo 5 e um pedido com duas linhas do mesmo produto: 3 unidades em uma linha e 3 na outra. A validação simples de cada linha, isoladamente, seria suficiente? Explique o risco e proponha como o Service poderia considerar a quantidade total pedida daquele produto.

> Não seria suficiente. O perigo é que, olhando uma linha de cada vez, o código vê a primeira linha com 3 (e 3 cabe em 5, então passa) e depois vê a segunda com 3 (e 3 ainda cabe em 5, então passa também). Só que no total são 6 unidades, e quando for diminuir do saldo de 5, o estoque vai ficar negativo (-1).
>
> Pra resolver isso, o Service precisaria somar antes o total pedido de cada produto. Daria pra usar um `Map` ou fazer uma conta antes somando os itens repetidos pra bater o total com o saldo do produto antes de aprovar.

---

## Entrega pelo Classroom

Envie esta ficha preenchida e anexe:

- [x] `LojaService.java` atualizado, preservando as validações que já existiam.
- [x] A `Main` usada para testar os cenários.
- [x] Evidências ou registros dos testes com compra aceita, no limite e recusada.
- [x] As respostas desta ficha, incluindo o teste de pagamento divergente.
- [x] Se resolveu o desafio extra, a explicação da estratégia usada.

**Em uma frase: qual regra da operação o `LojaService` passou a proteger?**

> Passou a proteger para a loja nunca vender mais itens do que tem guardado, evitando que o estoque fique negativo.
