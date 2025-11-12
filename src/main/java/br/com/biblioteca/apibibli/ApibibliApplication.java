// Define o pacote da aplicação, organizando as classes em br.com.biblioteca.apibibli
package br.com.biblioteca.apibibli;

// Importa a classe SpringApplication, necessária para iniciar a aplicação Spring Boot
import org.springframework.boot.SpringApplication;
// Importa a anotação SpringBootApplication, que combina várias configurações do Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Anotação que habilita a auto-configuração, varredura de componentes e configuração baseada em propriedades para a aplicação Spring Boot
@SpringBootApplication
// Classe principal que serve como ponto de entrada para a aplicação API Bibli, gerenciando livros e bibliotecas
public class ApibibliApplication {

	// Método main, executado quando a aplicação é iniciada, responsável por lançar o contexto Spring
	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot, criando o contexto de aplicação e configurando todos os beans e componentes
		SpringApplication.run(ApibibliApplication.class, args);
	}

}
