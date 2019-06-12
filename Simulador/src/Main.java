import java.io.IOException;

public class Main {

	public static Estacionamento estacionamento;
	public static int velocidade = 20;
	
	public static void main(String[] args) throws IOException {
		ConexaoMySQL.open();
		System.out.println("Estacionamento iniciado!");
		EstacionamentoFactory factory = new EstacionamentoFactory();
		estacionamento = factory.getEstacionamento();
		
		Rua rua =new Rua(estacionamento);
		rua.start();
		estacionamento.getGui().repaint();
	}

	public static String getCurrentDir() throws IOException {
		return new java.io.File( "." ).getCanonicalPath();
	}

}
