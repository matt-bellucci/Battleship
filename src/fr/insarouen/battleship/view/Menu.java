package fr.insarouen.battleship.view;

import javax.swing.*;
import java.awt.*;


public class Menu extends JMenuBar {

	private static final String lim = new String("Joueur:");
    private JPanel container = new JPanel();
	
    private JMenu info = new JMenu("Battleship");
    private JMenu stats = new JMenu("Stats");
    private JMenu aide = new JMenu("?");
	
    private JMenuItem createurs = new JMenuItem("Créateurs");
    private JMenuItem noms = new JMenuItem("DavidHenri\nMatthieu\nThomas");
	
    public Menu(){
		// Menu
		//this.createurs.add(noms);
	    this.info.add(createurs);
	    
	    this.add(info);
	    this.add(stats);
	    this.add(aide); 
	}
	
    
	public Menu(StateIHM state){
		// Menu
		//this.createurs.add(noms);
	    this.info.add(createurs);
		try {
			this.aide.add(state.getHelp());
		} catch (StringIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		
	    this.add(info);
	    this.add(stats);
	    this.add(aide); 
	}
	


}
