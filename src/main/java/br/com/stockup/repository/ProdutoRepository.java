package br.com.stockup.repository;

import br.com.stockup.enums.StatusProduto;
import br.com.stockup.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByReferencia(String referencia);
    boolean existsByReferenciaAndCor(String referencia, String cor);

    Optional<Produto> findByReferencia(String referencia);
    Optional<Produto> findByReferenciaAndCor(String referencia, String cor);

    Optional<Produto> findByIdAndExcluidoFalse(Long id);
    List<Produto> findByExcluidoFalse();

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByStatusNot(StatusProduto status);
}
