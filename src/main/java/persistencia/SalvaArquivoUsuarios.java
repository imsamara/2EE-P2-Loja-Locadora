package persistencia;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entidades.Usuario;
public class SalvaArquivoUsuarios {

    private String pasta;

    public SalvaArquivoUsuarios(String pasta) {

        this.pasta = pasta;

        File pastaVazia = new File(pasta);

        if (!pastaVazia.exists()) {

            if (pastaVazia.mkdirs()) {

                System.out.println("Pasta " + pasta + " criada");

            }
        }
    }

    public void salvar(List<Usuario> usuarios) {

        File caminhoCompleto = new File(this.pasta,"usuarios.csv");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoCompleto))) {

            for (Usuario usuario : usuarios) {

                escritor.write(usuario.getTipo() + ";" + usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getEmail() + ";" + usuario.getCpf() + ";" + usuario.getSenha() + ";" + usuario.isAtivo());
                escritor.newLine();
            }

            System.out.println("Usuários salvos com sucesso");

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar usuários: " + e.getMessage());
        }
    }
}