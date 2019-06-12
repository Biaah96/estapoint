
public class Vaga extends Posicao{
	
	private static int proxNumero=1;
	
	private int numero;
	
	public Vaga() {
		super();
		numero = proxNumero++;
	}
	
	public int getNumero() {
		return numero;
	}
}
