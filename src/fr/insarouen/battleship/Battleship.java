package fr.insarouen.battleship;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) {
			String host = "127.0.0.1";
			int port = 1099;

			// Création d'une connexion socket
			try {
				BattleshipClient com = new BattleshipClient(host, port);
				
				com.send("NEW:Bernard");
				
				// Lancement de l'IHM du jeu
				IHM ihm = new IHM(com); 
				
			} catch (ConnectException e) {
				System.err.println("Serveur indisponible : veuillez réessayer plus tard");
		    } catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		
	}

}
