// Define o pacote da aplicação, organizando as classes de entidade
package br.com.biblioteca.apibibli;

// Importa a anotação Entity do Jakarta Persistence, usada para marcar classes como entidades de banco de dados
import jakarta.persistence.Entity;
// Importa a anotação GeneratedValue, que especifica como o valor da chave primária é gerado
import jakarta.persistence.GeneratedValue;
// Importa a enumeração GenerationType, que define as estratégias de geração de ID
import jakarta.persistence.GenerationType;
// Importa a anotação Id, que marca o campo como chave primária da entidade
import jakarta.persistence.Id;
// Importa a anotação ManyToOne, que indica um relacionamento muitos-para-um
import jakarta.persistence.ManyToOne;
// Importa a anotação JoinColumn, que especifica a coluna de junção para o relacionamento
import jakarta.persistence.JoinColumn;
// Importa a anotação Table, que especifica o nome da tabela no banco de dados
import jakarta.persistence.Table;

// Anotação que indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados
@Entity
// Anotação que especifica o nome da tabela como "book"
@Table(name = "book")
// Classe que representa uma entidade Livro, contendo informações sobre um livro na biblioteca
public class Livro {

    // Anotação que marca o campo id como chave primária da entidade
    @Id
    // Anotação que especifica que o valor do id será gerado automaticamente pelo banco de dados usando identidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo que armazena o identificador único do livro, do tipo Long
    private Long id;

    // Campo que armazena o número de tombo do livro, do tipo String
    private String numeroTombo;
    // Campo que armazena o nome do autor do livro, do tipo String
    private String nomeAutor;
    // Campo que armazena o nome do livro, do tipo String
    private String nomeLivro;
// Campo que armazena o código RFID do livro, do tipo String
private String codigoRfid;
// Campo que armazena a classificação do livro, do tipo String
private String classificacao;

// Novos campos para aluguel
private boolean alugado;
private java.time.LocalDate dataAluguel;
private java.time.LocalDate dataDevolucao;

    // Anotação que indica um relacionamento muitos-para-um com a entidade Biblioteca
    @ManyToOne
    // Anotação que especifica a coluna de junção como "biblioteca_id"
    @JoinColumn(name = "biblioteca_id")
    // Campo que armazena a biblioteca associada ao livro
    private Biblioteca biblioteca;

    // Comentário indicando o início dos métodos getters e setters para acessar e modificar os campos
    // Método getter para obter o valor do campo id
    public Long getId() {
        // Retorna o valor do identificador do livro
        return id;
    }

    // Método setter para definir o valor do campo id
    public void setId(Long id) {
        // Atribui o valor passado ao campo id do livro
        this.id = id;
    }

    // Getters e setters para os novos campos de aluguel
    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public java.time.LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(java.time.LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public java.time.LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(java.time.LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Método getter para obter o valor do campo numeroTombo
    public String getNumeroTombo() {
        // Retorna o número de tombo do livro
        return numeroTombo;
    }

    // Método setter para definir o valor do campo numeroTombo
    public void setNumeroTombo(String numeroTombo) {
        // Atribui o valor passado ao campo numeroTombo do livro
        this.numeroTombo = numeroTombo;
    }

    // Método getter para obter o valor do campo nomeAutor
    public String getNomeAutor() {
        // Retorna o nome do autor do livro
        return nomeAutor;
    }

    // Método setter para definir o valor do campo nomeAutor
    public void setNomeAutor(String nomeAutor) {
        // Atribui o valor passado ao campo nomeAutor do livro
        this.nomeAutor = nomeAutor;
    }

    // Método getter para obter o valor do campo nomeLivro
    public String getNomeLivro() {
        // Retorna o nome do livro
        return nomeLivro;
    }

    // Método setter para definir o valor do campo nomeLivro
    public void setNomeLivro(String nomeLivro) {
        // Atribui o valor passado ao campo nomeLivro do livro
        this.nomeLivro = nomeLivro;
    }

    // Método getter para obter o valor do campo codigoRfid
    public String getCodigoRfid() {
        // Retorna o código RFID do livro
        return codigoRfid;
    }

    // Método setter para definir o valor do campo codigoRfid
    public void setCodigoRfid(String codigoRfid) {
        // Atribui o valor passado ao campo codigoRfid do livro
        this.codigoRfid = codigoRfid;
    }

    // Método getter para obter o valor do campo classificacao
    public String getClassificacao() {
        // Retorna a classificação do livro
        return classificacao;
    }

    // Método setter para definir o valor do campo classificacao
    public void setClassificacao(String classificacao) {
        // Atribui o valor passado ao campo classificacao do livro
        this.classificacao = classificacao;
    }

    // Método getter para obter o valor do campo biblioteca
    public Biblioteca getBiblioteca() {
        // Retorna a biblioteca associada ao livro
        return biblioteca;
    }

    // Método setter para definir o valor do campo biblioteca
    public void setBiblioteca(Biblioteca biblioteca) {
        // Atribui o valor passado ao campo biblioteca do livro
        this.biblioteca = biblioteca;
    }
}
