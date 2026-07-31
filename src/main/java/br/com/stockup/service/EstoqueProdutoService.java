package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueAtacado;
import br.com.stockup.dto.CadastroEstoqueVarejo;
import br.com.stockup.enums.TipoEstoque;
import br.com.stockup.enums.TipoLoja;
import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.repository.EstoqueProdutoRepository;
import br.com.stockup.repository.EstoqueTamanhoRepository;
import br.com.stockup.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EstoqueProdutoService {

    private final EstoqueProdutoRepository estoqueProdutoRepository;
    private final EstoqueTamanhoRepository estoqueTamanhoRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueProdutoService(EstoqueProdutoRepository estoqueProdutoRepository,
                                 EstoqueTamanhoRepository estoqueTamanhoRepository,
                                 ProdutoRepository produtoRepository) {
        this.estoqueProdutoRepository = estoqueProdutoRepository;
        this.estoqueTamanhoRepository = estoqueTamanhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarAtacado(CadastroEstoqueAtacado dto) {
       validarCadastroAtacado(dto);

        Produto produto = produtoRepository.findById(dto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(dto.getPrecoVenda());
        estoqueProduto.setTipoEstoque(TipoEstoque.FICHA);
        estoqueProduto.setFicha(dto.getFicha());
        estoqueProduto.setQuantidadeFichas(dto.getQuantidadeFichas());
        estoqueProduto.setProduto(produto);

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroAtacado(CadastroEstoqueAtacado dto) {
        if(dto.getProdutoId() == null) {
            throw new RuntimeException("O produto é obrigatório.");
        }
        if(!produtoRepository.existsById(dto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if(dto.getFicha() == null) {
            throw new RuntimeException("Ficha é obrigatória.");
        }
        if(dto.getQuantidadeFichas() == null || dto.getQuantidadeFichas() <= 0) {
            throw new RuntimeException("A quantidade de fichas é obrigatória, e deve ser maior que zero.");
        }
        if(dto.getPrecoCusto() == null || dto.getPrecoCusto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço de custo é obrigatório, e deve ser maior que zero.");
        }
        if(dto.getPrecoVenda() == null || dto.getPrecoVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço de venda é obrigatório, e deve ser maior que zero.");
        }
        if(dto.getPrecoVenda().compareTo(dto.getPrecoCusto()) < 0) {
            throw new RuntimeException("Preço de venda não pode ser menor que o preço de custo.");
        }
    }

    public void cadastrarVarejo(CadastroEstoqueVarejo dto) {
        validarCadastroVarejo(dto);

        Produto produto = produtoRepository.findById(dto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(dto.getPrecoVenda());
        estoqueProduto.setPrecoCusto(dto.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.PAR);

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroVarejo(CadastroEstoqueVarejo dto) {

        if (dto.getProdutoId() == null) {
            throw new RuntimeException("O produto é obrigatório.");
        }
        if (!produtoRepository.existsById(dto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if (dto.getPrecoCusto() == null || dto.getPrecoCusto().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException("O preço de custo é obrigatório e deve ser maior que zero.");
        }
        if (dto.getPrecoVenda() == null || dto.getPrecoVenda().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException("O preço de venda é obrigatório e deve ser maior que zero.");
        }
        if (dto.getPrecoVenda().compareTo(dto.getPrecoCusto()) < 0) {
            throw new RuntimeException("O preço de venda não pode ser menor que o preço de custo.");
        }
    }
}

