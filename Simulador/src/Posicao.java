
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public abstract class Posicao {
	private Carro carro = null;
	private List<Posicao> listDestinos;
	private IPosicao iPosicao = null;
	
	public Posicao() {
		listDestinos = new ArrayList<Posicao>();
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public Carro removeCarro() {
		Carro carro = this.carro;
		this.carro = null;
		iPosicao.resetPosicao();
		return carro;
	}
	
	public void colocaCarro(Carro carro) throws IOException {
		this.carro = carro;
		iPosicao.setPosicao(this);
	}
	
	public void setDestino(Posicao destino) {
		listDestinos.add(destino);
	}
	
	public boolean vazia() {
		return carro==null;
	}
	
	public List<Posicao> getDestinos(Carro carro){
		List<Posicao> lista = new ArrayList<Posicao>();
		for (Posicao p: listDestinos) {
			if ((p.vazia()) &&
				(((p instanceof Vaga)  && (carro.getIntencao()=='E') && (!ConexaoMySQL.vagaOcupada(((Vaga)(p)).getNumero()))) ||
				 ((p instanceof Saida) && (carro.getIntencao()=='S')) ||
				  (p instanceof Via) || (p instanceof ViaMaoDupla))) {
				lista.add(p);
			}
		}
		return lista;
	}

	public IPosicao getIPosicao() {
		return iPosicao;
	}

	public void setIPosicao(IPosicao iPosicao) {
		this.iPosicao = iPosicao;
	} 	 
	
}
