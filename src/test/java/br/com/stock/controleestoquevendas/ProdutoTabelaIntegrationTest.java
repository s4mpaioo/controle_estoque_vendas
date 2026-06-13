package br.com.stock.controleestoquevendas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProdutoTabelaIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
