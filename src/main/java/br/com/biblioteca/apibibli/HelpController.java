package br.com.biblioteca.apibibli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HelpController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/ajuda")
    public String ajuda(Model model) {
        return "ajuda";
    }

    @PostMapping("/enviar-mensagem")
    public String enviarMensagem(@RequestParam String nome,
                                @RequestParam String email,
                                @RequestParam String assunto,
                                @RequestParam String mensagem,
                                RedirectAttributes redirectAttributes) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("softagcorporation@gmail.com");
            mailMessage.setTo("softagcorporation@gmail.com");
            mailMessage.setSubject("Ajuda - " + assunto);
            mailMessage.setText("Nome: " + nome + "\n" +
                               "Email: " + email + "\n\n" +
                               "Mensagem:\n" + mensagem);

            mailSender.send(mailMessage);

            redirectAttributes.addFlashAttribute("success", "Mensagem enviada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            redirectAttributes.addFlashAttribute("error", "Erro ao enviar mensagem: " + e.getMessage());
        }

        return "redirect:/ajuda";
    }
}
