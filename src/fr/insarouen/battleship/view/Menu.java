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
	
    public Menu(){
		// Menu
		
	    this.newGame.add(ia);
	    
	    this.add(newGame);
	    this.add(stats);
	    this.add(aide); 
	}
	
    
	public Menu(String listAdv){
		// Menu
		try {
		    
			this.newGame.add(listAdv);
		} catch (StringIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	    this.newGame.add(ia);
	    
	    this.add(newGame);
	    this.add(stats);
	    this.add(aide); 
	}
	


}
