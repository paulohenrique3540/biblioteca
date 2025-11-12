// Define o pacote da aplicação, organizando os controladores
package br.com.biblioteca.apibibli;

// Importa a anotação Autowired do Spring, usada para injeção de dependências
import org.springframework.beans.factory.annotation.Autowired;
// Importa a anotação Controller do Spring MVC, que marca a classe como controlador web
import org.springframework.stereotype.Controller;
// Importa a interface Model do Spring MVC, usada para passar dados para as views
import org.springframework.ui.Model;
// Importa a anotação GetMapping, que mapeia requisições HTTP GET para métodos
import org.springframework.web.bind.annotation.GetMapping;
// Importa a anotação PostMapping, que mapeia requisições HTTP POST para métodos
import org.springframework.web.bind.annotation.PostMapping;
// Importa a anotação PathVariable, que extrai valores da URI
import org.springframework.web.bind.annotation.PathVariable;
// Importa a anotação ModelAttribute, que vincula parâmetros de requisição a objetos
import org.springframework.web.bind.annotation.ModelAttribute;
// Importa a anotação RequestParam, que extrai parâmetros de consulta da requisição
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Importa a classe Arrays do Java, usada para manipulação de arrays
import java.util.Arrays;
// Importa a interface List do Java, usada para coleções ordenadas
import java.util.List;
// Importa LocalDate para datas de aluguel
import java.time.LocalDate;
// Importa a entidade Aluguel
import br.com.biblioteca.apibibli.Aluguel;

// Anotação que marca esta classe como um controlador Spring MVC, responsável por lidar com requisições web
@Controller
// Classe controladora para operações de cadastro, listagem, busca, edição e exclusão de livros
public class CadastroController {

    // Anotação que injeta automaticamente uma instância de LivroRepository
    @Autowired
    // Repositório para operações CRUD em livros
    private LivroRepository livroRepository;

    // Anotação que injeta automaticamente uma instância de BibliotecaRepository
    @Autowired
    // Repositório para operações CRUD em bibliotecas
    private BibliotecaRepository bibliotecaRepository;

    // Anotação que injeta automaticamente uma instância de AluguelRepository
    @Autowired
    // Repositório para operações CRUD em alugueis
    private AluguelRepository aluguelRepository;

    // Anotação que injeta automaticamente uma instância de AlunoRepository
    @Autowired
    // Repositório para operações CRUD em alunos
    private AlunoRepository alunoRepository;

    // Anotação que injeta automaticamente uma instância de EmailService
    @Autowired
    // Serviço para envio de emails
    private EmailService emailService;

    // Anotação que mapeia requisições GET para "/cadastro" a este método
    @GetMapping("/cadastro")
    // Método que exibe o formulário de cadastro de livro
    public String cadastro(@RequestParam(value = "rfid", required = false) String rfid, Model model) {
        // Cria um novo objeto Livro
        Livro livro = new Livro();
        // Se RFID fornecido, pré-preenche o campo
        if (rfid != null && !rfid.trim().isEmpty()) {
            livro.setCodigoRfid(rfid);
        }
        // Adiciona o objeto Livro ao modelo para o formulário
        model.addAttribute("livro", livro);
        // Busca todas as bibliotecas no repositório
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        // Adiciona a lista de bibliotecas ao modelo
        model.addAttribute("bibliotecas", bibliotecas);
        // Retorna o nome da view "cadastro" para renderização
        return "cadastro";
    }

    // Anotação que mapeia requisições POST para "/cadastro" a este método
    @PostMapping("/cadastro")
    // Método que salva um novo livro no banco de dados
    public String salvarLivro(@ModelAttribute Livro livro) {
        // Salva o livro usando o repositório
        livroRepository.save(livro);
        // Redireciona para a página de cadastro após salvar
        return "redirect:/cadastro";
    }

