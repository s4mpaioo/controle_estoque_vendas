package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueAtacado;
import br.com.stockup.dto.CadastroEstoqueTamanho;
import br.com.stockup.dto.CadastroEstoqueVarejo;
import br.com.stockup.enums.TipoEstoque;
import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.repository.EstoqueProdutoRepository;
import br.com.stockup.repository.EstoqueTamanhoRepository;
import br.com.stockup.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public void cadastrarAtacado(CadastroEstoqueAtacado cadastroEstoqueAtacadoDto) {
        validarCadastroAtacado(cadastroEstoqueAtacadoDto);

        Produto produto = produtoRepository.findById(cadastroEstoqueAtacadoDto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueAtacadoDto.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueAtacadoDto.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.FICHA);
        estoqueProduto.setFicha(cadastroEstoqueAtacadoDto.getFicha());
        estoqueProduto.setQuantidadeFichas(cadastroEstoqueAtacadoDto.getQuantidadeFichas());

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroAtacado(CadastroEstoqueAtacado cadastroEstoqueAtacadoDto) {
        if(cadastroEstoqueAtacadoDto.getProdutoId() == null) {
            throw new RuntimeException("O produto é obrigatório.");
        }
        if(!produtoRepository.existsById(cadastroEstoqueAtacadoDto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if(cadastroEstoqueAtacadoDto.getFicha() == null) {
            throw new RuntimeException("Ficha é obrigatória.");
        }
        if(cadastroEstoqueAtacadoDto.getQuantidadeFichas() == null || cadastroEstoqueAtacadoDto.getQuantidadeFichas() <= 0) {
            throw new RuntimeException("A quantidade de fichas é obrigatória, e deve ser maior que zero.");
        }
        if(cadastroEstoqueAtacadoDto.getPrecoCusto() == null || cadastroEstoqueAtacadoDto.getPrecoCusto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço de custo é obrigatório, e deve ser maior que zero.");
        }
        if(cadastroEstoqueAtacadoDto.getPrecoVenda() == null || cadastroEstoqueAtacadoDto.getPrecoVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço de venda é obrigatório, e deve ser maior que zero.");
        }
        if(cadastroEstoqueAtacadoDto.getPrecoVenda().compareTo(cadastroEstoqueAtacadoDto.getPrecoCusto()) <= 0) {
            throw new RuntimeException("Preço de venda não pode ser menor ou igual ao preço de custo.");
        }
    }

    public void cadastrarVarejo(CadastroEstoqueVarejo cadastroEstoqueVarejoDto) {
        validarCadastroVarejo(cadastroEstoqueVarejoDto);

        Produto produto = produtoRepository.findById(cadastroEstoqueVarejoDto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueVarejoDto.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueVarejoDto.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.PAR);

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroVarejo(CadastroEstoqueVarejo cadastroEstoqueVarejoDto) {
        if (cadastroEstoqueVarejoDto.getProdutoId() == null) {
            throw new RuntimeException("O produto é obrigatório.");
        }
        if (!produtoRepository.existsById(cadastroEstoqueVarejoDto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if (cadastroEstoqueVarejoDto.getPrecoCusto() == null || cadastroEstoqueVarejoDto.getPrecoCusto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O preço de custo é obrigatório e deve ser maior que zero.");
        }
        if (cadastroEstoqueVarejoDto.getPrecoVenda() == null || cadastroEstoqueVarejoDto.getPrecoVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O preço de venda é obrigatório e deve ser maior que zero.");
        }
        if (cadastroEstoqueVarejoDto.getPrecoVenda().compareTo(cadastroEstoqueVarejoDto.getPrecoCusto()) <= 0) {
            throw new RuntimeException("O preço de venda não pode ser menor ou igual ao preço de custo.");
        }

        validarTamanhos(cadastroEstoqueVarejoDto.getQuantidadePorTamanho());

        validarQuantidadeTamanho(cadastroEstoqueVarejoDto.getQuantidadePorTamanho());
    }

    private void validarTamanhos(List<CadastroEstoqueTamanho> tamanhosDto) {
        if(tamanhosDto == null || tamanhosDto.isEmpty()) {
            throw new RuntimeException("Informe um tamanho.");
        }
        for(int i = 0; i < tamanhosDto.size(); i++) {
            CadastroEstoqueTamanho tamanho = tamanhosDto.get(i);
            if(tamanho.getTamanho() == null) {
                throw new RuntimeException("O tamanho é obrigatório.");
            }
        }
    }

    private void validarQuantidadeTamanho(List<CadastroEstoqueTamanho> tamanhosDto) {
        if(tamanhosDto == null || tamanhosDto.isEmpty()) {
            throw new RuntimeException("Informe uma quantidade.");
        }
        for (int i = 0; i < tamanhosDto.size(); i++) {
            CadastroEstoqueTamanho tamanho = tamanhosDto.get(i);

            if(tamanho.getQuantidade() == null || tamanho.getQuantidade() < 0){
                throw new RuntimeException("A quantidade deve ser informada e não pode ser negativa.");
            }
        }
    }
}

