package br.com.biblioteca.apibibli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacaoAluguel(String emailDestinatario, String nomeAluno, String nomeLivro,
                                       String dataAluguel, String dataDevolucao) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("softagcorporation@gmail.com");
            mailMessage.setTo(emailDestinatario);
            mailMessage.setSubject("Confirmação de Aluguel - Biblioteca");

            String corpoMensagem = String.format(
                "Olá %s,\n\n" +
                "Seu aluguel foi confirmado com sucesso!\n\n" +
                "Detalhes do aluguel:\n" +
                "Livro: %s\n" +
                "Data de aluguel: %s\n" +
                "Data de devolução: %s\n\n" +
                "Lembre-se de devolver o livro até a data especificada para evitar multas.\n\n" +
                "Atenciosamente,\n" +
                "Equipe da Biblioteca",
                nomeAluno, nomeLivro, dataAluguel, dataDevolucao
            );

            mailMessage.setText(corpoMensagem);

            mailSender.send(mailMessage);
            System.out.println("Email de confirmação enviado para: " + emailDestinatario);
        } catch (Exception e) {
            System.err.println("Erro ao enviar email de confirmação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
