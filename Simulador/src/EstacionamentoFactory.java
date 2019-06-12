import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EstacionamentoFactory {
	public Estacionamento getEstacionamento() throws IOException {
		
		Estacionamento e = new Estacionamento();
		
		String matrix[] = //manter sempre uma borda de espaços em branco
			{"          ",
			 " XVVVVVVX ",//1
			 " VhhhhxhE ",//2
			 " VtVVV^aX ",//3
			 " VtVVV^aX ",//4
			 " VqyyyYaX ",//5
			 " VttttxTX ",//6
			 " VtVVV^aX ",//7
			 " VtVVV^aX ",//8
			 " VqjjjjkS ",//9
			 " XVVVVVVX ",//10
			 "          "};
		
		// k baixo direita
		// y cima direita
		// x cima esquerda
		// t baixo esquerda
		// h cima baixo esquerda
		// j cima baixo direita
		// w cima esquerda direita
		// q baixo esquerda direita
		
		
		Posicao posicoes[][] = new Posicao[matrix.length][matrix[0].length()];
		
		EstacionamentoGUI gui = new EstacionamentoGUI(matrix.length-2,matrix[0].length()-2);
		e.setGui(gui);
		
		//Constrói matriz de posições
		for (int lin=1;lin<matrix.length;lin++) {
			for(int col=1;col<matrix[lin].length();col++) {
				switch (matrix[lin].charAt(col)) {
				case 'X': //parede
					posicoes[lin-1][col-1] = new Parede();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;
				case 'k': //via 
				case 't': //via  
				case 'h': //via 
				case 'j': //via 
				case 'Y': //via
				case 'w': //via 
				case 'q': //via 
				case '<': //via esquerda
				case 'v': //via baixo
				case '>': //via direita
				case '^': //via cima
				case 'x': //via todos, exceto origem
					posicoes[lin-1][col-1] = new Via();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;
				case 'V': //vaga
					posicoes[lin-1][col-1] = new Vaga();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;
				case 'E': //entrada
					e.setEntrada(new Entrada());
					posicoes[lin-1][col-1] = e.getEntrada();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;
				case 'S': //saida
					posicoes[lin-1][col-1] = new Saida();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;	
				case 'a':
				case 'y': //via
				case 'T': //via
					posicoes[lin-1][col-1] = new ViaMaoDupla();
					gui.getIPosicao(lin-1, col-1).setPosicao(posicoes[lin-1][col-1]);
					posicoes[lin-1][col-1].setIPosicao(gui.getIPosicao(lin-1, col-1));
					break;
				}
			}
		}
		
		//Constrói relações entre as posições
		int dl=0; //descocamento de linha
		int dc=0; //descocamento de coluna
		for (int lin=1;lin<matrix.length;lin++) {
			for(int col=1;col<matrix[lin].length();col++) {
				switch (matrix[lin].charAt(col)) {
				case 'X': //parede
					break;
				case '<': //via esquerda
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'v': //via baixo
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'a': //via baixo
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'k': //baixo e direita
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'y': //cima e direita
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'Y': //cima e direita
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case '>': //via direita
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case '^': //via cima
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'x': //para cima e para esquerda
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 't': //para baixo e para esquerda
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'T': //para baixo e para esquerda
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'h': //para cima, para baixo e para esquerda
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'w': // w cima esquerda direita
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'q': // q baixo esquerda direita
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'j': //para cima, para baixo e para direita
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^YTVS".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'V': //vaga
				case 'E': //entrada
					//busca posição do tipo via à sua volta
					dl= 0;
					dc=+1;
					if ("kyxthjwqa<v>^".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl= 0;
					dc=-1;
					if ("kyxthjwqa<v>^".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=-1;
					dc= 0;
					if ("kyxthjwqa<v>^".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					dl=+1;
					dc= 0;
					if ("kyxthjwqa<v>^".contains(""+matrix[lin+dl].charAt(col+dc))) {
						posicoes[lin-1][col-1].setDestino(posicoes[lin+dl-1][col+dc-1]);
					}
					break;
				case 'S': //saida
					break;	
				}
			}
		}
		
//		posicoes[1][15].setDestino(posicoes[1][14]);
//		posicoes[1][14].setDestino(posicoes[1][13]);
//		posicoes[1][13].setDestino(posicoes[1][12]);
//		
//		posicoes[1][13].setDestino(posicoes[0][13]);
//		posicoes[0][13].setDestino(posicoes[1][13]);
//		
//		posicoes[1][12].setDestino(posicoes[1][11]);
//		posicoes[1][11].setDestino(posicoes[1][10]);
//		posicoes[1][10].setDestino(posicoes[1][9]);
//		posicoes[1][9].setDestino(posicoes[1][8]);
//		posicoes[1][8].setDestino(posicoes[1][7]);
//		posicoes[1][7].setDestino(posicoes[1][6]);
//		posicoes[1][6].setDestino(posicoes[1][5]);
//		posicoes[1][5].setDestino(posicoes[1][4]);
//		posicoes[1][4].setDestino(posicoes[1][3]);
//		posicoes[1][3].setDestino(posicoes[1][2]);
//		posicoes[1][2].setDestino(posicoes[1][1]);
//		
//		posicoes[1][1].setDestino(posicoes[2][1]);
//		posicoes[2][1].setDestino(posicoes[3][1]);
//		posicoes[3][1].setDestino(posicoes[4][1]);
//		posicoes[4][1].setDestino(posicoes[4][2]);
//		posicoes[4][2].setDestino(posicoes[4][3]);
//		posicoes[4][3].setDestino(posicoes[4][4]);
//		posicoes[4][4].setDestino(posicoes[4][5]);
//		posicoes[4][5].setDestino(posicoes[4][6]);
//		posicoes[4][6].setDestino(posicoes[4][7]);
//		posicoes[4][7].setDestino(posicoes[4][8]);
//		posicoes[4][8].setDestino(posicoes[4][9]);
//		posicoes[4][9].setDestino(posicoes[4][10]);
//		posicoes[4][10].setDestino(posicoes[4][11]);
//		posicoes[4][11].setDestino(posicoes[4][12]);
//		posicoes[4][12].setDestino(posicoes[4][13]);
//		posicoes[4][13].setDestino(posicoes[4][14]);
//		posicoes[4][14].setDestino(posicoes[3][14]);
//		posicoes[3][14].setDestino(posicoes[2][14]);
//		posicoes[2][14].setDestino(posicoes[1][14]);
		
		//gui.repaint();
		e.setMatrix(posicoes);
		return e;
	}
}
