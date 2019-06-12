import java.io.IOException;

public class Rua extends Thread{
	
	private Estacionamento estacionamento = null;
	
	public Rua(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}
	
	public void run() {
		for (int i=0;i<100;i++)
		//while (true) 
		{
			try {sleep(50*Main.velocidade);} catch (InterruptedException e) {}
			System.out.println("fire Rua...");
			if (estacionamento.getEntrada().vazia()) {
				Carro carro = new Carro();
				carro.setPosicao(estacionamento.getEntrada());
				carro.start();
				try {
					estacionamento.getEntrada().colocaCarro(carro);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
