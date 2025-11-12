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
// Importa a anotação ResponseBody, que indica que o retorno deve ser serializado diretamente na resposta
import org.springframework.web.bind.annotation.ResponseBody;
// Importa a anotação RequestMapping, que mapeia requisições para métodos
import org.springframework.web.bind.annotation.RequestMapping;
// Importa a anotação RequestParam, que extrai parâmetros de consulta da requisição
import org.springframework.web.bind.annotation.RequestParam;

// Importa a interface List do Java, usada para coleções ordenadas
import java.util.List;
// Importa a classe Arrays do Java, usada para manipulação de arrays
import java.util.Arrays;

// Anotação que marca esta classe como um controlador Spring MVC, responsável por operações de inventário
@Controller
// Classe controladora para gerenciamento de inventário de livros, com fallback para dados mockados
public class InventarioController {

    // Anotação que injeta automaticamente uma instância de LivroRepository, com required=false para permitir null
    @Autowired(required = false)
    // Repositório para operações CRUD em livros, pode ser null se o banco não estiver disponível
    private LivroRepository livroRepository;

    // Anotação que mapeia requisições GET para "/inventario-controle" a este método
    @GetMapping("/inventario-controle")
    // Método que exibe a página de controle de inventário
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

    // Anotação que mapeia requisições GET para "/inventario-controle-demo" a este método
    @GetMapping("/inventario-controle-demo")
    // Método que exibe a página de demonstração de controle de inventário
    public String inventarioControleDemo(Model model) {
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
            System.out.println("Usando dados mockados para inventário demo: " + e.getMessage());
            // Cria lista de livros mockados
            livros = criarLivrosMockados();
        }

        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);
        // Adiciona o total de livros ao modelo
        model.addAttribute("totalLivros", livros.size());
        // Retorna o nome da view "inventario-controle-demo"
        return "inventario-controle-demo";
    }

    // Anotação que mapeia requisições para "/api/livros" a este método
    @RequestMapping("/api/livros")
    // Anotação que indica que o retorno deve ser serializado diretamente na resposta HTTP
    @ResponseBody
    // Método que retorna a lista de livros via API REST
    public List<Livro> listarLivrosApi() {
        // Tenta buscar dados do banco
        try {
            // Verifica se o repositório está disponível
            if (livroRepository != null) {
                // Busca todos os livros no repositório
                List<Livro> livros = livroRepository.findAll();
                // Verifica se a lista não é nula ou vazia
                if (livros == null || livros.isEmpty()) {
                    // Lança exceção se a lista estiver vazia
                    throw new RuntimeException("Lista vazia");
                }
                // Retorna a lista de livros
                return livros;
            } else {
                // Lança exceção se o repositório não estiver disponível
                throw new RuntimeException("Repository não disponível");
            }
        } catch (Exception e) {
            // Em caso de erro, imprime mensagem e retorna dados mockados
            System.out.println("Erro ao buscar livros via API: " + e.getMessage());
            // Retorna lista de livros mockados
            return criarLivrosMockados();
        }
    }

    // Anotação que mapeia requisições GET para "/api/verificar-rfid" a este método
    @GetMapping("/api/verificar-rfid")
    // Anotação que indica que o retorno deve ser serializado diretamente na resposta HTTP
    @ResponseBody
    // Método que verifica se um código RFID existe
    public boolean verificarRfid(@RequestParam String rfid) {
        // Tenta verificar no banco
        try {
            // Verifica se o repositório está disponível
            if (livroRepository != null) {
                // Verifica se existe um livro com o RFID fornecido
                return livroRepository.existsByCodigoRfid(rfid);
            } else {
                // Para dados mockados, verifica se algum livro mockado tem o RFID
                return criarLivrosMockados().stream().anyMatch(l -> rfid.equals(l.getCodigoRfid()));
            }
        } catch (Exception e) {
            // Em caso de erro, imprime mensagem e retorna false
            System.out.println("Erro ao verificar RFID: " + e.getMessage());
            // Retorna false indicando que o RFID não foi encontrado
            return false;
        }
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
