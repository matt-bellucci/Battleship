package fr.insarouen.battleship.view;

import javax.swing.*;
import java.awt.*;


public class Menu extends JMenuBar {

	private static final String lim = new String("Joueur:");
    private JPanel container = new JPanel();
	
    private JMenu newGame = new JMenu("Jouer");
    private JMenu stats = new JMenu("Statistique");
    private JMenu aide = new JMenu("?");
	
    private JMenuItem ia = new JMenuItem("IA");
    private JMenuItem item4 = new JMenuItem("Enregistrer sous");
	
	
	public Menu(String listAdv){
	

	    // Menu
		try {
		initAdv(listAdv);
		} catch (StringIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	    this.newGame.add(ia);
	    
	    
	    this.add(newGame);
	    this.add(stats);
	    this.add(aide);
	        
	  
	}
	
	private void initAdv(String listAdv) throws StringIndexOutOfBoundsException {
		int taille = lim.length();
		int i=0;
		while (i+taille<listAdv.length()){

			int l1= listAdv.indexOf(lim,i);
			int l2= listAdv.indexOf(lim,i+taille);
			String str = listAdv.substring(l1, l2);
			i+=taille+str.length();
			this.newGame.add(new JMenuItem(
		    		str
		    		));
		}
	}

}
