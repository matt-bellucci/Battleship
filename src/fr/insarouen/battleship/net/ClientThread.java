package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;

import fr.insarouen.battleship.controler.*;

/**
 * Class ClientThread : Thread lancé côté server à chaque connexion d'un client. Il reçoit les messages, les traite, et renvoie une réponse.
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class ClientThread extends Thread {

	private Socket socket = null;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private AbstractControler controler;


	public ClientThread(Socket socket, AbstractControler controler) throws IOException{
		this.socket = socket;
		this.controler = controler;
	}
	
	
	@Override
	public void start(){
		super.start();
	}
	
	@Override
	public void run() {
			 
		System.err.println("Lancement du traitement de la connexion cliente");
		boolean closeConnexion = false;
		
		//tant que la connexion est active, on traite les demandes
		while(!socket.isClosed()){
			try {
					
				writer = new PrintWriter(socket.getOutputStream());
				reader = new BufferedInputStream(socket.getInputStream());
				
				String response = read();
				InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
				
				//On affiche quelques infos
				String debug = "";
				debug = "Thread : " + Thread.currentThread().getName() + ". ";
				debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
				debug += " Sur le port : " + remote.getPort() + ".\n";
				debug += "\t -> Commande reçue : " + response + "\n";
				System.err.println("\n" + debug);

				//On traite la demande du client en fonction de la commande envoyée
				String toSend = "";
				switch(response.toUpperCase()){
				case "LIST":
					toSend = controler.getPlayers();
					break;
				case "NEW":
					controler.newPlayer();
					toSend = "Joueur ajouté";
					break;
				case "0":
					toSend = "PLOUF";
					break;
				case "CLOSE":
					toSend = "Communication terminée"; 
					controler.rm();
					closeConnexion = true;
					break;
				default : 
					toSend = "Commande inconnu !";                     
					break;
				}

				//On envoie la réponse au client
				writer.write(toSend);
				writer.flush();
				
				//On ferme la connexion
				if(closeConnexion){
					System.err.println("COMMANDE CLOSE DETECTEE ! ");
					writer = null;
					reader = null;
					socket.close();
					break;
				}
			} catch (SocketException e) {
				System.err.println("LA CONNEXION A ETE INTERROMPUE");
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		}
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
