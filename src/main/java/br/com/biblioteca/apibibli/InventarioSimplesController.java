// Define o pacote da aplicação, organizando os controladores de inventário
package br.com.biblioteca.apibibli;

// Importa a anotação Controller do Spring MVC, que marca a classe como controlador web
import org.springframework.stereotype.Controller;
// Importa a anotação GetMapping, que mapeia requisições HTTP GET para métodos
import org.springframework.web.bind.annotation.GetMapping;

// Anotação que marca esta classe como um controlador Spring MVC, versão simples do controlador de inventário
@Controller
// Classe controladora simples para exibir a página de inventário sem dados do banco
public class InventarioSimplesController {

    // Anotação que mapeia requisições GET para "/inventario-simples" a este método
    @GetMapping("/inventario-simples")
    // Método que exibe a página simples de inventário
    public String inventarioSimples() {
        // Para a versão simplificada, não precisamos de dados do banco
        // A página funciona com dados mockados no JavaScript
        // Retorna o nome da view "inventario-simples"
        return "inventario-simples";
    }
}
