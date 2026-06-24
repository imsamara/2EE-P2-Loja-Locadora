package main;

import apresentacao.MenuPrincipal;
import business.cruds.GerenciamentoContratos;
import business.cruds.GerenciamentoItens;
import business.cruds.GerenciamentoUsuarios;
import business.relatorios.GeradorRelatorios;
import facade.SistemaFacade;
import persistencia.SalvaArquivoContratos;
import persistencia.SalvaArquivoItens;
import persistencia.SalvaArquivoUsuarios;
import repositories.CategoriaRepositorio;
import repositories.ContratoRepositorio;
import repositories.FornecedorRepositorio;
import repositories.ItemRepositorio;
import repositories.UsuarioRepositorio;

public class Main {

    public static void main(String[] args) {

        CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();

        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();

       
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

        ItemRepositorio itemRepositorio = new ItemRepositorio(categoriaRepositorio, fornecedorRepositorio);

        ContratoRepositorio contratoRepositorio = new ContratoRepositorio(usuarioRepositorio, itemRepositorio);

        
        SalvaArquivoUsuarios salvaUsuarios = new SalvaArquivoUsuarios("usuarios.csv");

        SalvaArquivoItens salvaItens = new SalvaArquivoItens("itens.csv");

        SalvaArquivoContratos salvaContratos = new SalvaArquivoContratos("contratos.csv");

       
        GerenciamentoContratos gerenciamentoContratos = new GerenciamentoContratos(contratoRepositorio, salvaContratos);

        GerenciamentoUsuarios gerenciamentoUsuarios =new GerenciamentoUsuarios( usuarioRepositorio, salvaUsuarios, gerenciamentoContratos);

        GerenciamentoItens gerenciamentoItens = new GerenciamentoItens(itemRepositorio,salvaItens);

        // Relatórios
        GeradorRelatorios geradorRelatorios =new GeradorRelatorios(gerenciamentoItens,gerenciamentoContratos, gerenciamentoUsuarios);

        // Facade
        SistemaFacade sistema = new SistemaFacade(gerenciamentoUsuarios, gerenciamentoContratos,gerenciamentoItens, geradorRelatorios);

        
        MenuPrincipal menuPrincipal = new MenuPrincipal(sistema);

        menuPrincipal.exibir();
    }
}