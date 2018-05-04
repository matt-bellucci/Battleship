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

	// Constantes de l'Interface graphique
	public static final String GAME_NAME = "Battleship GM";
	public static final int WINDOW_LENGTH = 400;
	public static final int WINDOW_WIDTH = 600;
    public static final Color BACKGROUND_COLOR = new Color(0,0,0);
    
    // Conteneurs de l'interface graphique
    JPanel container = new JPanel();
    Menu menu;
    ListPlayers listing;
    
    // Lien de communication avec le serveur
	ServerCommunicationThread com;
    
	
    public IHM(final ServerCommunicationThread com){

    	// Initialiser fenêtre
    	super(GAME_NAME);

    	this.com = com;
    	
    	this.menu =  new Menu();
    	
    	this.listing = new ListPlayers(com, "IA\n");
    	
    	// Gestion fenêtre
    	this.setSize(WINDOW_LENGTH,WINDOW_WIDTH);
    	this.setBackground(BACKGROUND_COLOR);
    	this.setLocationRelativeTo(null); //au centre
	
    	// Fermeture de la connexion Socket et du programme lors de la fermeture de la fenêtre
    	this.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent we) {
    			com.send("CLOSE");
    			System.exit(0);
    		}
    	});
    	this.setResizable(true); //possibilité de redimensionner la fenêtre
	
    	// Paramétrage du conteneur principal
    	container.setLayout(new BorderLayout());
    	
    	// Menu
    	this.setJMenuBar(menu);
    	
    	// Démarrage partie
    	container.add(listing,BorderLayout.CENTER);
    	
    	
    // EXEMPLES UTILISATION SWING
	
	//container.setBorder(BorderFactory.createTitledBorder("Ceci est un bord"));
	//container.add(new ValidateButton("Vouvouzella"));
	//container.add(new JLabel("MonJLabel", SwingConstants.CENTER));
	//container.add(new JCheckBox("Ma box", true));
	//JRadioButton rb1 = new JRadioButton("Mode normal");
	//JRadioButton rb2 = new JRadioButton("Mode WTF");
	//ButtonGroup bg = new ButtonGroup();
	//bg.add(rb1);
	//bg.add(rb2);
	//container.add(rb1,BorderLayout.EAST);
	//container.add(rb2,BorderLayout.EAST);
	
	
    	this.setContentPane(container);
	
    	//Afficher la fenêtre
    	this.setVisible(true);
    	
    }
    
    public void changeTitle(String title){
    	this.setTitle(title);
    }

	public void updateListPlayers(String players) {
		listing.setPlayersName(players);
	}
    
}	
	