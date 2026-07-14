package br.com.stockup.service;

import br.com.stockup.dto.CadastroProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.stockup.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(CadastroProduto dto) {
        if (produtoRepository.existsByReferencia(dto.getReferencia())) {
            throw new RuntimeException("Produto já cadastrado, deseja realmente acrescentar neste produto?");
        }
        Produto produto = new Produto();
        produto.setReferencia(dto.getReferencia());
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setModelo(dto.getModelo());
        produto.setDescricao(dto.getDescricao());
        produtoRepository.save(produto);

    }

    public void editar(Long id, CadastroProduto dto) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado.");
        }

        Produto produtoEncontrado = produto.get();
        produtoEncontrado.setReferencia(dto.getReferencia());
        produtoEncontrado.setNome(dto.getNome());
        produtoEncontrado.setMarca(dto.getMarca());
        produtoEncontrado.setModelo(dto.getModelo());
        produtoEncontrado.setDescricao(dto.getDescricao());

        produtoRepository.save(produtoEncontrado);
    }

    public void excluir(Long id) {

    }

    public List<Produto> buscarPorNome(String nome) {
        return null;
    }
}
