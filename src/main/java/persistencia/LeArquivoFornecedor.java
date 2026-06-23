package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidades.Fornecedor;

public class LeArquivoFornecedor {

    private String pasta;

    public LeArquivoFornecedor(String pasta){
        this.pasta = pasta;
    }

    public List<Fornecedor> carregar(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        File caminhoCompleto = new File(this.pasta, "fornecedores.csv");

        if (!caminhoCompleto.exists()){
            System.out.println("Arquivo de fornecedores não encontrado");
            return fornecedores;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoCompleto))){
            String linha;
            while ((linha = leitor.readLine()) != null){
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String razaoSocial = dados[1];
                String cnpj = dados[2];
                String email = dados[3];
                String telefone = dados[4];
                boolean statusAtivo = Boolean.parseBoolean(dados[5]);

                Fornecedor novo = new Fornecedor(id, razaoSocial, cnpj, email, telefone, statusAtivo);
                fornecedores.add(novo);
            }
        }catch (IOException e) {
            System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
        }
        return fornecedores;
    }
}
