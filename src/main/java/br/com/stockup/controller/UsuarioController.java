package br.com.stockup.controller;

import br.com.stockup.dto.*;
import br.com.stockup.dto.RedefinirSenha;
import br.com.stockup.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void cadastrar(@RequestBody CadastroUsuario cadastroUsuario) {
        usuarioService.cadastrar(cadastroUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUsuario loginUsuario) {
        LoginResponse response = usuarioService.login(loginUsuario);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@RequestBody RedefinirSenha redefinirSenha) {
        usuarioService.redefinirSenha(redefinirSenha);
        return ResponseEntity.ok("O código foi enviado para seu email.");
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<String> validarCodigo(@RequestBody ValidarCodigo validarCodigo) {
        usuarioService.validarCodigo(validarCodigo);
        return ResponseEntity.ok("Código válido.");
    }

    @PostMapping("/nova-senha")
    public ResponseEntity<String> novaSenha(@RequestBody NovaSenha novaSenha) {
        usuarioService.novaSenha(novaSenha);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
