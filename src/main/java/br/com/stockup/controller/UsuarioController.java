package br.com.stockup.controller;

import br.com.stockup.dto.CadastroUsuario;
import br.com.stockup.dto.LoginUsuario;
import br.com.stockup.repository.UsuarioRepository;
import br.com.stockup.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //responde requisições http
@RequestMapping ("/usuarios") //tudo que é relacionado a usuarios
public class UsuarioController {

    private final UsuarioService usuarioService; // aqui é como se a controler pedisse ajuda a service

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody CadastroUsuario dto) {
        usuarioService.cadastrar(dto);
    }

    @PostMapping("/login")
    public ResponseEntity <String> login(@RequestBody LoginUsuario dto) {
        usuarioService.login(dto);
        return ResponseEntity.ok("Login realizado com sucesso.");
    }
}
