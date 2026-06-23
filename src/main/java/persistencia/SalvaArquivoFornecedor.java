package persistencia;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entidades.Fornecedor;

public class SalvaArquivoFornecedor {
    private String pasta;

    public SalvaArquivoFornecedor(String pasta){
        this.pasta = pasta;
        File pastaVazia = new File(pasta);
        
        if (!pastaVazia.exists()){
            if (pastaVazia.mkdirs()){
                System.out.println("Pasta " + pasta + " criada");
            }
        }
    }

    public void salvar(List<Fornecedor> fornecedores){
        File caminhoCompleto = new File(this.pasta, "fornecedores.csv");

        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoCompleto))){
            for (Fornecedor fornecedor  : fornecedores){
                escritor.write(fornecedor.getId() + ";" + fornecedor.getRazaoSocial() + ";" + fornecedor.getCnpj() + ";" + fornecedor.getEmail() + ";" + fornecedor.getTelefone() + ";" + fornecedor.getStatusAtivo());
                escritor.newLine();
            }

            System.out.println("Fornecedor salvo com sucesso!");

        }catch (IOException e){
            System.out.println("Erro ao salvar fornecedor: " + e.getMessage());
        }
    }
}
