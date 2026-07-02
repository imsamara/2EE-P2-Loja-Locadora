package com.lojalocadora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import business.cruds.GerenciamentoCategoria;
import repositories.CategoriaRepositorio;
import entidades.Categoria;

public class GerenciamentoCategoriaTeste{
	
	@Test //Uma categoria está sendo cadastrada
	public void cadastrarFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		Categoria categoria = new Categoria (
				1,
				"Roupas",
				"Qualquer peça, tecido ou material utilizado para cobrir, proteger ou adornar o corpo humano"
				);
		
		boolean resultado = gerenciamento.cadastrarCategoria(categoria);

	    assertTrue(resultado);		
    }
	
	@Test //Duas categorias estão sendo cadastradas com o mesmo ID. Por isso, a segunda não será registrada
	public void cadastrarNaoFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		Categoria categoria1 = new Categoria (
				2,
				"Eletrônicos",
				"Dispositivos que utiliza circuitos elétricos e semicondutores para controlar e aproveitar o fluxo de elétrons"
				);
		
		Categoria categoria2 = new Categoria (
				2,
				"Livros",
				"Meios que transmitem ideias, histórias e conhecimentos"
				);
		
		boolean resultado1 = gerenciamento.cadastrarCategoria(categoria1);
		boolean resultado2 = gerenciamento.cadastrarCategoria(categoria2);

	    assertTrue(resultado1);		
	    assertTrue(resultado2);	
    }
	
	@Test //A categoria de ID = 1 está sendo procurada, e ela existe
	public void buscarFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		Categoria resultado = gerenciamento.buscarCategoria(1);
		
		assertNotNull(resultado);
	}
	
	@Test //A categoria de ID = 5 está sendo procurada, porém ela não existe
	public void buscarNaoFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		Categoria resultado = gerenciamento.buscarCategoria(5);
		
		assertNotNull(resultado);
	}
	
	@Test //Todas as categorias estão sendo listadas
	public void listar() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		List<Categoria> categorias = gerenciamento.listarCategorias();
		
		assertNotNull(categorias);
		assertFalse(categorias.isEmpty());
	}
	
	@Test //A categoria de ID = 1 está sendo atualizada, e a categoria existe
	public void atualizarFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamentoCategoria = new GerenciamentoCategoria(repositorio);
		
		Categoria categoriaAtualizada = new Categoria (
				1,
				"Roupas",
				"Peças para cobrir, proteger ou adornar o corpo humano"
				);
		
		boolean resultado = gerenciamentoCategoria.atualizarCategoria(categoriaAtualizada);
		assertTrue(resultado);		
	}
	
	@Test //A categoria de ID = 5 está sendo atualizada, porém a categoria não existe
	public void atualizarNaoFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamentoCategoria = new GerenciamentoCategoria(repositorio);
		
		Categoria categoriaAtualizada = new Categoria (
				5,
				"Calçados",
				"Serve principalmente para proteger, apoiar e dar conforto aos pés"
				);
		
		boolean resultado = gerenciamentoCategoria.atualizarCategoria(categoriaAtualizada);
		assertTrue(resultado);		
	}
	
	@Test
	public void desativarFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		boolean resultado = gerenciamento.desativarCategoria(1);
		
		assertTrue(resultado);
	}
	
	@Test
	public void desativarNaoFunciona() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		boolean resultado = gerenciamento.desativarCategoria(5);
		
		assertTrue(resultado);
	}
	
	@Test //O próximo ID da categoria está sendo gerado
	public void gerarProximoId() {
		CategoriaRepositorio repositorio = new CategoriaRepositorio();
		GerenciamentoCategoria gerenciamento = new GerenciamentoCategoria(repositorio);
		
		int maiorId = 0;

	    for (Categoria categoria : gerenciamento.listarCategorias()) {
	        if (categoria.getId() > maiorId) {
	            maiorId = categoria.getId();
	        }
	    }

	    int proximoId = gerenciamento.gerarProximoId();

	    assertEquals(maiorId + 1, proximoId);
	}
	
}