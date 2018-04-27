package fr.insarouen.battleship.view;

import javax.swing.*;
import java.awt.*;


public class Menu extends JMenuBar {

	
    private JPanel container = new JPanel();
	
    private JMenu newGame = new JMenu("Nouvelle partie");
    private JMenu stats = new JMenu("Statistique");
    private JMenu aide = new JMenu("?");
	
    private JMenuItem noNet = new JMenuItem("Mode hors ligne");
    private JMenuItem net = new JMenuItem("Mode en ligne");
    private JMenuItem item4 = new JMenuItem("Enregistrer sous");
	
	
	public Menu(){
	
		
	    // Menu
	    this.newGame.add(noNet);
	    this.newGame.add(net);
	    
	    
	    this.add(newGame);
	    this.add(stats);
	    this.add(aide);
	        
	  
	}

}
