package fr.insarouen.battleship;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.net.*;

/** 
 * Server Program
 * @author David ALBERT
 *
 */
public class BattleshipServer {

    public static void main(String[] args){
    	
    	 String host = "192.168.1.22";
    	 int port = 1099;
    	 
    	 // Récupération-Initialisation des données- Instanciation du Modèle 
    	 DataServer data = new DataServer();
    	 
    	 // Ouverture du Serveur
    	 ServerCommunication serverThread = new ServerCommunication(host, port, data);
    	 serverThread.open();
    	 System.out.println("Serveur initialisé.");
    	 
    }
    
}
