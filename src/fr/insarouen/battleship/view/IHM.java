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
    public static final Color BACKGROUND_COLOR = new Color(0,0,0);
    
    // Conteneurs de l'interface graphique
    private JPanel container = new JPanel();
    private Menu menu;
    private ListPlayers listing;
    
    // Lien de communication avec le serveur
	private ClientCommunicationThread com;
    
	
    public IHM(final ClientCommunicationThread com){

    	// Initialiser fenêtre
    	super(GAME_NAME);
    	this.com = com;
    	
    	// Positionnement fenêtre
    	this.setLocationRelativeTo(null); //au centre
	
    	// Fermeture de la connexion Socket et du programme lors de la fermeture de la fenêtre
    	this.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent we) {
    			com.send("CLOSE");
    			System.exit(0);
    		}
    	});
    	
    	// Possibilité de redimensionner la fenêtre
    	this.setResizable(true); 
    	
    	// Affichage fenêtre choix nom
    	infoPlayer();
    	
		
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
	
	
    	
    }

	public void updateListPlayers(String players) {
		listing.setPlayersName(players);
	}
	
	public void askNewGame(String str){
		JOptionPane jop = new JOptionPane();      
		int option = jop.showConfirmDialog(null, "Voulez-vous jouer contre " +str+" ?","Demande de partie", JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE);
		if (option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
			System.out.println("Réponse oui");
	    	battleInterface(str);
		}
	}
	private void infoPlayer(){
		
		container.removeAll();
		this.setTitle("Informations joueur");
    	this.setSize(200,100);
    	this.setBackground(BACKGROUND_COLOR);

    	
    	JButton submit = new JButton("Valider");
    	final JTextField pseudoField = new JTextField("Pseudo");
    	pseudoField.setPreferredSize(new Dimension(100, 20));
    	container.setLayout(new FlowLayout());
    	container.add(new JLabel("Pseudo : "));
    	container.add(pseudoField);
    	submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (com.request("AvailableName:"+pseudoField.getText()).compareTo("yes") == 0){
					opponentChoiceInterface();
					com.send("NEW:"+pseudoField.getText());
					com.send("LIST");
				}
			}
		});
    	container.add(submit);

    	this.setContentPane(container);
    	
    	//Afficher la fenêtre
    	this.setVisible(true);
    	
	}
	
	private void opponentChoiceInterface(){
		// On efface tous les composants du conteneur principal
		container.removeAll();
		
		// Ajout Menu
		this.menu =  new Menu();
		
		// Ajout listing des joueurs
    	this.listing = new ListPlayers(com, "IA\n");
    	
    	// Gestion fenêtre
    	this.setTitle("Choix adversaire");
    	this.setSize(400,600);
    	this.setBackground(BACKGROUND_COLOR);
    	
    	// Paramétrage du conteneur principal
    	container.setLayout(new BorderLayout());
    	container.add(listing,BorderLayout.CENTER);
    	
    	// Menu
    	this.setJMenuBar(menu);
    	
    	// Démarrage partie
    	this.setContentPane(container);
    	
    	//Afficher la fenêtre
    	this.setVisible(true);
    	
	}
	
	private void battleInterface(String name){

		container.removeAll();
		
		this.setTitle("Bataille contre "+name);
    	this.setSize(1000,600);
    	this.setBackground(BACKGROUND_COLOR);
    	this.setJMenuBar(menu);
    	this.setContentPane(container);
    	
    	//Afficher la fenêtre
    	this.setVisible(true);
	}
}	
	