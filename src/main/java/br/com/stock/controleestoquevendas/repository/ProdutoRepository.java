package br.com.stock.controleestoquevendas.repository;

import br.com.stock.controleestoquevendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
