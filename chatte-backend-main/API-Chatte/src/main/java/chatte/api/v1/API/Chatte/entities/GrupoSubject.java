package chatte.api.v1.API.Chatte.entities;

import chatte.api.v1.API.Chatte.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GrupoSubject {
    @Autowired
    GrupoRepository grupoRepository;

    private List<UsuarioObserver> subjects = new ArrayList<>();

    private Integer idGrupo;

    public GrupoSubject(Integer idGrupo) {
        this.idGrupo = idGrupo;
        setSubjects();
    }

    private void setSubjects() {
        List<Usuario> usuarios = grupoRepository.findAllUsers(idGrupo);

        if(!usuarios.isEmpty()) {
            for (Usuario u : usuarios) {
                subjects.add(new UsuarioObserver(u.getEmail()));
            }
        }
    }

    public void notificarObservers(Mensagem m) {
        if(!subjects.isEmpty()) {
            for (UsuarioObserver u: subjects) {
                u.notificarMensagem(m);
            }
        }
    }
}
