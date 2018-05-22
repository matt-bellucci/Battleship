package fr.insarouen.battleship.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PersonalGrid extends JPanel {

	 public static final Color DICOVERED = new Color(50,20,20);
	 public static final Color BOAT = new Color(20,20,20);
	 public static final Color NOT_BOAT = new Color(20,20,100);
	    
	 private static int nbLigne = 10;
	 private static int nbColonne = 10;
	 
	 private int[][] square;
	
	public PersonalGrid() {
		super();
		square = new int[10][10];
		for (int j=0;j< nbColonne;j++){
		    for (int i=0;i< nbLigne;i++){
		    	this.square[i][j]= 0;
		    }
		}
		//this.addMouseListener(this);
	}

	public PersonalGrid(int nbLig,int nbCol) {
		super();
		this.nbLigne=nbLig;
		this.nbColonne=nbCol;
		square = new int[nbColonne][nbLigne];
		
		for (int j=0;j< nbColonne;j++){
		    for (int i=0;i< nbLigne;i++){
			this.square[j][i]=0;
		    }
		}
		//this.addMouseListener(this);
	}

	public int getNbCol(){
		return this.nbColonne;
	}

	public int getNbLig(){
		return this.nbLigne;
	}

	public void paintComponent(Graphics g){

		int largeurCase =  this.getWidth()/nbColonne;
		int hauteurCase =  this.getHeight()/nbLigne;

		// Dessiner la grille
		for (int j=0;j< nbColonne;j++){
		    for (int i=0;i< nbLigne;i++){
		    	switch (square[j][i]) {
		    	case -1 : 
		    		g.setColor(DICOVERED);
		    		g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
		    		break;
		    	case 0 :
		    		g.setColor(NOT_BOAT);
		    		g.fillRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
			    break;
		    	case 1 :
				    g.setColor(BOAT);
				    g.fillOval(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
				    break;
		    	}
			g.setColor(new Color(50,50,50));
			g.drawRect(j*largeurCase,i*hauteurCase,largeurCase,hauteurCase);
		
			 
		    }
		}	
		repaint();
	}
/*
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX()/((int) this.getWidth()/nbColonne);
		int y = e.getY()/((int) this.getHeight()/(nbLigne+1));
		if (square[x][y] == 0){
			square[x][y]=1;
		}
		else {
			square[x][y]=-1;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
*/
	public int getValueOfSquare(int x, int y) {
		return square[x][y];
	}
	
	public void setValueOfSquare(int x,int y, int value){
		this.square[x][y]= value;
	}

	public void setGrid(String string) {
		for (int i=0 ; i<this.getNbLig(); i++){
			for (int j=0 ; j<this.getNbCol(); j++){
				String val =string.substring(i*getNbCol()+j,i*getNbCol()+j+1);
				System.out.println(val+" ");
				this.square[i][j] = Integer.parseInt(val);
			}
		}
	}
	
	@Override
	public String toString(){
		return "Grille Personnelle";
	}
}


