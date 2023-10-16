package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.PilhaObj;
import chatte.api.v1.API.Chatte.entities.Usuario;
import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/v1/uploads")
public class UploadController {
        @Autowired
        UsuarioRepository usuarioRepository;

        private PilhaObj<String> pilha = new PilhaObj<>(50);

        @PostMapping
        public ResponseEntity upload(@RequestParam("file") MultipartFile arquivoTXT) throws IOException {
                InputStream inputStream = arquivoTXT.getInputStream();
                Stream<String> teste = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines();

                for (String linha  : teste.collect(Collectors.toList())) {
                        String[] teste2 = linha.split(";");
                        Usuario u = new Usuario(
                                teste2[0],
                                teste2[1],
                                teste2[2],
                                teste2[3],
                                convertStringToBoolean(teste2[4]),
                                convertStringToNull(teste2[5])
                        );

                        usuarioRepository.save(u);
                }

                pilha.push(arquivoTXT.getOriginalFilename());
                return ResponseEntity.ok().build();
        }

        @GetMapping
        public ResponseEntity<List<String>> buscarUploads() {
                List<String> uploads = new ArrayList<>();

                while (!pilha.isEmpty()) {
                        uploads.add(pilha.pop());
                }

                return uploads.isEmpty() ?
                        ResponseEntity.noContent().build() :
                        ResponseEntity.ok(uploads);
        }
        private Boolean convertStringToBoolean(String string) {
                return string.equals("true");
        }

        private String convertStringToNull(String string) {
                return string.equals("null") ? null : string;
        }
}
