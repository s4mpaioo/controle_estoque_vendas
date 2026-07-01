package br.com.stockup.services;

import br.com.stockup.domains.entity.Produto;
import br.com.stockup.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service

public class ProdutoService {
    public final ProdutoRepository produtoRepository; //service recebe o repository

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
