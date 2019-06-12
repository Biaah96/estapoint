import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EstacionamentoGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private IPosicao[][] iPosicao = null;

	public EstacionamentoGUI(int lin,int col) throws IOException {
		this.setTitle("Estacionamento");
		this.setLayout(new GridLayout(lin,col));
		this.setBounds(10, 10, 1200, 500);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		iPosicao = new IPosicao[lin][col];
		for (int i=0; i < lin; i++) {
			for (int j=0; j < col; j++) {
				iPosicao[i][j] = new IPosicao(null);
				this.add(iPosicao[i][j]);
			}
		}

		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        System.exit(0);
		    }
		});
		
		this.repaint();
		this.setVisible(true);
	}
	
	public IPosicao getIPosicao(int lin, int col) {
		return iPosicao[lin][col];
	}
	
	public void setIPosicao(int lin, int col, IPosicao posicoes) {
		iPosicao[lin][col] = posicoes;
	}
	
}
