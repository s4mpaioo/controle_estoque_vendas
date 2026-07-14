package br.com.stockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*CRUD*/

@Repository
public interface ProdutoRepository extends JpaRepository<br.com.stockup.model.Produto, Long> {

    List<br.com.stockup.model.Produto> findByNome(String nome); // aqui é onde faz a consulta

    boolean existsByReferencia(String referencia);

}
