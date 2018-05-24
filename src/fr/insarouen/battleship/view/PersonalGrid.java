package fr.insarouen.battleship.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PersonalGrid extends AbstractGraphicGrid {

	public static final Color DICOVERED = new Color(250,20,20);
	public static final Color BOAT = new Color(100,100,100);
	public static final Color NOT_BOAT = new Color(250,250,250);
	
	public PersonalGrid() {
		super();
	}

	public PersonalGrid(int nbLig,int nbCol) {
		super();
	}

	public void paintComponent(Graphics g){

		int largeurCase =  this.getWidth()/getNbCol();
		int hauteurCase =  this.getHeight()/getNbLig();

		// Dessiner la grille
		for (int j=0;j< getNbCol();j++){
		    for (int i=0;i< getNbLig();i++){
		    	switch (square[j][i]) {
		    	case -1 : 
		    		g.setColor(DICOVERED);
		    		g.fillOval(j*largeurCase+largeurCase/4,i*hauteurCase+hauteurCase/4,largeurCase/2,hauteurCase/2);
		    		break;
		    	case 0 :
		    		g.setColor(NOT_BOAT);
		    		g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
			    break;
		    	case 1 :
				    g.setColor(BOAT);
				    g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
				    break;
		    	}
			g.setColor(new Color(50,50,50));
			g.drawRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
		
			 
		    }
		}	
		repaint();
	}

	public void setGrid(String string) {
		for (int i=0 ; i<this.getNbLig(); i++){
			for (int j=0 ; j<this.getNbCol(); j++){
				String val =string.substring(i*getNbCol()+j,i*getNbCol()+j+1);
				this.square[i][j] = Integer.parseInt(val);
			}
		}
		repaint();
	}
	
	@Override
	public String toString(){
		return "Grille Personnelle";
	}

	public void discovered(int x, int y) {
		setValueOfSquare(x, y, -1);
	}
}


