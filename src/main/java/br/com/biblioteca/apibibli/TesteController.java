// Define o pacote da aplicação, organizando os controladores de teste
package br.com.biblioteca.apibibli;

// Importa a anotação Controller do Spring MVC, que marca a classe como controlador web
import org.springframework.stereotype.Controller;
// Importa a interface Model do Spring MVC, usada para passar dados para as views
import org.springframework.ui.Model;
// Importa a anotação GetMapping, que mapeia requisições HTTP GET para métodos
import org.springframework.web.bind.annotation.GetMapping;

// Importa a classe Arrays do Java, usada para manipulação de arrays
import java.util.Arrays;
// Importa a interface List do Java, usada para coleções ordenadas
import java.util.List;

// Anotação que marca esta classe como um controlador Spring MVC, usado para testes
@Controller
// Classe controladora para testes, exibindo dados mockados de inventário
public class TesteController {

    // Anotação que mapeia requisições GET para "/inventario-teste" a este método
    @GetMapping("/inventario-teste")
    // Método que exibe a página de teste de inventário com dados mockados
    public String inventarioTeste(Model model) {
        // Cria uma lista de livros mockados para teste
        List<Livro> livros = Arrays.asList(
            // Cria e adiciona livro mockado 1
            criarLivroMock("Java Programming", "John Doe", "12345", "RFID123"),
            // Cria e adiciona livro mockado 2
            criarLivroMock("Spring Boot Guide", "Jane Smith", "12346", "RFID124"),
            // Cria e adiciona livro mockado 3
            criarLivroMock("Database Design", "Bob Johnson", "12347", null),
            // Cria e adiciona livro mockado 4
            criarLivroMock("Web Development", "Alice Brown", "12348", "RFID125")
        );
        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);
        // Retorna o nome da view "inventario"
        return "inventario";
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
