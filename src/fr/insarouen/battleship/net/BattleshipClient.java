package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;


/**
 * Class BattleshipClient : Classe côté client qui permet la connexion au server et l'envoie de messages à celui-ci
 *
 * @author David ALBERT
 * @version 0
 * 
 */
public class BattleshipClient {
	
	private Socket connexion = null;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;

	   
	public BattleshipClient(String pHost, int pPort) throws UnknownHostException, IOException {
		connexion = new Socket(pHost,pPort);	  
		try {
			
			writer = new PrintWriter(connexion.getOutputStream(), true);
			reader = new BufferedInputStream(connexion.getInputStream());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	   
	public String send(String msg) {
	   String response = new String("Erreur");
	   try {
            
            //On envoie le message au serveur
            writer.write(msg);
            writer.flush();  

            System.out.println("Commande " + msg + " envoyée au serveur");

            //On attend la réponse
            response = read();
            System.out.println("\t Réponse reçue " + response);

         } catch (ConnectException e) {
				System.err.println("Serveur indisponible : veuillez réessayer plus tard");
         }  catch (IOException e1) {
            e1.printStackTrace();
         } catch (NullPointerException e1) {
	            e1.printStackTrace();
	         }
	   
	   return response;
   }

	   private String read() throws IOException{      
		   String response = "";
		   int stream;
		   byte[] b = new byte[4096];
		   stream = reader.read(b);
		   response = new String(b, 0, stream);      
		   return response;
	   }   
}
