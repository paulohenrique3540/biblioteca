// Define o pacote da aplicação, organizando os controladores de inventário
package br.com.biblioteca.apibibli;

// Importa a anotação Autowired do Spring, usada para injeção de dependências
import org.springframework.beans.factory.annotation.Autowired;
// Importa a anotação Controller do Spring MVC, que marca a classe como controlador web
import org.springframework.stereotype.Controller;
// Importa a interface Model do Spring MVC, usada para passar dados para as views
import org.springframework.ui.Model;
// Importa a anotação GetMapping, que mapeia requisições HTTP GET para métodos
import org.springframework.web.bind.annotation.GetMapping;

// Importa a interface List do Java, usada para coleções ordenadas
import java.util.List;
// Importa a classe Arrays do Java, usada para manipulação de arrays
import java.util.Arrays;

// Anotação que marca esta classe como um controlador Spring MVC, versão resiliente do controlador de inventário
@Controller
// Classe controladora resiliente para gerenciamento de inventário de livros, com fallback para dados mockados
public class InventarioControllerResiliente {

    // Anotação que injeta automaticamente uma instância de LivroRepository, com required=false para permitir null
    @Autowired(required = false)
    // Repositório para operações CRUD em livros, pode ser null se o banco não estiver disponível
    private LivroRepository livroRepository;

    // Anotação que mapeia requisições GET para "/inventario-resiliente" a este método
    @GetMapping("/inventario-resiliente")
    // Método que exibe a página resiliente de controle de inventário
    public String inventarioControle(Model model) {
        // Declara variável para armazenar a lista de livros
        List<Livro> livros;

        // Tenta buscar dados do banco
        try {
            // Verifica se o repositório está disponível
            if (livroRepository != null) {
                // Busca todos os livros no repositório
                livros = livroRepository.findAll();
                // Verifica se a lista não é nula ou vazia
                if (livros == null || livros.isEmpty()) {
                    // Lança exceção se a lista estiver vazia
                    throw new RuntimeException("Lista vazia");
                }
            } else {
                // Lança exceção se o repositório não estiver disponível
                throw new RuntimeException("Repository não disponível");
            }
        } catch (Exception e) {
            // Em caso de erro, usa dados mockados para demonstração
            System.out.println("Usando dados mockados para inventário: " + e.getMessage());
            // Cria lista de livros mockados
            livros = criarLivrosMockados();
        }

        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);
        // Retorna o nome da view "inventario-controle"
        return "inventario-controle";
    }

    // Método privado que cria uma lista de livros mockados para demonstração
    private List<Livro> criarLivrosMockados() {
        // Retorna uma lista imutável de livros mockados usando Arrays.asList
        return Arrays.asList(
            // Cria e adiciona livro mockado 1
            criarLivroMock("Java Programming", "John Doe", "12345", "RFID123"),
            // Cria e adiciona livro mockado 2
            criarLivroMock("Spring Boot Guide", "Jane Smith", "12346", "RFID124"),
            // Cria e adiciona livro mockado 3
            criarLivroMock("Database Design", "Bob Johnson", "12347", null),
            // Cria e adiciona livro mockado 4
            criarLivroMock("Web Development", "Alice Brown", "12348", "RFID125")
        );
    }

    // Método privado que cria um objeto Livro mockado com os parâmetros fornecidos
    private Livro criarLivroMock(String titulo, String autor, String tombo, String rfid) {
        // Cria uma nova instância de Livro
        Livro livro = new Livro();
        // Define o nome do livro
        livro.setNomeLivro(titulo);
        // Define o nome do autor
        livro.setNomeAutor(autor);
        // Define o número de tombo
        livro.setNumeroTombo(tombo);
        // Define o código RFID
        livro.setCodigoRfid(rfid);
        // Retorna o livro criado
        return livro;
    }
}
