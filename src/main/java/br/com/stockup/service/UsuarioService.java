package br.com.stockup.service;

import br.com.stockup.dto.*;
import br.com.stockup.enums.PerfilUsuario;
import br.com.stockup.model.Loja;
import br.com.stockup.model.Usuario;
import br.com.stockup.repository.LojaRepository;
import br.com.stockup.repository.UsuarioRepository;
//import br.com.stockup.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Random;
import java.time.LocalDateTime;

import java.util.Optional;

@Service
public class UsuarioService {
    //conversa com o backend
    private final UsuarioRepository usuarioRepository;
    private final LojaRepository lojaRepository;
    private final EmailService emailService;

    //para o spring entregar os repositories
    public UsuarioService(UsuarioRepository usuarioRepository, LojaRepository lojaRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.lojaRepository = lojaRepository;
        this.emailService = emailService;
    }

    /*o que a service faz
    * o metodo cadastrar tem que receber o formulario que são as informações do front
    * */
    public void cadastrar(CadastroUsuario dto) {
        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(PerfilUsuario.PROPRIETARIO);

        usuarioRepository.save(usuario);

        Loja loja = new Loja();

        loja.setNome(dto.getNome());
        loja.setTipo(dto.getTipoLoja());
        loja.setUsuario(usuario);

        lojaRepository.save(loja);
    }

    public LoginResponse login(LoginUsuario dto) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        Usuario usuarioEncontrado = usuario.get();

        if (!dto.getSenha().equals(usuarioEncontrado.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(usuarioEncontrado.getId());
        loginResponse.setNome(usuarioEncontrado.getNome());
        loginResponse.setEmail(usuarioEncontrado.getEmail());

        return loginResponse;
    }

    public void redefinirSenha(RedefinirSenha dto) {
        Optional <Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
        if(usuario.isEmpty()) {
            throw new RuntimeException("Email não encontrado.");
        }

        Usuario usuarioEncontrado = usuario.get();
        Random random = new Random(); //usando a classe random para gerar o codigo de redefinir senha
        int codigo = random.nextInt(900000) + 100000;

        usuarioEncontrado.setCodigoRecuperacao(String.valueOf(codigo)); //converte de inteiro para string
        usuarioEncontrado.setExpiracaoCodigo(LocalDateTime.now().plusMinutes(10));

        usuarioRepository.save(usuarioEncontrado);

        emailService.enviarCodigoRecuperacao(usuarioEncontrado.getEmail(), usuarioEncontrado.getCodigoRecuperacao());
    }

    public void validarCodigo(ValidarCodigo dto) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Email não encontrado.");
        }

        Usuario usuarioEncontrado = usuario.get();

        // verifica se o código foi gerado
        if (usuarioEncontrado.getCodigoRecuperacao() == null || usuarioEncontrado.getExpiracaoCodigo() == null) {
            throw new RuntimeException("Nenhum código de recuperação foi solicitado para este email.");
        }

        // verifica se o código expirou
        if (LocalDateTime.now().isAfter(usuarioEncontrado.getExpiracaoCodigo())) {
            throw new RuntimeException("Código expirado.");
        }

        // verifica se o código informado esta correto
        if (!usuarioEncontrado.getCodigoRecuperacao().equals(dto.getCodigo())) {
            throw new RuntimeException("Código inválido.");
        }
    }

    public void novaSenha(NovaSenha dto) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Email não encontrado.");
        }

        Usuario usuarioEncontrado = usuario.get();

        if (usuarioEncontrado.getCodigoRecuperacao() == null || usuarioEncontrado.getExpiracaoCodigo() == null) {
            throw new RuntimeException("Nenhum código de recuperação foi solicitado para este email.");
        }

        if (LocalDateTime.now().isAfter(usuarioEncontrado.getExpiracaoCodigo())) {
            throw new RuntimeException("Código expirado.");
        }

        if (!usuarioEncontrado.getCodigoRecuperacao().equals(dto.getCodigo())) {
            throw new RuntimeException("Código inválido.");
        }

        if (dto.getNovaSenha() == null || dto.getConfirmarSenha() == null) {
            throw new RuntimeException("Preencha a nova senha.");
        }

        if (!dto.getNovaSenha().equals(dto.getConfirmarSenha())) {
            throw new RuntimeException("As senhas não coincidem.");
        }

        // Atualiza a senha com hash BCrypt
        usuarioEncontrado.setSenha((dto.getNovaSenha()));

        // Invalida o código de recuperação
        usuarioEncontrado.setCodigoRecuperacao(null);
        usuarioEncontrado.setExpiracaoCodigo(null);

        usuarioRepository.save(usuarioEncontrado);
    }
}