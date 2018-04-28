/**
 * 
 */
package fr.insarouen.battleship.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import fr.insarouen.battleship.net.*;

/**
 * Classe IHM : contient tous les objets graphiques de la fenêtre utilisateur Battleship.
 * 
 * @author David ALBERT
 *
 */
public class IHM extends JFrame {

    public static final Color BACKGROUND_COLOR = new Color(0,0,0);
    
    JPanel container = new JPanel();
    Menu menu;
    
	BattleshipClient com;
    
    public IHM(final BattleshipClient com){

	// Initialiser fenêtre
	super("BattleShip");
	this.com = com;
	this.menu =  new Menu(com.send("LIST"));
	this.setSize(1000,600);
	this.setBackground(BACKGROUND_COLOR);
	this.setLocationRelativeTo(null); //au centre
	this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent we) {
			com.send("CLOSE");
		    System.exit(0);
		  }
		});
	this.setResizable(true); //possibilité de redimensionner la fenêtre

	// Paramétrage du conteneur principal
	container.setLayout(new GridLayout());

	// Menu
	this.setJMenuBar(menu);
	
	// Ajout du plan de l'infrastructure au conteneur principal
	container.add(new Button("Cliquer pour commencer"));
	
	// Ajout West
	


	this.setContentPane(container);
	
	//Afficher la fenêtre
	this.setVisible(true);
	
    }
}
