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
public class BattleshipClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			String host = "172.18.18.250";
			int port = 3258;

			try {
				System.out.println("Lancement de l'IHM");
				IHM ihm = new IHM(host, port); 
			} catch (IndexOutOfBoundsException e){
				e.printStackTrace();
			}
		
		
	}

}
