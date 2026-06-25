package repositories;

import java.util.List;
import java.util.ArrayList;
import entidades.Fornecedor;
import entidades.Multa;
import entidades.Usuario;
import persistencia.LeArquivoMulta;

public class MultaRepositorio implements IMultaRepositorio{
	
	private List <Multa> multas;
	
	public MultaRepositorio(){
		//fazer construtor
	}
	
	@Override
	public void gerar(Multa multa) {
		multas.add(multa);
	}
	
	@Override
    public List<Multa> listarTodas() {
        return multas;
    }
	
	@Override
	public Multa buscarPorNomeCliente(String nomeCliente) {
		for (Multa multa : multas) {
			
			if (multa.getNomeCliente().equalsIgnoreCase(nomeCliente)) {
				return multa;
			}
		}
		
		return null;
	}
	
	@Override
	public Multa buscarPorId(int idMulta) {
		for (Multa multa : multas) {

            if (multa.getIdMulta() == idMulta) {
                return multa;
            }
        }

        return null;
	}
	
	@Override
	public void remover(Multa multa) {
		multas.remove(multa);
	}
	
	@Override
	public int gerarProximoId() {
		if (multas.isEmpty()){
            return 1;
        }
		
        int maiorId = 0;

        for (Multa multa : multas){
            if (multa.getIdMulta() > maiorId){
                maiorId = multa.getIdMulta();
            }
        }
        
        return maiorId + 1;
	}
	
}
