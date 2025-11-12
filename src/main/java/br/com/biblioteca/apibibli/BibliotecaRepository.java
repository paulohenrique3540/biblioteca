// Define o pacote da aplicação, organizando as interfaces de repositório
package br.com.biblioteca.apibibli;

// Importa a interface JpaRepository do Spring Data JPA, que fornece métodos CRUD para entidades
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que define o repositório para a entidade Biblioteca, herdando operações padrão do JPA
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
