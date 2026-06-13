package br.com.stock.controleestoquevendas.repositories;

import br.com.stock.controleestoquevendas.domains.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
