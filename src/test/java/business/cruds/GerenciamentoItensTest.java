package business.cruds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import entidades.Categoria;
import entidades.Fornecedor;
import entidades.Item;
import support.FakeItemRepositorio;

@DisplayName("GerenciamentoItens")
class GerenciamentoItensTest {

    private FakeItemRepositorio repositorio;
    private GerenciamentoItens gerenciamentoItens;

    private Categoria categoria;
    private Fornecedor fornecedor;

    @BeforeEach
    void setUp() {
        repositorio = new FakeItemRepositorio();
        gerenciamentoItens = new GerenciamentoItens(repositorio);

        categoria = new Categoria(1, "Ferramentas", "Ferramentas em geral");
        fornecedor = new Fornecedor(1, "Fornecedor XPTO", "00.000.000/0001-00", "contato@xpto.com", "8199999999");
    }

    private Item novoItem(int id) {
        return new Item(id, "Furadeira", "Furadeira de impacto", 20.0, "BOM", 500.0, categoria, fornecedor);
    }

    @Nested
    @DisplayName("cadastrarItem")
    class CadastrarItem {

        @Test
        @DisplayName("deve cadastrar um item novo e deixá-lo disponível")
        void deveCadastrarItemNovo() {
            Item item = novoItem(1);

            boolean resultado = gerenciamentoItens.cadastrarItem(item);

            assertTrue(resultado);
            assertTrue(item.estaDisponivel());
        }

        @Test
        @DisplayName("deve rejeitar quando já existe item com o mesmo id")
        void deveRejeitarIdDuplicado() {
            gerenciamentoItens.cadastrarItem(novoItem(1));

            boolean resultado = gerenciamentoItens.cadastrarItem(novoItem(1));

            assertFalse(resultado);
            assertEquals(1, gerenciamentoItens.listarItens().size());
        }
    }

    @Nested
    @DisplayName("buscarItem")
    class BuscarItem {

        @Test
        @DisplayName("deve retornar o item quando existir")
        void deveEncontrarItemExistente() {
            gerenciamentoItens.cadastrarItem(novoItem(1));

            assertNotNull(gerenciamentoItens.buscarItem(1));
        }

        @Test
        @DisplayName("deve retornar null quando não existir")
        void deveRetornarNullQuandoNaoExiste() {
            assertNull(gerenciamentoItens.buscarItem(999));
        }
    }

    @Nested
    @DisplayName("atualizarItem")
    class AtualizarItem {

        @Test
        @DisplayName("deve retornar false quando o item não existe")
        void deveRetornarFalseQuandoNaoExiste() {
            Item itemNovo = new Item(999, "Novo nome", "Nova descrição", 30.0, "OTIMO", 600.0, null, null);
            boolean resultado = gerenciamentoItens.atualizarItem(itemNovo);

            assertFalse(resultado);
        }

        @Test
        @DisplayName("deve atualizar todos os campos do item existente")
        void deveAtualizarCamposDoItem() {
            gerenciamentoItens.cadastrarItem(novoItem(1));

            Item itemNovo = new Item(1, "Serra Elétrica", "Serra circular", 35.0, "OTIMO", 800.0, categoria, fornecedor);
            boolean resultado = gerenciamentoItens.atualizarItem(itemNovo);

            Item atualizado = gerenciamentoItens.buscarItem(1);

            assertTrue(resultado);
            assertEquals("Serra Elétrica", atualizado.getNome());
            assertEquals("Serra circular", atualizado.getDescricao());
            assertEquals(35.0, atualizado.getTaxaDiaria(), 0.0001);
            assertEquals("OTIMO", atualizado.getEstadoConservacao());
            assertEquals(800.0, atualizado.getValorReposicao(), 0.0001);
        }
    }

    @Nested
    @DisplayName("excluirItem")
    class ExcluirItem {

        @Test
        @DisplayName("deve retornar false quando o item não existe")
        void deveRetornarFalseQuandoNaoExiste() {
            assertFalse(gerenciamentoItens.excluirItem(999));
        }

        @Test
        @DisplayName("deve desativar o item, tornando-o indisponível")
        void deveDesativarItem() {
            gerenciamentoItens.cadastrarItem(novoItem(1));

            boolean resultado = gerenciamentoItens.excluirItem(1);
            Item item = gerenciamentoItens.buscarItem(1);

            assertTrue(resultado);
            assertFalse(item.isAtivo());
            assertFalse(item.estaDisponivel(), "Item desativado não pode estar disponível para aluguel");
        }
    } 

    @Nested
    @DisplayName("gerarProximoId")
    class GerarProximoId {

        @Test
        @DisplayName("deve retornar 1 quando não há itens cadastrados")
        void devePrimeiroIdQuandoVazio() {
            assertEquals(1, gerenciamentoItens.gerarProximoId());
        }

        @Test
        @DisplayName("deve retornar o maior id cadastrado + 1")
        void deveIncrementarAPartirDoMaiorId() {
            gerenciamentoItens.cadastrarItem(novoItem(7));
            gerenciamentoItens.cadastrarItem(novoItem(3));

            assertEquals(8, gerenciamentoItens.gerarProximoId());
        }
    }
}
