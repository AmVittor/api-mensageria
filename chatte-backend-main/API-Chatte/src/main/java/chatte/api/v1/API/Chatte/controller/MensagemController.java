package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.GrupoSubject;
import chatte.api.v1.API.Chatte.entities.Mensagem;
import chatte.api.v1.API.Chatte.repositories.MensagemRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mensagem Rest Controller", description = "Responsável por gerir mensagens nesse projeto")
@RestController
@RequestMapping("/api/v1/mensagens")
public class MensagemController {

    @Autowired
    MensagemRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message/{idGroup}")
    @SendTo("/chatte/reply/{idGroup}")
    public void enviarMensagem(@DestinationVariable Integer idGroup, @Payload Mensagem mensagem) {
        System.out.println("mensagem enviada! " + mensagem.getConteudo() + " " + mensagem.getHorario() + " privada: " + mensagem.isPrivado() + mensagem.getFkUsuario());
        messagingTemplate.convertAndSend("/chatte/reply/" + idGroup, mensagem.getConteudo());
        repository.save(mensagem);
        GrupoSubject subject = new GrupoSubject(idGroup);
        subject.notificarObservers(mensagem);
    }
}
