
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
                // PARTE 4 (Início): Histórico da loja
                ArrayList<String> historico = new ArrayList<>();

                // PARTE 1: Fila de clientes
                ArrayList<Cliente> filaAtendimento = new ArrayList<>();

                Cliente c1 = new Cliente("Ana Silva", "ana@email.com", "111.111.111-11", "(11) 91111-1111");
                Cliente c2 = new Cliente("Carlos Souza", "carlos.souza@email.com", "222.222.222-22", "(11) 92222-2222");
                Cliente c3 = new Cliente("Mariana Costa", "mari.costa@email.com", "333.333.333-33", "(11) 93333-3333");

                filaAtendimento.add(c1);
                filaAtendimento.add(c2);
                filaAtendimento.add(c3);

                historico.add("Clientes adicionados à fila: " + c1.getNome() + ", " + c2.getNome() + ", "
                                + c3.getNome());

                System.out.println("==========================================");
                System.out.println("          ATENDIMENTO DA LOJA");
                System.out.println("==========================================");

                // Atende o primeiro cliente usando remove(0)
                Cliente clienteAtendido = filaAtendimento.remove(0);
                historico.add("Cliente atendido: " + clienteAtendido.getNome());

                System.out.println("Cliente em atendimento: " + clienteAtendido.getNome());
                System.out.println("\nClientes ainda aguardando na fila:");
                for (Cliente c : filaAtendimento) {
                        System.out.println("- " + c.getNome());
                }

                // PARTE 2: Pedido do cliente atendido
                ProdutoFisico livro = new ProdutoFisico("Livro Clean Code", 90.0, 15.0);
                ProdutoDigital ebook = new ProdutoDigital("E-book Design Patterns", 45.0, "QR-CODE-12345");

                Pedido pedido = new Pedido(clienteAtendido);
                pedido.adicionarItem(livro, 1);
                pedido.adicionarItem(ebook, 2);

                historico.add("Pedido criado para " + clienteAtendido.getNome() + " com total de R$ "
                                + pedido.calcularTotal());

                System.out.println("\n------------------------------------------");
                System.out.println("PEDIDO DO CLIENTE");
                System.out.println("Cliente: " + pedido.getCliente().getNome());
                for (ItemPedido item : pedido.getItens()) {
                        System.out.println("- " + item.getProduto().nome + " | Qtd: " + item.getQuantidade()
                                        + " | Subtotal: R$ " + item.calcularSubtotal());
                }
                System.out.println("Total do pedido: R$ " + pedido.calcularTotal());

                // PARTE 3: Pagamento
                System.out.println("\n------------------------------------------");
                System.out.println("PROCESSAMENTO DO PAGAMENTO");
                PagamentoPix pagamento = new PagamentoPix(pedido.calcularTotal());
                pagamento.processar();
                System.out.println("Total final pago (com taxas se houver): R$ " + pagamento.calcularTotal());

                historico.add("Pagamento de R$ " + pagamento.calcularTotal() + " processado via Pix");

                // PARTE 4 (Conclusão): Histórico antes e depois de desfazer
                System.out.println("\n==========================================");
                System.out.println("HISTÓRICO DA LOJA (ANTES DE DESFAZER):");
                for (int i = 0; i < historico.size(); i++) {
                        System.out.println((i + 1) + ". " + historico.get(i));
                }

                // Desfaz a última ação
                historico.remove(historico.size() - 1);

                System.out.println("\nHISTÓRICO DA LOJA (DEPOIS DE DESFAZER A ÚLTIMA AÇÃO):");
                for (int i = 0; i < historico.size(); i++) {
                        System.out.println((i + 1) + ". " + historico.get(i));
                }
                System.out.println("==========================================");
        }
}