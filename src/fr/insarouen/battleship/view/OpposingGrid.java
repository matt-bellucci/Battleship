package fr.insarouen.battleship.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class OpposingGrid extends AbstractGraphicGrid {

	 public static final Color NOT_DICOVERED = new Color(200,200,200);
	 public static final Color TOUCHED = new Color(200,20,20);
	 public static final Color NOT_TOUCHED = new Color(20,20,200);
	
	 public OpposingGrid() {
		 super();
	 }

	public OpposingGrid(int nbLig,int nbCol) {
		super(nbLig,nbCol);
	}

	public void paintComponent(Graphics g){

		int largeurCase =  this.getWidth()/getNbCol();
		int hauteurCase =  this.getHeight()/getNbLig();

		// Dessiner la grille
		for (int j=0;j< getNbCol();j++){
		    for (int i=0;i< getNbLig();i++){
		    	switch (square[j][i]) {
		    	case -1 : 
		    		g.setColor(NOT_TOUCHED);
		    		g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
		    		break;
		    	case 0 :
		    		g.setColor(NOT_DICOVERED);
		    		g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
			    break;
		    	case 1 :
				    g.setColor(TOUCHED);
				    g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
				    break;
		    	}
			g.setColor(new Color(50,50,50));
			g.drawRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
		
			 
		    }
		}	
		repaint();
	}
	
	@Override
	public String toString(){
		return "Grille Adverse";
	}

	public void setMissed(int x, int y) {
		setValueOfSquare(x, y, -1);
	}

	public void setTouched(int x, int y) {
		setValueOfSquare(x, y, 1);
	}
}

