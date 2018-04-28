package fr.insarouen.battleship;

import fr.insarouen.battleship.view.*;
import fr.insarouen.battleship.net.*;

/** 
 * Classe Battleship : Programme principal côté Client. Le programme crée une connexion à un serveur distant et débute le jeux bataille navale en réseau avec interface graphique.
 * @author David ALBERT
 *
 */
public class Battleship {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			String host = "127.0.0.1";
			int port = 1099;

			// Création d'une connexion socket
			BattleshipClient com = new BattleshipClient(host, port);
			
			com.send("NEW");
			// Lancement de l'IHM du jeu
			IHM ihm = new IHM(com);  
		
		
	}

}
