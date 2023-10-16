package chatte.api.v1.API.Chatte.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class UsuarioObserver {
    @Autowired
    private JavaMailSender emailSender;

    private FilaObj<Mensagem> fila = new FilaObj<>(10);

    private String email;

    public UsuarioObserver(String email) {
        this.email = email;
    }

    public void notificarMensagem(Mensagem m) {
        fila.insert(m);

        enviarEmail(m);
    }

    private void enviarEmail(Mensagem m) {
        for (int i = 0; i < fila.getTamanho(); i++) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("teste@teste.com");
            message.setTo(email);
            message.setText(m.getConteudo());
            emailSender.send(message);
            fila.poll();
        }
    }
}
