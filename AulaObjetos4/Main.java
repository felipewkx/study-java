import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // PARTE 4 (Início): Histórico da loja
        ArrayList<String> historico = new ArrayList<>();

        // PARTE 1: Fila de clientes (ArrayList) e Controle de Unicidade (Set)
        ArrayList<Cliente> filaAtendimento = new ArrayList<>();
        Set<String> documentos = new HashSet<>();
        
        // Lista auxiliar para guardar os documentos que foram rejeitados
        ArrayList<String> documentosRejeitados = new ArrayList<>();

        // Instanciação de 4 clientes (c2 e c4 possuem o mesmo CPF de propósito)
        Cliente c1 = new Cliente("Gugu Liberato", "gugu@email.com", "111.111.111-11", "(11) 91111-1111");
        Cliente c2 = new Cliente("Fausto Silva", "faustao@email.com", "222.222.222-22", "(11) 92222-2222");
        Cliente c3 = new Cliente("Hebe Camargo", "hebesbt@email.com", "333.333.333-33", "(11) 93333-3333");
        Cliente c4 = new Cliente("Bruce Wayne", "batman@email.com", "222.222.222-22", "(11) 94444-4444");
        Cliente c5 = new Cliente("Celso Portiolli", "celso@email.com", "444.444.444-44", "(11) 95555-5555");

        Cliente[] novosClientes = {c1, c2, c3, c4, c5};

        System.out.println("==========================================");
        System.out.println("          PROCESSANDO CADASTROS");
        System.out.println("==========================================");

        for (Cliente c : novosClientes) {
            // Testando o retorno do .add(). Só entra no bloco se for true (inédito)
            boolean documentoInedito = documentos.add(c.getDocumento());

            if (documentoInedito) { 
                // Adiciona à lista APENAS quando o documento entra no set
                filaAtendimento.add(c);
                historico.add("Cliente cadastrado: " + c.getNome());
                System.out.println("Status: [SUCESSO] " + c.getNome() + " (ID #" + c.getCodigo() + ") validado.");
            } else {
                // Guarda o documento rejeitado para exibir no relatório final
                documentosRejeitados.add(c.getDocumento() + " (" + c.getNome() + ")");
                System.out.println("Status: [REJEITADO] " + c.getNome() + " possui CPF já existente.");
            }
        }

        // EXIBIÇÃO DOS RELATÓRIOS SOLICITADOS
        System.out.println("\n==========================================");
        System.out.println("          RELATÓRIO DE CADASTROS");
        System.out.println("==========================================");
        
        System.out.println("Clientes Aceitos:");
        for (Cliente c : filaAtendimento) {
            System.out.println("- ID #" + c.getCodigo() + ": " + c.getNome() + " [CPF: " + c.getDocumento() + "]");
        }

        System.out.println("\nDocumentos Rejeitados:");
        if (documentosRejeitados.isEmpty()) {
            System.out.println("- Nenhum documento foi rejeitado.");
        } else {
            for (String doc : documentosRejeitados) {
                System.out.println("- CPF: " + doc);
            }
        }

        // Total de cadastros válidos finais na estrutura
        System.out.println("\nTotal de cadastros válidos: " + filaAtendimento.size());

        System.out.println("\n==========================================");
        System.out.println("          ATENDIMENTO DA LOJA");
        System.out.println("==========================================");

        // Atende o primeiro cliente usando remove(0)
        Cliente clienteAtendido = filaAtendimento.remove(0);
        historico.add("Cliente atendido: " + clienteAtendido.getNome());

        System.out.println("Cliente em atendimento: ID #" + clienteAtendido.getCodigo() + " - " + clienteAtendido.getNome());
        
        System.out.println("\nClientes ainda aguardando na fila:");
        for (Cliente c : filaAtendimento) {
                System.out.println("- ID #" + c.getCodigo() + ": " + c.getNome());
        }
        
        // PARTE 2: Catálogo de Produtos com INSTANCIAÇÃO DIRETA
        Produto livro = new ProdutoFisico("Livro Harry Potter", 90.0, 15.0);
        Produto ebook = new ProdutoDigital("E-book Pokemon", 45.0, "QR-CODE-12345");
        Produto caderno = new ProdutoFisico("Caderno Voltas as Aulas", 30.0, 5.0);

        Map<Integer, Produto> catalogo = new HashMap<>();
        
        catalogo.put(livro.getCodigo(), livro);
        catalogo.put(ebook.getCodigo(), ebook);
        catalogo.put(caderno.getCodigo(), caderno);

        System.out.println("\n------------------------------------------");
        System.out.println("CONSULTA DE PRODUTOS NO CATÁLOGO");

        int[] codigosParaBuscar = {livro.getCodigo(), ebook.getCodigo(), 999}; 

        for (int codigo : codigosParaBuscar) {
                System.out.println("\nBuscando produto com código: " + codigo);
                
                if (catalogo.containsKey(codigo)) {
                        Produto produtoEncontrado = catalogo.get(codigo);
                        System.out.println("Produto encontrado!");
                        System.out.println("Nome: " + produtoEncontrado.nome);
                        System.out.println("Preço Final: R$ " + produtoEncontrado.calcularPrecoFinal()); 
                } else {
                        System.out.println("Erro: Produto com o código " + codigo + " não foi encontrado no catálogo.");
                }
        }

        double totalSimulado = livro.calcularPrecoFinal(); 
        historico.add("Consulta de catálogo realizada. Produto principal: " + livro.nome);

        // PARTE 3: Pagamento
        System.out.println("\n------------------------------------------");
        System.out.println("PROCESSAMENTO DO PAGAMENTO");
        PagamentoPix pagamento = new PagamentoPix(totalSimulado);
        pagamento.processar();
        System.out.println("Total final pago (com taxas se houver): R$ " + pagamento.calcularTotal());

        historico.add("Pagamento de R$ " + pagamento.calcularTotal() + " processado via Pix");
    }      
}
