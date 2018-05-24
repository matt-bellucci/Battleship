package fr.insarouen.battleship.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class AbstractGraphicGrid extends JPanel {

	 private static int nbLigne = 10;
	 private static int nbColonne = 10;
	 
	 protected int[][] square;
	
	
	public AbstractGraphicGrid() {
		super();
		square = new int[10][10];
		for (int j=0;j< nbColonne;j++){
		    for (int i=0;i< nbLigne;i++){
		    	this.square[i][j]= 0;
		    }
		}
	}
	
	public AbstractGraphicGrid(int nbLig,int nbCol) {
		super();
		this.nbLigne=nbLig;
		this.nbColonne=nbCol;
		square = new int[nbColonne][nbLigne];
		
		for (int j=0;j< nbColonne;j++){
		    for (int i=0;i< nbLigne;i++){
			this.square[j][i]=0;
		    }
		}
	}

	public int getNbCol(){
		return this.nbColonne;
	}

	public int getNbLig(){
		return this.nbLigne;
	}

	public abstract void paintComponent(Graphics g);
	
	public int getValueOfSquare(int x, int y) {
		return square[x][y];
	}
	
	protected void setValueOfSquare(int x,int y, int value){
		this.square[x][y]= value;
	}
	
	@Override
	public String toString(){
		return "Grille Adverse";
	}

}
