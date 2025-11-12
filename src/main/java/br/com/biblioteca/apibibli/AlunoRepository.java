package br.com.biblioteca.apibibli;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByCpfContainingIgnoreCase(String cpf);
    List<Aluno> findByRfidContainingIgnoreCase(String rfid);
}
