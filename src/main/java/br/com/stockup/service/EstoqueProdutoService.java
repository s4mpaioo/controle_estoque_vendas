package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueAtacado;
import br.com.stockup.dto.CadastroEstoqueVarejo;
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

    }
}

