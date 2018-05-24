/**
 * 
 */
package fr.insarouen.battleship.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import fr.insarouen.battleship.net.*;

/**
 * Classe IHM : contient tous les objets graphiques de la fenêtre utilisateur Battleship.
 * 
 * @author David ALBERT
 *
 */
public class IHM extends JFrame {

	// Constantes de l'Interface graphique
	public static final String GAME_NAME = "BattleshipNET";
    public static final Color BACKGROUND_COLOR = new Color(0,0,0);
    
    // Conteneurs de l'interface graphique
    private JPanel container = new JPanel();
    
    private Menu menu;
    private ListPlayers listing;
    
    // Lien de communication avec le serveur
    private boolean connected = false;
	private ClientCommunicationThread com = null;
	
	// Etat de l'application
	private StateIHM current_state = StateIHM.PLAYER_IDENTIFICATION;
	private boolean turn = false;
    
	// Elements partie
	JLabel message =new JLabel("C'est parti !");
	private OpposingGrid opposingGrid = new OpposingGrid();
    private PersonalGrid personalGrid = new PersonalGrid(); 
    
	// Données perso utiles
	String pName = "";
	
    public IHM(String host, int port){

    	// Initialiser fenêtre
    	super(GAME_NAME);
    	
    	//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    		// Positionnement fenêtre
    	this.setLocationRelativeTo(null); //au centre
	
    	// Fermeture de la connexion Socket et du programme lors de la fermeture de la fenêtre
    	this.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent we) {
    			if (connected){
    				com.send("CLOSE");
    			}
    			System.exit(0);
    		}
    	});
    	
    	// Possibilité de redimensionner la fenêtre
    	this.setResizable(true); 
    	try {
    		System.out.println("Initialisation Thread de Communication");
    		com = new ClientCommunicationThread(this, host, port);
    		System.out.println("Lancement Thread de Communication");
    		com.start();
    		connected = true;
    	} catch (ConnectException e) {
			System.err.println("Serveur indisponible : veuillez réessayer plus tard");
    	 } catch (UnknownHostException e) {
    		 e.printStackTrace();
    	 } catch (IOException e) {
    		 e.printStackTrace();
    	 }
    	
    	container.removeAll();
    	// Affichage fenêtre choix nom
    
    	infoPlayer("Entrez votre pseudo");
    			
    }

		
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

	public void updateListPlayers(String players) {
		listing.setPlayersName(players);
	}
	
	public void requestRefused(String name,StateIHM state){
		switch (state){
		case OPPONENT_CHOICE : 
			this.listing.setButtonText("Demande de "+name+" refusée.");
			break;
		case PLAYER_IDENTIFICATION : 
			infoPlayer("Pseudo indisponible, autre pseudo");
			break;
		}
	}
	
	public void setStateIHM(StateIHM state){
		this.current_state = state;
	}
	
	public StateIHM getStateIHM(){
		return this.current_state;
	}
	
	public void askNewGame(String str){
		JOptionPane jop = new JOptionPane();      
		int option = jop.showConfirmDialog(null, "Voulez-vous jouer contre " +str+" ?","Demande de partie", JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE);
		if (option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
			System.out.println("Réponse oui");
			com.send("REPONSEPARTIE:"+str+":oui");
			battleInterface(str);
			this.turn = true;
		}
		else {
			com.send("REPONSEPARTIE:"+str+":non");
		}
	}
	
	private void infoPlayer(String comment){
		JOptionPane jop = new JOptionPane();
    	 String name = jop.showInputDialog(null, comment, "BattleshipNET", JOptionPane.QUESTION_MESSAGE);
    		if (name != null) 
    			com.send("AvailableName:"+name);
    		else {
    			infoPlayer("Entrez votre pseudo");
    		}
	}
	
	
	public void opponentChoiceInterface(){
		// On efface tous les composants du conteneur principal
		container.removeAll();
		
		// Ajout Menu
		this.menu =  new Menu(StateIHM.OPPONENT_CHOICE);
		
		// Ajout listing des joueurs
    	this.listing = new ListPlayers(com, "IA\n");
    	
    	// Gestion fenêtre
    	this.setTitle("Choix adversaire");
    	this.setSize(400,500);
    	this.setBackground(BACKGROUND_COLOR);
    	
    	// Paramétrage du conteneur principal
    	container.setLayout(new BorderLayout());
    	container.add(listing,BorderLayout.CENTER);
    	
    	// Menu
    	this.setJMenuBar(menu);

    	this.setContentPane(container);
    	
    	//Afficher la fenêtre
    	this.setVisible(true);
    	
	}
	
	public void battleInterface(String str){
		container.removeAll();
		GridLayout layout = new GridLayout(0,2);
    	this.setTitle("Bataille contre "+str);
    	this.setSize(1000,600);
    	this.setBackground(BACKGROUND_COLOR);
		this.menu =  new Menu(StateIHM.IN_GAME);
    	this.setJMenuBar(menu);
    	
    	if (turn){
    		message.setFont(new Font("Arial", 10, 20));
    		message.setText("A vous de jouer");
    	}
    	
    	opposingGrid.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()/((int) opposingGrid.getWidth()/opposingGrid.getNbCol());
				int y = e.getY()/((int) opposingGrid.getHeight()/opposingGrid.getNbLig());
				if ((turn) && opposingGrid.getValueOfSquare(x,y) == 0){
					com.send("DECOUVRIR:"+x+":"+y);
				}
			}
		});
    	container.setLayout(layout);
    	container.add(opposingGrid);
    	container.add(message);
    	container.add(new JLabel(""));
    	container.add(personalGrid);

    	
    	this.setContentPane(container);
    	//Afficher la fenêtre
    	this.setVisible(true);
	}


	public void updateGrid(String x, String y, String name,String state) {
		if (name.equals(this.pName)){
				personalGrid.discovered(Integer.parseInt(x),Integer.parseInt(y));
				message.setText("A vous de jouer");
		}
		else {
			if (state.equals("PLOUF")){
				opposingGrid.setMissed(Integer.parseInt(x),Integer.parseInt(y));
			}
			else {
				opposingGrid.setTouched(Integer.parseInt(x),Integer.parseInt(y));
				
			}
			message.setText("Résultat de l'attaque : "+state);
		}
		
		turn = !turn;
	}


	public void setPlayerName(String string) {
		this.pName = string;
	}

	public String getPlayerName(){
		return this.pName;
	}

	public void endGame(String looser) {
		JOptionPane jop = new JOptionPane();      
		int option = jop.showConfirmDialog(null, looser+"a perdu\n Voulez-vous recommencer ?","Fin partie", JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE);
		if (option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
			infoPlayer("Entrez votre pseudo");
		}
		else {
			if (connected){
				com.send("CLOSE");
			}
			System.exit(0);
		}
	}
	public void setPersonalGrid(String string) {
		System.out.println("Mise a jour de la grille perso");
		this.personalGrid.setGrid(string);
	}
	
	@Override
	public String toString(){
		return "Interface Homme Machine du jeu BattleShipNET";
	}


}	
	