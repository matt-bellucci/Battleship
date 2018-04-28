package fr.insarouen.battleship;


import java.net.*;
import java.io.*;
import java.nio.*;

/**
 * Class TestClient 
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class TestClient {


    public static void main(String[] args){
	Socket socket = null;
	String str = "";
	try {
		byte[] ip = {127,0,0,1};
		InetAddress serveur = InetAddress.getByAddress(ip);
		socket = new Socket(serveur,1099);
		PrintStream out = new PrintStream(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(
							   new InputStreamReader(System.in));

		BufferedInputStream reader2 = new BufferedInputStream(connexion.getInputStream());
		
	    while (str != "FIN") {
	    	System.out.println("Texte à envoyer : ");
	        str = reader.readLine();
	        out.print(str);
	        
	        String response = read();

            System.out.println("\t * " + name + " : Réponse reçue " + response);
            
	    }
	    
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (socket != null) socket.close();
	    } catch (IOException e) {} 
	}
    }
   
}
