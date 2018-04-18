/**
 * 
 */
package fr.insarouen.view;

import javax.swing.*;
import java.awt.*;
import java.lang.*;

/**
 * @author david
 *
 */
public class IHM extends JFrame {

    public static final Color BACKGROUND_COLOR = new Color(0,0,0);
    JPanel container = new JPanel();
    Menu menu = new Menu();
    
    public IHM(){

	// Initialiser fenêtre
	super("BattleShip");
	this.setSize(1000,600);
	this.setBackground(BACKGROUND_COLOR);
	this.setLocationRelativeTo(null); //au centre
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête le programme quand on appuie sur la croix
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
