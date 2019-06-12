
public class Estacionamento {
	private Posicao[][] matrix = null; 
	private Posicao entrada = null;
	private EstacionamentoGUI gui = null;
	
	public void setEntrada(Posicao p) {
		entrada = p;
	}
	
	public Posicao getEntrada() {
		return entrada;
	}

	public EstacionamentoGUI getGui() {
		return gui;
	}

	public void setGui(EstacionamentoGUI gui) {
		this.gui = gui;
	}
	
	public void setMatrix(Posicao[][] matrix) {
		this.matrix = matrix;
	}
	
//	public void repaint() {
//		for (int i=0; i<matrix.length;i++) {
//			for (int j=0; j<matrix[0].length;j++) {
//				if (matrix[i][j]!=null) matrix[i][j].getIPosicao().resetPosicao();
//			}
//		}
//	}
}
