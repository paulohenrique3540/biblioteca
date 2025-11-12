package br.com.biblioteca.apibibli;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    // Additional query methods can be added here if needed
}
