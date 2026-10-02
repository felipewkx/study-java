# Atividade — O Service protege as regras da compra

**UC4 — Aula 12: Colaboração entre objetos e Service**

Preencha este material durante a atividade e entregue sua cópia pelo Google Classroom. Você receberá o início da classe `LojaService` pronto e funcionando, incluindo as listas, os métodos de cadastro e a busca de produto. Os métodos restantes já estarão declarados, mas você deverá completar seus corpos e aplicar as regras da operação.

Responda com suas próprias palavras e, quando solicitado, cole o trecho de código testado.

**Estudante:** Felipe Walker\
 **Turma:** TDS - UC4\
 **Data:** 01/10/2026

---

## Contexto

Na loja, a `Main` inicia o fluxo, o `Pedido` conhece seus itens e calcula o total, e o `LojaService` coordena a operação.

Nesta atividade, você vai completar os métodos restantes de uma classe `LojaService` parcialmente pronta.

Considere estas regras:

- O cliente precisa estar cadastrado na loja.
- O pedido precisa ter pelo menos um item.
- Os produtos do pedido precisam estar cadastrados na loja.
- O valor-base do pagamento precisa ser igual ao total calculado pelo pedido.
- Se alguma regra falhar, o pagamento não deve ser processado e o pedido não deve ser registrado.

---

## Exercício 1 — Casos do caixa: descubra as regras

Para cada situação, marque o resultado: **processa e registra**, **barra**, ou **falta informação**. Depois justifique citando a regra envolvida.

| Situação                                                                    | Resultado               | Regra e justificativa                                                                                                                    |
| --------------------------------------------------------------------------- | ----------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| Cliente cadastrado, itens da loja e valor-base correto.                     | **processa e registra** | Atendeu a todas as regras da loja: o cliente existe na lista, os produtos são cadastrados e o valor cobrado bate exatamente com o total. |
| Cliente não cadastrado, com pedido completo e pagamento correto.            | **barra**               | Regra: o cliente precisa estar previamente cadastrado na loja. Como não está, a compra não pode ser iniciada nem registrada.             |
| Um dos produtos do pedido não aparece no catálogo da loja.                  | **barra**               | Regra: todos os itens do pedido devem estar cadastrados no estoque/catálogo da loja para evitar venda de item inexistente.               |
| O pedido soma R$ 230,00, mas o pagamento informa R$ 220,00 como valor-base. | **barra**               | Regra: o valor-base informado no pagamento tem que ser idêntico ao total do pedido. Como faltaram R$ 10,00, a transação não é aceita.    |

### Reflexão

**Por que o Service deve validar as regras antes de chamar `pagamento.processar()`?**

> Porque se a gente processar o pagamento antes de checar as regras e depois der erro (como faltar um produto ou o cliente ser inválido), o cliente já vai ter sido cobrado no cartão ou Pix de forma indevida e a loja teria que fazer estorno manual. Validando antes, a gente garante que só desconta o dinheiro se a compra estiver 100% pronta para ser registrada no sistema.

---

## Exercício 2 — Continue a classe `LojaService`

No projeto, crie o arquivo `LojaService.java` no pacote `br.com.senac.service` usando o esqueleto abaixo.

Os campos, os cadastros e a busca de produto já estão implementados. Os métodos seguintes já estão declarados, mas seus corpos precisam ser completados.

Use os modelos existentes no projeto:

- `Cliente`
- `Produto`
- `Pedido`
- `ItemPedido`
- `Pagamento`

### Etapa de validação do pagamento

Dentro de `finalizarCompra`, implemente a comparação do valor-base do pagamento com o total do pedido:

```
if (Double.compare(
        pagamento.getValor(),
        pedido.calcularTotal()) != 0) {
    throw new IllegalArgumentException(
            "Pagamento diferente do pedido.");
}
```

Depois dessa validação, mantenha a ordem:

**validar → processar o pagamento → registrar o pedido**

### Implementação de `finalizarCompra`

```
public void finalizarCompra(Pedido pedido, Pagamento pagamento) {
    // 1. Valida se o cliente está cadastrado
    if (pedido == null || !clienteCadastrado(pedido.getCliente())) {
        throw new IllegalArgumentException(
                "Cliente do pedido não está cadastrado na loja.");
    }

    // 2. Valida se o pedido tem pelo menos 1 item
    if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
        throw new IllegalArgumentException(
                "O pedido precisa de pelo menos um item.");
    }

    // 3. Valida se todos os produtos do pedido
    // constam no catalogo da loja
    for (ItemPedido item : pedido.getItens()) {
        if (buscarProdutoPorCodigo(item.getProduto().getCodigo()) == null) {
            throw new IllegalArgumentException(
                    "Produto " + item.getProduto().getNome()
                    + " não cadastrado na loja.");
        }
    }

    // 4. Valida se o valor-base bate com o total do pedido
    if (Double.compare(
            pagamento.getValor(),
            pedido.calcularTotal()) != 0) {
        throw new IllegalArgumentException(
                "Pagamento diferente do pedido.");
    }

    // Fluxo final: Processar pagamento e registrar pedido
    pagamento.processar();
    this.pedidos.add(pedido);
}
```

