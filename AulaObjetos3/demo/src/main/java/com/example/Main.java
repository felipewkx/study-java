package com.example;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.Cliente;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // --- BLOCO PARA AUMENTAR A FONTE DE TUDO ---
        Font fonteTexto = new Font("Arial", Font.BOLD, 30);
        Font fonteBotao = new Font("Arial", Font.PLAIN, 28);
        UIManager.put("OptionPane.messageFont", fonteTexto);
        UIManager.put("OptionPane.buttonFont", fonteBotao);
        UIManager.put("TextField.font", fonteTexto);
        // -------------------------------------------

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Monitor", 50.0, 5));
        produtos.add(new Produto("Mouse", 30.0, 0));
        produtos.add(new Produto("Teclado", 45.0, 12));
        produtos.add(new Produto("Gabinete", 95.0, 16));
        produtos.add(new Produto("Placa de Vídeo", 595.0, 26));
        produtos.add(new Produto("Pendrive 20gb", 20.0, 56));

        // Menu Inicial
        String[] opcoes = { "Visualizar Estoque", "Modificar Estoque", "Descontos", "Clientes", "Sair" };
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Selecione a operação desejada:",
                "Controle de Estoque",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (escolha == 3) {
            olharCliente();
        } else if (escolha == 4 || escolha == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Saindo do sistema...");
        } else {
            JOptionPane.showMessageDialog(null, "Outra opção selecionada: " + opcoes[escolha]);
        }

        if (escolha == 0) {
            // Fluxo Original: Busca por código + Exibição do Relatório Completo
            buscarPorCodigo(produtos);
            exibirRelatorio(produtos);
        } else if (escolha == 1) {
            // Fluxo Novo: Modificar a quantidade de um produto específico
            modificarEstoque(produtos);
        } else if (escolha == 2) {
            // Fluxo de Descontos: Submenu para escolher o tipo de desconto
            String[] opcoesDesconto = { "Por Percentual (%)", "Por Valor (R$)" };
            int escolhaDesconto = JOptionPane.showOptionDialog(
                    null,
                    "Selecione o tipo de desconto que deseja aplicar:",
                    "Aplicar Desconto",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesDesconto,
                    opcoesDesconto[0]);

            if (escolhaDesconto == 0) {
                aplicarDescontoPercentual(produtos);
            } else if (escolhaDesconto == 1) {
                aplicarDescontoPorValor(produtos);
            }
        }

    }

    public static void buscarPorCodigo(List<Produto> produtos) {
        String input = JOptionPane.showInputDialog(null, "Digite o código do produto:", "Buscar Produto",
                JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int codigoBusca = Integer.parseInt(input);

                if (codigoBusca < 0) {
                    throw new NumberFormatException();
                }

                Optional<Produto> produtoEncontrado = produtos.stream()
                        .filter(p -> p.getCodigo() == codigoBusca)
                        .findFirst();

                if (produtoEncontrado.isPresent()) {
                    Produto p = produtoEncontrado.get();
                    // ADICIONADO: "Código: %s\n" no formato da String e p.getCodigo() nos
                    // argumentos
                    String mensagem = String.format(
                            "Produto Encontrado:\nCódigo: %s\nNome: %s\nPreço: R$ %.2f\nEstoque: %d",
                            p.getCodigo(), p.getNome(), p.getPreco(), p.getQuantidade());
                    JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "FATAL ERROR: Produto não encontrado para o código ---> " + codigoBusca, "FATAL ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um código numérico válido (maior ou igual a 0).",
                        "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void modificarEstoque(List<Produto> produtos) {
        String inputCodigo = JOptionPane.showInputDialog(null, "Digite o código do produto para modificar:",
                "Modificar Estoque",
                JOptionPane.QUESTION_MESSAGE);

        if (inputCodigo == null || inputCodigo.trim().isEmpty())
            return;

        try {
            int codigoBusca = Integer.parseInt(inputCodigo);
            Optional<Produto> produtoEncontrado = produtos.stream()
                    .filter(p -> p.getCodigo() == codigoBusca)
                    .findFirst();

            if (!produtoEncontrado.isPresent()) {
                JOptionPane.showMessageDialog(null, "FATAL ERROR: Produto não encontrado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto p = produtoEncontrado.get();

            // Menu para Adicionar ou Remover
            String[] acoes = { "Adicionar", "Remover", "Cancelar" };
            int acao = JOptionPane.showOptionDialog(
                    null,
                    String.format("Produto: %s\nEstoque Atual: %d unidades\n\nO que deseja fazer?", p.getNome(),
                            p.getQuantidade()),
                    "Alterar Estoque",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    acoes,
                    acoes[0]);

            if (acao == 2 || acao == JOptionPane.CLOSED_OPTION)
                return;

            String inputQtd = JOptionPane.showInputDialog(null, "Digite a quantidade (apenas números positivos):",
                    "Quantidade",
                    JOptionPane.QUESTION_MESSAGE);

            if (inputQtd == null || inputQtd.trim().isEmpty())
                return;

            int qtd = Integer.parseInt(inputQtd);
            boolean sucesso = false;

            if (acao == 0) {
                sucesso = p.adicionarEstoque(qtd);
            } else if (acao == 1) {
                sucesso = p.removerEstoque(qtd);
            }

            if (sucesso) {
                JOptionPane.showMessageDialog(null,
                        String.format("Estoque atualizado com sucesso!\nNova quantidade de %s: %d", p.getNome(),
                                p.getQuantidade()),
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Operação inválida. Verifique se digitou um valor positivo ou se há estoque suficiente.",
                        "Erro na Operação", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.", "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void aplicarDescontoPercentual(List<Produto> produtos) {
        String inputCodigo = JOptionPane.showInputDialog(null, "Digite o código do produto para aplicar desconto (%):",
                "Desconto Percentual", JOptionPane.QUESTION_MESSAGE);

        if (inputCodigo != null && !inputCodigo.trim().isEmpty()) {
            try {
                int codigoBusca = Integer.parseInt(inputCodigo);
                if (codigoBusca < 0)
                    throw new NumberFormatException();

                Optional<Produto> produtoEncontrado = produtos.stream()
                        .filter(p -> p.getCodigo() == codigoBusca)
                        .findFirst();

                if (produtoEncontrado.isPresent()) {
                    Produto p = produtoEncontrado.get();
                    String inputPercentual = JOptionPane.showInputDialog(null,
                            String.format(
                                    "Produto: %s\nPreço Atual: R$ %.2f\n\nDigite a porcentagem do desconto (0 a 50):",
                                    p.getNome(), p.getPreco()),
                            "Desconto Percentual", JOptionPane.QUESTION_MESSAGE);

                    if (inputPercentual != null && !inputPercentual.trim().isEmpty()) {
                        double percentual = Double.parseDouble(inputPercentual);

                        if (p.aplicarDesconto(percentual)) {
                            JOptionPane.showMessageDialog(null,
                                    String.format("Desconto de %.2f%% aplicado com sucesso!\nNovo preço: R$ %.2f",
                                            percentual, p.getPreco()),
                                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "FATAL ERROR: Percentual inválido! Deve ser entre 0 e 50%.", "FATAL ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "FATAL ERROR: Produto não encontrado para o código ---> " + codigoBusca, "FATAL ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.", "FATAL ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void aplicarDescontoPorValor(List<Produto> produtos) {
        String inputCodigo = JOptionPane.showInputDialog(null, "Digite o código do produto para aplicar desconto (R$):",
                "Desconto em Valor", JOptionPane.QUESTION_MESSAGE);

        if (inputCodigo != null && !inputCodigo.trim().isEmpty()) {
            try {
                int codigoBusca = Integer.parseInt(inputCodigo);
                if (codigoBusca < 0)
                    throw new NumberFormatException();

                Optional<Produto> produtoEncontrado = produtos.stream()
                        .filter(p -> p.getCodigo() == codigoBusca)
                        .findFirst();

                if (produtoEncontrado.isPresent()) {
                    Produto p = produtoEncontrado.get();
                    String inputValor = JOptionPane.showInputDialog(null,
                            String.format("Produto: %s\nPreço Atual: R$ %.2f\n\nDigite o valor em reais do desconto:",
                                    p.getNome(), p.getPreco()),
                            "Desconto em Valor", JOptionPane.QUESTION_MESSAGE);

                    if (inputValor != null && !inputValor.trim().isEmpty()) {
                        double valor = Double.parseDouble(inputValor);

                        if (p.aplicarDescontoValor(valor)) {
                            JOptionPane.showMessageDialog(null,
                                    String.format("Desconto de R$ %.2f aplicado com sucesso!\nNovo preço: R$ %.2f",
                                            valor, p.getPreco()),
                                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "FATAL ERROR: Valor inválido! O desconto deve ser maior que 0 e menor que o preço atual.",
                                    "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "FATAL ERROR: Produto não encontrado para o código ---> " + codigoBusca, "FATAL ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.", "FATAL ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void exibirRelatorio(List<Produto> produtos) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("<html><table border=0 cellpadding=5>");
        relatorio.append("<tr>"); // Linha inicial
        int count = 0;

        for (Produto p : produtos) {
            if (count > 0 && count % 3 == 0) {
                relatorio.append("</tr><tr>"); // Quebra de linha da tabela a cada 3 produtos
            }
            relatorio.append("<td style='border:1px solid #ccc; background-color:#f9f9f9;'>");
            relatorio.append("Código: ").append(p.getCodigo()).append("<br>");
            relatorio.append("<b>").append(p.getNome()).append("</b><br>");
            relatorio.append("Preço: R$ ").append(p.getPreco()).append("<br>");
            relatorio.append("Total: R$ ").append(p.calcularValorTotal()).append("<br>");
            relatorio.append("Em Estoque: ").append(p.temEstoque() ? "Sim" : "Não").append("<br>");
            relatorio.append("Qtd: ").append(p.getQuantidade());
            relatorio.append("</td>");
            count++;
        }
        relatorio.append("</tr></table><br>");

        Produto maisCaro = produtos.stream().max(Comparator.comparingDouble(Produto::getPreco)).orElse(null);
        Produto maisBarato = produtos.stream().min(Comparator.comparingDouble(Produto::getPreco)).orElse(null);
        Produto maisQuantidade = produtos.stream().max(Comparator.comparingInt(Produto::getQuantidade)).orElse(null);
        // CORRIGIDO ABAIXO: Alterado de 'status' para 'produtos'
        Produto menosQuantidade = produtos.stream().min(Comparator.comparingInt(Produto::getQuantidade)).orElse(null);

        relatorio.append("===============================\n");
        if (maisCaro != null) {
            relatorio.append("<i>PRODUTO MAIS CARO:</i> ").append(maisCaro.getNome()).append(" (R$ ")
                    .append(maisCaro.getPreco()).append(")").append("\n");
        }
        if (maisBarato != null) {
            relatorio.append("<i>PRODUTO MAIS BARATO:</i> ").append(maisBarato.getNome()).append(" (R$ ")
                    .append(maisBarato.getPreco()).append(")").append("\n");
        }
        if (maisQuantidade != null) {
            relatorio.append("<i>MAIOR ESTOQUE:</i> ").append(maisQuantidade.getNome()).append(" (")
                    .append(maisQuantidade.getQuantidade()).append(" unidades)").append("\n");
        }
        if (menosQuantidade != null) {
            relatorio.append("<i>MENOR ESTOQUE:</i> ").append(menosQuantidade.getNome()).append(" (")
                    .append(menosQuantidade.getQuantidade()).append(" unidades)").append("\n");
        }

        String htmlRelatorio = "<html><body>" + relatorio.toString().replace("\n", "<br>") + "</body></html>";

        htmlRelatorio = htmlRelatorio
                .replace("Código:", "<b>Código:</b>")
                .replace("Produto:", "<b>Produto:</b>")
                .replace("Preço:", "<b>Preço:</b>")
                .replace("Valor Total:", "<b>Valor Total:</b>")
                .replace("Tem Estoque:", "<b>Tem Estoque:</b>")
                .replace("Quantidade:", "<b>Quantidade:</b>")
                .replace("PRODUTO MAIS CARO:", "<b>PRODUTO MAIS CARO:</b>")
                .replace("PRODUTO MAIS BARATO:", "<b>PRODUTO MAIS BARATO:</b>")
                .replace("MAIOR ESTOQUE:", "<b>MAIOR ESTOQUE:</b>")
                .replace("MENOR ESTOQUE:", "<b>MENOR ESTOQUE:</b>");

        JOptionPane.showMessageDialog(null, htmlRelatorio, "Relatório de Estoque", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void olharCliente() {
        // Criando uma lista mockada (simulada) de clientes para teste
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1, "Ana Silva"));
        listaClientes.add(new Cliente(2, "Bruno Souza"));
        listaClientes.add(new Cliente(3, "Carlos Eduardo"));

        // 1. Pede o ID do cliente via JOptionPane
        String inputId = JOptionPane.showInputDialog(null, "Digite o ID do cliente:");

        // Se o usuário clicar em "Cancelar" ou fechar a janela do ID
        if (inputId == null) {
            return;
        }

        try {
            // 2. Converte a String digitada para um número inteiro
            int idBuscado = Integer.parseInt(inputId.trim());
            Cliente clienteEncontrado = null;

            // 3. Procura o cliente na lista pelo ID
            for (Cliente c : listaClientes) {
                if (c.getCodigo() == idBuscado) {
                    clienteEncontrado = c;
                    break;
                }
            }

            // 4. Exibe o resultado também em um JOptionPane
            if (clienteEncontrado != null) {
                String mensagem = "--- Dados do Cliente ---\n" +
                        "Código: " + clienteEncontrado.getCodigo() + "\n" +
                        "Nome: " + clienteEncontrado.getNome();
                JOptionPane.showMessageDialog(null, mensagem, "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente com o ID " + idBuscado + " não foi encontrado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            // Caso o usuário digite letras ou deixe em branco
            JOptionPane.showMessageDialog(null, "Por favor, digite apenas números válidos para o ID.",
                    "Erro de Digitação", JOptionPane.ERROR_MESSAGE);
        }
    }
}
