// Define o pacote da aplicação, organizando as classes relacionadas à biblioteca
package br.com.biblioteca.apibibli;

// Importa a anotação Entity do Jakarta Persistence, usada para marcar classes como entidades de banco de dados
import jakarta.persistence.Entity;
// Importa a anotação GeneratedValue, que especifica como o valor da chave primária é gerado
import jakarta.persistence.GeneratedValue;
// Importa a enumeração GenerationType, que define as estratégias de geração de ID
import jakarta.persistence.GenerationType;
// Importa a anotação Id, que marca o campo como chave primária da entidade
import jakarta.persistence.Id;

// Anotação que indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Entity
// Classe que representa uma entidade Biblioteca, contendo informações sobre uma biblioteca no sistema
public class Biblioteca {

    // Anotação que marca o campo id como chave primária da entidade
    @Id
    // Anotação que especifica que o valor do id será gerado automaticamente pelo banco de dados usando identidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo que armazena o identificador único da biblioteca, do tipo Long
    private Long id;

    // Campo que armazena o nome da biblioteca, do tipo String
    private String nome;

    // Comentário indicando o início dos métodos getters e setters para acessar e modificar os campos
    // Método getter para obter o valor do campo id
    public Long getId() {
        // Retorna o valor do identificador da biblioteca
        return id;
    }

    // Método setter para definir o valor do campo id
    public void setId(Long id) {
        // Atribui o valor passado ao campo id da biblioteca
        this.id = id;
    }

    // Método getter para obter o valor do campo nome
    public String getNome() {
        // Retorna o nome da biblioteca
        return nome;
    }

    // Método setter para definir o valor do campo nome
    public void setNome(String nome) {
        // Atribui o valor passado ao campo nome da biblioteca
        this.nome = nome;
    }
}