### Testes realizados

| Teste              | Valor-base do pagamento | Total do pedido | O que aconteceu?                                                                                                               |
| ------------------ | ----------------------- | --------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| Valores iguais     | R$ 105,00               | R$ 105,00       | O pagamento foi aprovado com sucesso e o pedido foi salvo na lista de pedidos finalizados da loja.                             |
| Valores diferentes | R$ 90,00                | R$ 105,00       | O Service barrou o fluxo lançando a exceção `"Pagamento diferente do pedido."`, sem processar cobrança nem registrar o pedido. |

### Por que a ordem deve ser validar → processar → registrar?

A ordem precisa ser essa para blindar o sistema: primeiro validamos todas as regras de negócio para ter certeza de que o pedido é legítimo e está correto.

Estando tudo certo, executamos a operação externa crítica (processar o pagamento e cobrar o cliente).

Só após o pagamento ser bem-sucedido é que registramos o pedido no histórico da loja como uma venda concluída.

---

## Exercício 3 — Teste seu Service pela Main

Depois de criar a classe e seus métodos, use a `Main` para testar o fluxo abaixo.

Crie ou reutilize os objetos `loja`, `ana` e `livro`; confira os tipos e os imports do projeto.

### Trecho da `Main`

```
LojaService loja = new LojaService();

Cliente ana = new Cliente(
        "Ana Lima",
        "ana@email.com",
        "123.456.789-00",
        "(11) 98888-7777"
);

Produto livro = new ProdutoFisico(
        "Livro Harry Potter",
        90.0,
        15.0
);

loja.cadastrarCliente(ana);
loja.cadastrarProduto(livro);

Pedido pedido = loja.abrirPedido(ana);
pedido.adicionarItem(livro, 1);

Pagamento pagamento = new PagamentoCartao(
        pedido.calcularTotal(),
        5.0
);

loja.finalizarCompra(pedido, pagamento);

System.out.println(
        "Compra finalizada! Total de pedidos registrados: "
        + loja.listarPedidos().size()
);
```

---

### Agora tente finalizar um pedido vazio

#### O que você alterou para deixar o pedido sem itens?

Eu apenas comentei a linha em que o item era inserido:

```
// pedido.adicionarItem(livro, 1);
```

Assim o objeto `pedido` foi aberto com o cliente, mas sua lista interna de itens permaneceu vazia.

#### Qual resultado ocorreu ao chamar `finalizarCompra`?

O método não executou o pagamento e disparou a exceção `IllegalArgumentException`:

```
O pedido precisa de pelo menos um item.
```

Isso impediu que a compra prosseguisse.

#### Depois da tentativa inválida, esse pedido deve aparecer no histórico? Explique como você verificou.

Não deve aparecer.

Verifiquei fazendo um bloco `try-catch` capturando o erro e, logo em seguida, chamei:

```
loja.listarPedidos().size();
```

O retorno continuou sendo `0`, provando que o pedido com erro não foi adicionado à lista da loja.

---

## Desafio extra — Troque a forma de pagamento

Repita o fluxo usando `PagamentoPix`, calculando o valor a partir do próprio pedido.

### Qual linha da `Main` você precisou alterar?

```
Pagamento pagamento = new PagamentoPix(
        pedido.calcularTotal()
);
```

### Foi necessário alterar o `LojaService` para ele conhecer especificamente `PagamentoPix`? Explique.

Não foi necessário alterar nada no `LojaService`.

O método `finalizarCompra` foi tipado para receber a classe abstrata `Pagamento`. Como `PagamentoPix` herda de `Pagamento`, ela é aceita diretamente sem que o Service precise saber os detalhes internos de como o Pix funciona.

### Qual tipo de referência permite que o fluxo aceite formas de pagamento diferentes?

A classe genérica `Pagamento` (ou a interface `Pagavel`), aplicando o conceito de polimorfismo.

---

# Entrega pelo Classroom

Antes de enviar, confira:

- [x] Respondi aos casos do caixa e justifiquei as decisões.
- [x] Mantive a parte já implementada e completei os métodos que estavam com TODO.
- [x] Testei pagamento com valor correto e divergente.
- [x] Testei a compra pela `Main` e o pedido vazio.
- [x] Anexei `LojaService.java` e a `Main` usada nos testes, ou colei os trechos solicitados.
- [x] Se fiz o desafio extra, respondi às três perguntas.

---

## O que aprendi sobre a responsabilidade do Service?

Aprendi que o Service atua como o cérebro das regras de negócio do sistema.

Em vez de deixar a `Main` cuidar das regras ou misturar validações dentro das entidades (`Cliente`, `Pedido` ou `Pagamento`), o Service assume a responsabilidade de coordenar todo o processo com segurança, garantindo que o dinheiro só seja cobrado e o pedido só seja salvo se todas as condições da loja forem atendidas.
