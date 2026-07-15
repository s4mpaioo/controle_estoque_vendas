package br.com.stockup.service;

import br.com.stockup.dto.CadastroProduto;
import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.Produto;
import org.springframework.stereotype.Service;
import br.com.stockup.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(CadastroProduto dto) {
        if (produtoRepository.existsByReferenciaAndCor(dto.getReferencia(), dto.getCor())) {
            throw new RuntimeException("Já existe um produto com esta referência e cor.");
        }

        Produto produto = new Produto();
        produto.setReferencia(dto.getReferencia());
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setModelo(dto.getModelo());
        produto.setCor(dto.getCor());
        produto.setDescricao(dto.getDescricao());
        produtoRepository.save(produto);

    }

    public void editar(Long id, CadastroProduto dto) {
        validarProduto(dto);

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado.");
        }

        Produto produtoEncontrado = produto.get();

        Optional<Produto> produtoReferencia = produtoRepository.findByReferenciaAndCor(dto.getReferencia(), dto.getCor());

        if (produtoReferencia.isPresent()
                && !produtoReferencia.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um produto com esta referência e cor.");
        }

        produtoEncontrado.setReferencia(dto.getReferencia());
        produtoEncontrado.setNome(dto.getNome());
        produtoEncontrado.setMarca(dto.getMarca());
        produtoEncontrado.setModelo(dto.getModelo());
        produtoEncontrado.setCor(dto.getCor());
        produtoEncontrado.setDescricao(dto.getDescricao());

        produtoRepository.save(produtoEncontrado);
    }

    public void excluir(Long id) {
        Optional <Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new  RuntimeException("Produto não encontrado.");
        }

        Produto produtoEncontrado = produto.get();

        for (EstoqueProduto estoque : produtoEncontrado.getEstoques()) {
            if(estoque.getQuantidadeTotalPares() != null && estoque.getQuantidadeTotalPares() > 0) {
                    throw new RuntimeException("Não é possível excluir um produto que possui estoque cadastrado.");
            }
        }

        produtoRepository.delete(produtoEncontrado);
    }

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);

        if(produtos.isEmpty()){
            throw new RuntimeException("Nenhum produto encontrado.");
        }

        return produtos;
    }

    private void validarProduto(CadastroProduto dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RuntimeException("O nome é obrigatório.");
        }
        if (dto.getReferencia()  == null || dto.getReferencia().isBlank()) {
            throw new RuntimeException("A referência é obrigatória.");
        }
        if (dto.getMarca() == null || dto.getMarca().isBlank()) {
            throw new RuntimeException("A marca é obrigatória.");
        }
        if(dto.getModelo() == null) {
            throw new RuntimeException("O modelo é obrigatório.");
        }
        if(dto.getCor() == null || dto.getCor().isBlank()) {
            throw new RuntimeException("A cor é obrigatória.");
        }
    }
}
