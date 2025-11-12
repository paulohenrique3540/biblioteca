package br.com.biblioteca.apibibli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        // If not found by username, try by email
        if (!usuarioOpt.isPresent()) {
            usuarioOpt = usuarioRepository.findByEmail(username);
        }

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                // Login bem-sucedido
                session.setAttribute("usuarioLogado", usuario);
                return "redirect:/listar-livros";
            } else {
                // Senha incorreta
                model.addAttribute("error", "Usuário/Email ou senha inválidos");
            }
        } else {
            // Usuário não encontrado
            model.addAttribute("error", "Usuário/Email ou senha inválidos");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
