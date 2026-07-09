package br.com.stockup.service;

import br.com.stockup.dto.*;
import br.com.stockup.enums.PerfilUsuario;
import br.com.stockup.model.Loja;
import br.com.stockup.model.Usuario;
import br.com.stockup.repository.LojaRepository;
import br.com.stockup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
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
    public UsuarioService(UsuarioRepository usuarioRepository, LojaRepository lojaRepository, EmailService emailService) {
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
        //criar o usuario
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

    public void login(LoginUsuario dto) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail()); //procurar um usuario com esse email
        if(usuario.isEmpty()) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        Usuario usuarioEncontrado = usuario.get();

        if(!usuarioEncontrado.getSenha().equals(dto.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos.");
        }
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

        }

        public void novaSenha(NovaSenha dto) {

        }
    }
