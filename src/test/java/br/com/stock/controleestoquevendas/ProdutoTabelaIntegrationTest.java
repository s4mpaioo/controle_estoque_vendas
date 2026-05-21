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

    @Test
    void deveCriarTabelaProduto() {
        Integer quantidade = jdbcTemplate.queryForObject(
                """
                select count(*)
                from information_schema.tables
                where table_schema = 'public'
                  and table_name = 'produto'
                """,
                Integer.class
        );

        assertThat(quantidade).isEqualTo(1);
    }
}
