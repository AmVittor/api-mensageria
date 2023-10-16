package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import chatte.api.v1.API.Chatte.services.DownloadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DownloadControllerTest {

    DownloadService downloadService;
    @Autowired
    DownloadController downloadController;

    @MockBean
    UsuarioRepository usuarioRepository;


    @Test
    void download() {
    }
}