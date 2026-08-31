package br.com.stockup.service;

import br.com.stockup.dto.CadastroProdutoDTO;
import br.com.stockup.enums.StatusProduto;
import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.EstoqueTamanho;
import br.com.stockup.model.Loja;
import br.com.stockup.model.Produto;
import br.com.stockup.repository.LojaRepository;
import org.springframework.stereotype.Service;
import br.com.stockup.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final LojaRepository lojaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, LojaRepository lojaRepository) {
        this.produtoRepository = produtoRepository;
        this.lojaRepository = lojaRepository;
    }

    @Override
    public void cadastrarProduto(CadastroProdutoDTO cadastroProdutoDTO) {
        Loja loja = lojaRepository.findById(cadastroProdutoDTO.getLojaId())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada."));

        if(produtoRepository.existsByReferenciaAndCor(cadastroProdutoDTO.getReferencia(), cadastroProdutoDTO.getCor())) {
            throw new RuntimeException("Já existe um produto com esta referência e cor.");
        }

        Produto produto = criarProduto(cadastroProdutoDTO, loja);

        produtoRepository.save(produto);
    }

    private Produto criarProduto(CadastroProdutoDTO cadastroProdutoDTO, Loja loja) {
        Produto produto = new Produto();

        produto.setReferencia(cadastroProdutoDTO.getReferencia());
        produto.setNome(cadastroProdutoDTO.getNome());
        produto.setMarca(cadastroProdutoDTO.getMarca());
        produto.setModelo(cadastroProdutoDTO.getModelo());
        produto.setCor(cadastroProdutoDTO.getCor());
        produto.setDescricao(cadastroProdutoDTO.getDescricao());

        produto.setLoja(loja);

        return produto;
    }

    @Override
    public void editar(Long id, CadastroProdutoDTO cadastroProdutoDTO) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado.");
        }

        Produto produtoEncontrado = produto.get();

        Optional<Produto> produtoReferencia = produtoRepository.findByReferenciaAndCor(cadastroProdutoDTO.getReferencia(), cadastroProdutoDTO.getCor());

        if (produtoReferencia.isPresent() && !produtoReferencia.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um produto com esta referência e cor.");
        }

        produtoEncontrado.setReferencia(cadastroProdutoDTO.getReferencia());
        produtoEncontrado.setNome(cadastroProdutoDTO.getNome());
        produtoEncontrado.setMarca(cadastroProdutoDTO.getMarca());
        produtoEncontrado.setModelo(cadastroProdutoDTO.getModelo());
        produtoEncontrado.setCor(cadastroProdutoDTO.getCor());
        produtoEncontrado.setDescricao(cadastroProdutoDTO.getDescricao());

        produtoRepository.save(produtoEncontrado);
    }

    @Override
    public void excluir(Long id) {
        Optional <Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new  RuntimeException("Produto não encontrado.");
        }

        Produto produtoEncontrado = produto.get();

        validarProdutoSemEstoque(produtoEncontrado);

        produtoEncontrado.setExcluido(true);

        produtoRepository.save(produtoEncontrado);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        if(nome == null || nome.isBlank()) {
            throw new RuntimeException("Informe o nome do produto.");
        }

        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);

        if(produtos.isEmpty()){
            throw new RuntimeException("Nenhum produto encontrado.");
        }

        return produtos;
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findByExcluidoFalse();
    }

    private void validarProdutoSemEstoque(Produto produto) {
        for (EstoqueProduto estoque : produto.getEstoques()) {
            for (EstoqueTamanho tamanho : estoque.getQuantidadePorTamanho()) {
                if (tamanho.getQuantidade() != null && tamanho.getQuantidade() > 0) {
                    throw new RuntimeException("Não é possível excluir um produto que possui estoque cadastrado.");
                }
            }
        }
    }
}
