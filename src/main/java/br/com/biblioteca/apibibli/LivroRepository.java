// Define o pacote da aplicação, organizando as interfaces de repositório
package br.com.biblioteca.apibibli;

// Importa a interface JpaRepository do Spring Data JPA, que fornece métodos CRUD para entidades
import org.springframework.data.jpa.repository.JpaRepository;
// Importa a interface List do Java, usada para coleções ordenadas
import java.util.List;

// Interface que define o repositório para a entidade Livro, herdando operações padrão do JPA e adicionando consultas customizadas
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Método para buscar livros por nome contendo a string, ignorando maiúsculas/minúsculas
    List<Livro> findByNomeLivroContainingIgnoreCase(String nomeLivro);
    // Método para buscar livros por nome do autor contendo a string, ignorando maiúsculas/minúsculas
    List<Livro> findByNomeAutorContainingIgnoreCase(String nomeAutor);
    // Método para buscar livros por número de tombo contendo a string, ignorando maiúsculas/minúsculas
    List<Livro> findByNumeroTomboContainingIgnoreCase(String numeroTombo);
    // Método para buscar livros por código RFID contendo a string, ignorando maiúsculas/minúsculas
    List<Livro> findByCodigoRfidContainingIgnoreCase(String codigoRfid);
    // Método para verificar se existe um livro com o código RFID fornecido
    boolean existsByCodigoRfid(String codigoRfid);
}
