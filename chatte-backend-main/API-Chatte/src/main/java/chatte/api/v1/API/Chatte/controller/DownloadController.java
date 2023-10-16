package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import chatte.api.v1.API.Chatte.services.DownloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Tag(name = "Download Rest Controller", description = "Responsável por gerir downloads nesse projeto")

@RestController
@RequestMapping("api/v1/downloads")
public class DownloadController {

    private static final String SERVER_LOCATION = "";
    private static String EXTENSION = ".";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DownloadService downloadService;

    @Operation(
            summary = "Método responsável por gerar um arquivo csv com as informações em ordem alfabética",
            description = "Esse método é responsável por gravar um arquivo csv a partir de uma consulta feita na base " +
                    "de dados, recebe como parâmetro o nome que o arquivo será salvo, a extensão do arquivo e qual a ordem " +
                    "da ordenação (crescente ou decrescente) e utiliza um algoritimo de ordenação " +
                    "para ordenar as informações do arquivo pelo nome do usuário em ordem alfabética crescente." +
                    "\nPode retornar os seguintes status http: 500, 200"
    )
    @PostMapping("/{fileName}/{extensao}/{ordenacao}")
    public ResponseEntity gravaArquivoCsv(
            @PathVariable String fileName,
            @PathVariable String extensao,
            @PathVariable String ordenacao) {
        if (fileName == null || extensao == null || ordenacao == null) {
            return ResponseEntity.status(400).build();
        } else if (fileName.isEmpty() || extensao.isEmpty() || ordenacao.isEmpty()) {
            return ResponseEntity.status(400).build();
        } else if (!extensao.equals("csv") || !extensao.equals("txt") || !extensao.equals("xls")){
            return ResponseEntity.status(400).build();
        } else {
        downloadService.gravaArquivoCsv(fileName, extensao, ordenacao);
        return ResponseEntity.status(200).build();
        }
    }

    @Operation(
            summary = "Método responsável por fazer o download do arquivo gerado",
            description = "Esse método faz uma busca a partir do nome recebido como parâmetro e monta uma resposta que " +
                    "faz o arquivo ser baixado automaticamente no navegador" +
                    "\nPode retornar os seguintes status http: 500, 200"
    )
    @GetMapping("/{fileName:.+}/{extension}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String fileName, @PathVariable String extension) throws IOException {
        EXTENSION += extension;
        File file = new File(SERVER_LOCATION + fileName + EXTENSION);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName() + "");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);

        } catch (NoSuchFileException e) {
            System.out.println("Arquivo não encontrado");
            System.out.println("stacktrace: " + Arrays.toString(e.getStackTrace()));
            System.out.println("message: " + e.getMessage());
            return ResponseEntity.status(404).build();
        } finally {
            EXTENSION = ".";
        }
    }
}
