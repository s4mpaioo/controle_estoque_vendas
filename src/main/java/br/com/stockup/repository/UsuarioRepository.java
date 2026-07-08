package br.com.stockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<br.com.stockup.model.Usuario, Long> {

    boolean existsByEmail(String email);
}
