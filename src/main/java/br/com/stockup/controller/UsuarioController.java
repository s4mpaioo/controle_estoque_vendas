package br.com.stockup.controller;

import br.com.stockup.dto.*;
import br.com.stockup.dto.RedefinirSenha;
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

    @PostMapping("/redefinir-senha")
    public ResponseEntity <String> redefinirSenha(@RequestBody RedefinirSenha dto) {
        usuarioService.redefinirSenha(dto);
        return ResponseEntity.ok("O código foi enviado para seu email.");
    }

    @PostMapping("validar-codigo")
    public ResponseEntity <String> validarCodigo(@RequestBody ValidarCodigo dto) {
        usuarioService.validarCodigo(dto);
        return ResponseEntity.ok("Código válido.");
    }

    @PostMapping("nova-senha")
    public ResponseEntity <String> novaSenha(@RequestBody NovaSenha dto) {
        usuarioService.novaSenha(dto);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
