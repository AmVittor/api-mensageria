package chatte.api.v1.API.Chatte.services;

import chatte.api.v1.API.Chatte.entities.Usuario;
import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class DownloadService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean gravaArquivoCsv(String fileName, String extensao, String ordenacao) {

        List<Usuario> lista = usuarioRepository.findAll();
        Usuario[] array = new Usuario[lista.size()];

        if(ordenacao.equals("crescente")) {
            ordenarCrescente(lista.toArray(array));
        } else if (ordenacao.equals("decrescente")) {
            ordenarDecrescente(lista.toArray(array));
        } else {
            return false;
        }

        FileWriter arq = null;
        Formatter saida = null;
        fileName += "." + extensao + "";

        try {
            arq = new FileWriter(fileName);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            return false;
        }

        try {
            for (Usuario u : array) {
                saida.format("%d;%s;%s;%s;%s;%b;%s\n", u.getIdUsuario(), u.getNome(),
                        u.getEmail(), u.getCargo(), u.getEmpresa(), u.isAdm(), u.getDtNascimento());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            return false;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
            }
        }

        return true;
    }

    private void ordenarCrescente(Usuario[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < v.length; j++) {
                if(v[j].getNome().compareTo(v[menor].getNome()) < 0) {
                    menor = j;
                }
            }
            Usuario aux = v[i];
            v[i] = v[menor];
            v[menor] = aux;
        }
    }

    private void ordenarDecrescente(Usuario[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int maior = i;
            for (int j = i + 1; j < v.length; j++) {
                if(v[j].getNome().compareTo(v[maior].getNome()) > 0) {
                    maior = j;
                }
            }
            Usuario aux = v[i];
            v[i] = v[maior];
            v[maior] = aux;
        }
    }

}