    // Anotação que mapeia requisições GET para "/listar-livros" a este método
    @GetMapping("/listar-livros")
    // Método que lista todos os livros cadastrados
    public String listarLivros(Model model) {
        // Busca todos os livros no repositório
        List<Livro> livros = livroRepository.findAll();
        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);
        // Retorna o nome da view "listar-livros" para renderização
        return "listar-livros";
    }

    // Anotação que mapeia requisições GET para "/listar-alunos" a este método
    @GetMapping("/listar-alunos")
    // Método que lista todos os alunos cadastrados
    public String listarAlunos(Model model) {
        // Busca todos os alunos no repositório
        List<Aluno> alunos = alunoRepository.findAll();
        // Adiciona a lista de alunos ao modelo
        model.addAttribute("alunos", alunos);
        // Retorna o nome da view "listar-alunos" para renderização
        return "listar-alunos";
    }

    // Anotação que mapeia requisições GET para "/buscar-livros" a este método
    @GetMapping("/buscar-livros")
    // Método que busca livros por termo de pesquisa
    public String buscarLivros(@RequestParam(value = "q", required = false) String query, Model model) {
        // Declara variável para armazenar a lista de livros
        List<Livro> livros;

        // Verifica se o termo de busca não é nulo e não está vazio
        if (query != null && !query.trim().isEmpty()) {
            // Busca livros por título contendo o termo, ignorando maiúsculas/minúsculas
            livros = livroRepository.findByNomeLivroContainingIgnoreCase(query);
            // Busca livros por autor contendo o termo
            List<Livro> livrosPorAutor = livroRepository.findByNomeAutorContainingIgnoreCase(query);
            // Busca livros por número de tombo contendo o termo
            List<Livro> livrosPorTombo = livroRepository.findByNumeroTomboContainingIgnoreCase(query);
            // Busca livros por código RFID contendo o termo
            List<Livro> livrosPorRfid = livroRepository.findByCodigoRfidContainingIgnoreCase(query);

            // Adiciona os resultados de autor à lista principal
            livros.addAll(livrosPorAutor);
            // Adiciona os resultados de tombo à lista principal
            livros.addAll(livrosPorTombo);
            // Adiciona os resultados de RFID à lista principal
            livros.addAll(livrosPorRfid);

            // Remove duplicatas da lista usando stream e distinct
            livros = livros.stream().distinct().toList();

            // Adiciona o termo de busca ao modelo para exibição
            model.addAttribute("termoBusca", query);
        } else {
            // Se não há termo de busca, busca todos os livros
            livros = livroRepository.findAll();
        }

        // Adiciona a lista de livros ao modelo
        model.addAttribute("livros", livros);
        // Retorna a view de listagem de livros
        return "listar-livros";
    }

    // Anotação que mapeia requisições GET para "/editar/{id}" a este método
    @GetMapping("/editar/{id}")
    // Método que exibe o formulário de edição de um livro específico
    public String editarLivro(@PathVariable Long id, Model model) {
        // Busca o livro pelo ID, retornando null se não encontrado
        Livro livro = livroRepository.findById(id).orElse(null);
        // Se o livro não existe, redireciona para a listagem
        if (livro == null) {
            return "redirect:/listar-livros";
        }
        // Adiciona o livro ao modelo para edição
        model.addAttribute("livro", livro);
        // Busca todas as bibliotecas para o dropdown
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        // Imprime no console o número de bibliotecas encontradas para debug
        System.out.println("Número de bibliotecas encontradas no /editar/" + id + ": " + bibliotecas.size());
        // Adiciona as bibliotecas ao modelo
        model.addAttribute("bibliotecas", bibliotecas);
        // Retorna a view de edição
        return "editar-livro";
    }

    // Anotação que mapeia requisições POST para "/editar/{id}" a este método
    @PostMapping("/editar/{id}")
    // Método que salva as edições feitas em um livro
    public String salvarEdicaoLivro(@PathVariable Long id, @ModelAttribute Livro livroAtualizado) {
        // Busca o livro existente pelo ID
        Livro livroExistente = livroRepository.findById(id).orElse(null);
        // Se o livro existe, atualiza seus campos
        if (livroExistente != null) {
            // Atualiza o número de tombo
            livroExistente.setNumeroTombo(livroAtualizado.getNumeroTombo());
            // Atualiza o nome do autor
            livroExistente.setNomeAutor(livroAtualizado.getNomeAutor());
            // Atualiza o nome do livro
            livroExistente.setNomeLivro(livroAtualizado.getNomeLivro());
            // Atualiza o código RFID
            livroExistente.setCodigoRfid(livroAtualizado.getCodigoRfid());
            // Atualiza a classificação indicativa
            livroExistente.setClassificacao(livroAtualizado.getClassificacao());
            // Atualiza a biblioteca associada
            livroExistente.setBiblioteca(livroAtualizado.getBiblioteca());
            // Salva as alterações no repositório
            livroRepository.save(livroExistente);
        }
        // Redireciona para a listagem após salvar
        return "redirect:/listar-livros";
    }

    // Anotação que mapeia requisições GET para "/excluir/{id}" a este método
    @GetMapping("/excluir/{id}")
    // Método que exclui um livro pelo ID
    public String excluirLivro(@PathVariable Long id) {
        // Exclui o livro do repositório pelo ID
        livroRepository.deleteById(id);
        // Redireciona para a listagem após exclusão
        return "redirect:/listar-livros";
    }

    // Anotação que mapeia requisições GET para "/inventario" a este método
    @GetMapping("/inventario")
    // Método que exibe a página de inventário com todos os livros
    public String inventario(Model model) {
        // Busca todos os livros para o inventário
        List<Livro> livros = livroRepository.findAll();
        // Adiciona os livros ao modelo
        model.addAttribute("livros", livros);
        // Retorna a view de inventário
        return "inventario";
    }

    // Anotação que mapeia requisições GET para "/alugar-livros" a este método
    @GetMapping("/alugar-livros")
    // Método que lista livros disponíveis para aluguel ou histórico de alugueis com busca
    public String alugarLivros(@RequestParam(value = "view", required = false, defaultValue = "disponiveis") String view,
                               @RequestParam(value = "q", required = false) String query, Model model) {
        if ("historico".equals(view)) {
            // Mostrar histórico de alugueis
            List<Aluguel> alugueis = aluguelRepository.findAll();

            // Aplicar filtro se query fornecida
            if (query != null && !query.trim().isEmpty()) {
                alugueis = alugueis.stream()
                    .filter(aluguel -> aluguel.getNomeLivro().toLowerCase().contains(query.toLowerCase()) ||
                                       aluguel.getNumeroTombo().toLowerCase().contains(query.toLowerCase()) ||
                                       aluguel.getNomeAluno().toLowerCase().contains(query.toLowerCase()) ||
                                       aluguel.getCpfAluno().toLowerCase().contains(query.toLowerCase()))
                    .toList();
                model.addAttribute("termoBusca", query);
            }

            model.addAttribute("alugueis", alugueis);
            model.addAttribute("view", "historico");
        } else {
            // Mostrar livros alugados
            List<Livro> todosLivros = livroRepository.findAll();
            List<Livro> livrosAlugados = todosLivros.stream()
                .filter(livro -> livro.isAlugado())
                .toList();

            // Aplicar filtro se query fornecida
            if (query != null && !query.trim().isEmpty()) {
                livrosAlugados = livrosAlugados.stream()
                    .filter(livro -> livro.getNomeLivro().toLowerCase().contains(query.toLowerCase()) ||
                                     livro.getNomeAutor().toLowerCase().contains(query.toLowerCase()) ||
                                     livro.getNumeroTombo().toLowerCase().contains(query.toLowerCase()) ||
                                     (livro.getCodigoRfid() != null && livro.getCodigoRfid().toLowerCase().contains(query.toLowerCase())))
                    .toList();
                model.addAttribute("termoBusca", query);
            }

            model.addAttribute("livros", livrosAlugados);
            model.addAttribute("view", "disponiveis");
        }
        // Retorna a view de aluguel de livros
        return "alugar-livros";
    }

    // Anotação que mapeia requisições GET para "/alugar/{id}" a este método
    @GetMapping("/alugar/{id}")
    // Método que exibe a página de confirmação de aluguel de um livro
    public String alugarLivro(@PathVariable Long id, Model model) {
        // Busca o livro pelo ID
        Livro livro = livroRepository.findById(id).orElse(null);
        // Se não encontrado ou já alugado, redireciona
        if (livro == null || livro.isAlugado()) {
            return "redirect:/alugar-livros";
        }
        // Adiciona ao modelo
        model.addAttribute("livro", livro);
        // Retorna a view de aluguel individual
        return "alugar-livro";
    }

    // Anotação que mapeia requisições POST para "/alugar/{id}" a este método
    @PostMapping("/alugar/{id}")
    // Método que processa o aluguel de um livro
    public String processarAluguel(@PathVariable Long id, @RequestParam String nomeAluno, @RequestParam String cpfAluno) {
        // Busca o livro
        Livro livro = livroRepository.findById(id).orElse(null);
        // Se encontrado e não alugado
        if (livro != null && !livro.isAlugado()) {
            // Marca como alugado
            livro.setAlugado(true);
            // Define data de aluguel como hoje
            livro.setDataAluguel(LocalDate.now());
            // Define data de devolução como 14 dias depois
            livro.setDataDevolucao(LocalDate.now().plusDays(14));
            // Salva
            livroRepository.save(livro);

            // Cria registro de aluguel para histórico
            Aluguel aluguel = new Aluguel();
            aluguel.setDataAluguel(LocalDate.now());
            aluguel.setNomeLivro(livro.getNomeLivro());
            aluguel.setNumeroTombo(livro.getNumeroTombo());
            aluguel.setNomeAluno(nomeAluno);
            aluguel.setCpfAluno(cpfAluno);
            aluguel.setDataDevolucao(LocalDate.now().plusDays(14));
            aluguelRepository.save(aluguel);

            // Busca o aluno pelo CPF para obter o email
            Aluno aluno = alunoRepository.findByCpfContainingIgnoreCase(cpfAluno).stream()
                .filter(a -> a.getCpf().equalsIgnoreCase(cpfAluno))
                .findFirst().orElse(null);

            // Envia email de confirmação se o aluno foi encontrado e tem email
            if (aluno != null && aluno.getEmail() != null && !aluno.getEmail().trim().isEmpty()) {
                emailService.enviarConfirmacaoAluguel(
                    aluno.getEmail(),
                    nomeAluno,
                    livro.getNomeLivro(),
                    LocalDate.now().toString(),
                    LocalDate.now().plusDays(14).toString()
                );
            }
        }
        // Redireciona para a lista de aluguel
        return "redirect:/alugar-livros";
    }

    // Anotação que mapeia requisições POST para "/devolver/{id}" a este método
    @PostMapping("/devolver/{id}")
    // Método que processa a devolução de um livro
    public String devolverLivro(@PathVariable Long id) {
        // Busca o livro
        Livro livro = livroRepository.findById(id).orElse(null);
        // Se encontrado e alugado
        if (livro != null && livro.isAlugado()) {
            // Marca como não alugado
            livro.setAlugado(false);
            // Limpa datas
            livro.setDataAluguel(null);
            livro.setDataDevolucao(null);
            // Salva
            livroRepository.save(livro);
        }
        // Redireciona para a lista de aluguel
        return "redirect:/alugar-livros";
    }

    // Anotação que mapeia requisições GET para "/alugar-form" a este método
    @GetMapping("/alugar-form")
    // Método que exibe o formulário de aluguel de livro
    public String alugarForm(@RequestParam(value = "numeroTombo", required = false) String numeroTombo, Model model) {
        // Busca todas as bibliotecas
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        model.addAttribute("bibliotecas", bibliotecas);

        // Se numeroTombo fornecido, busca o livro
        if (numeroTombo != null && !numeroTombo.trim().isEmpty()) {
            Livro livro = livroRepository.findByNumeroTomboContainingIgnoreCase(numeroTombo).stream()
                .filter(l -> l.getNumeroTombo().equalsIgnoreCase(numeroTombo))
                .findFirst().orElse(null);
            model.addAttribute("livro", livro);
            model.addAttribute("numeroTombo", numeroTombo);
        }

        // Retorna o nome da view "alugar" para renderização
        return "alugar";
    }

    // Anotação que mapeia requisições POST para "/alugar-form" a este método
    @PostMapping("/alugar-form")
    // Método que processa o aluguel de um livro via formulário
    public String processarAluguelForm(@RequestParam String numeroTombo, @RequestParam String nomeAluno, @RequestParam String cpfAluno, @RequestParam String dataNascimento, @RequestParam String dataDevolucao, @RequestParam Long bibliotecaId) {
        // Busca o livro pelo número de tombo
        Livro livro = livroRepository.findByNumeroTomboContainingIgnoreCase(numeroTombo).stream()
            .filter(l -> l.getNumeroTombo().equalsIgnoreCase(numeroTombo))
            .findFirst().orElse(null);
        // Se não encontrado ou já alugado, redireciona de volta
        if (livro == null || livro.isAlugado()) {
            return "redirect:/alugar-form";
        }
        // Marca como alugado
        livro.setAlugado(true);
        // Define data de aluguel como hoje
        livro.setDataAluguel(LocalDate.now());
        // Define data de devolução como especificada pelo usuário
        livro.setDataDevolucao(LocalDate.parse(dataDevolucao));
        // Salva
        livroRepository.save(livro);

        // Busca a biblioteca
        Biblioteca biblioteca = bibliotecaRepository.findById(bibliotecaId).orElse(null);

        // Cria registro de aluguel para histórico
        Aluguel aluguel = new Aluguel();
        aluguel.setDataAluguel(LocalDate.now());
        aluguel.setNomeLivro(livro.getNomeLivro());
        aluguel.setNumeroTombo(livro.getNumeroTombo());
        aluguel.setNomeAluno(nomeAluno);
        aluguel.setCpfAluno(cpfAluno);
        aluguel.setDataDevolucao(LocalDate.parse(dataDevolucao));
        aluguel.setDataNascimento(LocalDate.parse(dataNascimento));
        aluguel.setNomeAutor(livro.getNomeAutor());
        aluguel.setBiblioteca(biblioteca);
        aluguelRepository.save(aluguel);

        // Busca o aluno pelo CPF para obter o email
        Aluno aluno = alunoRepository.findByCpfContainingIgnoreCase(cpfAluno).stream()
            .filter(a -> a.getCpf().equalsIgnoreCase(cpfAluno))
            .findFirst().orElse(null);

        // Envia email de confirmação se o aluno foi encontrado e tem email
        if (aluno != null && aluno.getEmail() != null && !aluno.getEmail().trim().isEmpty()) {
            emailService.enviarConfirmacaoAluguel(
                aluno.getEmail(),
                nomeAluno,
                livro.getNomeLivro(),
                LocalDate.now().toString(),
                LocalDate.parse(dataDevolucao).toString()
            );
        }
        // Redireciona para a lista de aluguel
        return "redirect:/alugar-livros";
    }

    // Anotação que mapeia requisições GET para "/cadastro-aluno" a este método
    @GetMapping("/cadastro-aluno")
    // Método que exibe o formulário de cadastro de aluno
    public String cadastroAluno(Model model) {
        // Adiciona um novo objeto Aluno ao modelo para o formulário
        model.addAttribute("aluno", new Aluno());
        // Retorna o nome da view "cadastro-aluno" para renderização
        return "cadastro-aluno";
    }

    // Anotação que mapeia requisições POST para "/cadastro-aluno" a este método
    @PostMapping("/cadastro-aluno")
    // Método que salva um novo aluno no banco de dados
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        // Salva o aluno usando o repositório
        alunoRepository.save(aluno);
        // Redireciona para a listagem de alunos após salvar
        return "redirect:/listar-alunos";
    }

    // Anotação que mapeia requisições GET para "/api/aluno-por-rfid" a este método
    @GetMapping("/api/aluno-por-rfid")
    @ResponseBody
    // Método que busca um aluno pelo RFID e retorna como JSON
    public Aluno buscarAlunoPorRfid(@RequestParam String rfid) {
        // Busca alunos pelo RFID (usando containing para flexibilidade)
        List<Aluno> alunos = alunoRepository.findByRfidContainingIgnoreCase(rfid);
        // Filtra para encontrar RFID exato (ignorando maiúsculas/minúsculas)
        return alunos.stream()
            .filter(aluno -> aluno.getRfid().equalsIgnoreCase(rfid))
            .findFirst()
            .orElse(null);
    }

    // Anotação que mapeia requisições GET para "/api/aluno-por-cpf" a este método
    @GetMapping("/api/aluno-por-cpf")
    @ResponseBody
    // Método que busca um aluno pelo CPF e retorna como JSON
    public Aluno buscarAlunoPorCpf(@RequestParam String cpf) {
        // Busca alunos pelo CPF (usando containing para flexibilidade)
        List<Aluno> alunos = alunoRepository.findByCpfContainingIgnoreCase(cpf);
        // Filtra para encontrar CPF exato (ignorando maiúsculas/minúsculas)
        return alunos.stream()
            .filter(aluno -> aluno.getCpf().equalsIgnoreCase(cpf))
            .findFirst()
            .orElse(null);
    }

    // Anotação que mapeia requisições GET para "/api/livro-por-rfid" a este método
    @GetMapping("/api/livro-por-rfid")
    @ResponseBody
    // Método que busca um livro pelo RFID e retorna como JSON
    public Livro buscarLivroPorRfid(@RequestParam String rfid) {
        // Busca livros pelo RFID (usando containing para flexibilidade)
        List<Livro> livros = livroRepository.findByCodigoRfidContainingIgnoreCase(rfid);
        // Filtra para encontrar RFID exato (ignorando maiúsculas/minúsculas)
        return livros.stream()
            .filter(livro -> livro.getCodigoRfid().equalsIgnoreCase(rfid))
            .findFirst()
            .orElse(null);
    }

    // Anotação que mapeia requisições GET para "/api/livro-por-numeroTombo" a este método
    @GetMapping("/api/livro-por-numeroTombo")
    @ResponseBody
    // Método que busca um livro pelo número de tombo e retorna como JSON
    public Livro buscarLivroPorNumeroTombo(@RequestParam String numeroTombo) {
        // Busca livros pelo número de tombo (usando containing para flexibilidade)
        List<Livro> livros = livroRepository.findByNumeroTomboContainingIgnoreCase(numeroTombo);
        // Filtra para encontrar número de tombo exato (ignorando maiúsculas/minúsculas)
        return livros.stream()
            .filter(livro -> livro.getNumeroTombo().equalsIgnoreCase(numeroTombo))
            .findFirst()
            .orElse(null);
    }

    // Anotação que mapeia requisições GET para "/editar-aluno/{id}" a este método
    @GetMapping("/editar-aluno/{id}")
    // Método que exibe o formulário de edição de um aluno específico
    public String editarAluno(@PathVariable Long id, Model model) {
        // Busca o aluno pelo ID, retornando null se não encontrado
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        // Se o aluno não existe, redireciona para a listagem
        if (aluno == null) {
            return "redirect:/listar-alunos";
        }
        // Adiciona o aluno ao modelo para edição
        model.addAttribute("aluno", aluno);
        // Retorna a view de edição
        return "editar-aluno";
    }

    // Anotação que mapeia requisições POST para "/editar-aluno/{id}" a este método
    @PostMapping("/editar-aluno/{id}")
    // Método que salva as edições feitas em um aluno
    public String salvarEdicaoAluno(@PathVariable Long id, @ModelAttribute Aluno alunoAtualizado) {
        // Busca o aluno existente pelo ID
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        // Se o aluno existe, atualiza seus campos
        if (alunoExistente != null) {
            // Atualiza o nome
            alunoExistente.setNome(alunoAtualizado.getNome());
            // Atualiza o CPF
            alunoExistente.setCpf(alunoAtualizado.getCpf());
            // Atualiza o email
            alunoExistente.setEmail(alunoAtualizado.getEmail());
            // Atualiza a data de nascimento
            alunoExistente.setDataNascimento(alunoAtualizado.getDataNascimento());
            // Atualiza o RFID
            alunoExistente.setRfid(alunoAtualizado.getRfid());
            // Salva as alterações no repositório
            alunoRepository.save(alunoExistente);
        }
        // Redireciona para a listagem de alunos após salvar
        return "redirect:/listar-alunos";
    }

    // Anotação que mapeia requisições POST para "/excluir-aluno/{id}" a este método
    @PostMapping("/excluir-aluno/{id}")
    // Método que exclui um aluno pelo ID
    public String excluirAluno(@PathVariable Long id) {
        // Exclui o aluno do repositório pelo ID
        alunoRepository.deleteById(id);
        // Redireciona para a listagem de alunos após exclusão
        return "redirect:/listar-alunos";
    }
}
