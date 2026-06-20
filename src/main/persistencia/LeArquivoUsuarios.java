import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeArquivoUsuarios {

    private String pasta;

    public LeArquivoUsuarios(String pasta) {
        this.pasta = pasta;
    }

    public List<Usuario> carregar() {

        List<Usuario> usuarios = new ArrayList<>();

        File caminhoCompleto = new File(this.pasta,"usuarios.csv");

        if (!caminhoCompleto.exists()) {

            System.out.println("Arquivo de usuários não encontrado.");

            return usuarios;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoCompleto))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {

                String[] dados = linha.split(";");

                String tipo = dados[0];

                int id = Integer.parseInt(dados[1]);

                String nome = dados[2];

                String email = dados[3];

                String cpf = dados[4];

                String senha = dados[5];

                boolean ativo = Boolean.parseBoolean(dados[6]);

                Usuario usuario = null;

                switch (tipo) {

                    case "CLIENTE":

                        usuario = new Cliente(id, nome, email, cpf, senha);

                        break;

                    case "FUNCIONARIO":

                        usuario = new Funcionario(id,nome,email,cpf,senha);

                        break;

                    case "ADMINISTRADOR":

                        usuario =new Administrador(id, nome, email, cpf, senha);

                        break;
                }

                if (usuario != null) {

                    usuario.setAtivo(ativo);

                    usuarios.add(usuario);
                }
            }

        } catch (IOException e) {

            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return usuarios;
    }
}