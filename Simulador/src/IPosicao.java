import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IPosicao extends JPanel{
	private Posicao posicao;
	private JLabel label;
	private JLabel imagem;
	
	public IPosicao(Posicao posicao) throws IOException {
		this.setPosicao(posicao);
		this.setLayout(new BorderLayout());
		
		//TEXTO
		label = new JLabel("");
		
		//IMAGEM
		imagem = new JLabel();
		imagem.setBounds(10, 10, 100, 100);
		
		this.add(label,BorderLayout.NORTH);
		this.add(imagem,BorderLayout.CENTER);
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public void setImagem(String imagemURL) {
		ImageIcon imageIcon = new ImageIcon(imagemURL);                                      // load the image to a imageIcon
		Image image         = imageIcon.getImage();                                          // transform it
		Image newimg        = image.getScaledInstance(60, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		imageIcon           = new ImageIcon(newimg);                                         // transform it back
		imagem.setIcon(imageIcon);	
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) throws IOException {
		this.posicao = posicao;
		if (posicao!=null) {
			if (!posicao.vazia()) {
				setText(posicao.getCarro().getPlaca());
				setImagem(Main.getCurrentDir()+"\\src\\car_front.png");
			}
			if (posicao instanceof Parede) {
				this.setBackground(Color.DARK_GRAY);
			} else
			if (posicao instanceof Via) {
				this.setBackground(Color.GRAY);	
			} else
			if (posicao instanceof ViaMaoDupla) {
				this.setBackground(Color.GRAY);	
				this.imagem.setBorder(BorderFactory.createLineBorder(Color.white, 1));
			} else
			if (posicao instanceof Vaga) {
				if (posicao.vazia()) {
					this.setText(""+((Vaga)(posicao)).getNumero());
				} else {
					this.setText(""+((Vaga)(posicao)).getNumero()+" "+posicao.getCarro().getPlaca());	
				}
				this.setBackground(Color.LIGHT_GRAY);
			} else
			if (posicao instanceof Entrada) {
				this.setBackground(Color.YELLOW);
			} else 
			if (posicao instanceof Saida) {
				this.setBackground(Color.WHITE);
			} else {
				this.setBackground(Color.WHITE);	
			}
		}
	}

	public void resetPosicao() {
		if (posicao instanceof Vaga) {
			this.setText(""+((Vaga)(posicao)).getNumero());
		} else {
			setText("");
		}
		setImagem(null);
	}
	
}
