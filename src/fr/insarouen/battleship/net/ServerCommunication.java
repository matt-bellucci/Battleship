package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;

import fr.insarouen.battleship.controler.BattleshipControler;
import fr.insarouen.battleship.model.DataServer;

/**
 * Thread de communication serveur, recoit les connexions clientes et les traitent
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class ServerCommunication {


   //On initialise les attributs du server par défault
   private int port = 1099;
   private String host = "127.0.0.1";
   private static final int MAX_CONNEXIONS = 100;
   private DataServer data;
   
   private ServerSocket server = null;
   private boolean isRunning = true;
   
   public ServerCommunication(){
      try {
         server = new ServerSocket(port, MAX_CONNEXIONS, InetAddress.getByName(host));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public ServerCommunication(String pHost, int pPort, DataServer data){
      host = pHost;
      port = pPort;
      this.data = data;
      try {
         server = new ServerSocket(port, MAX_CONNEXIONS, InetAddress.getByName(host));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   //On lance le serveur
   public void open(){
      
      //Toujours dans un thread à part vu qu'il est dans une boucle infinie
       Thread t = new Thread( new Runnable(){
         public void run(){
            while(isRunning == true){
            	
               try {
                  //On attend une connexion d'un client
                  Socket client = server.accept();
                  
                  //Une fois reçue, on la traite dans un thread séparé
                  System.out.println("Connexion cliente reçue : "+client.getInetAddress());
                  
                  // instanciation du contrôleur et de la vue (ici représenté par le canal de communication avec la vue réelle)
                  ServerCommunicationThread t = new ServerCommunicationThread(client, new BattleshipControler(data, client.getInetAddress()));
                  
                  // Ajout du Thread comme observateur des données
                  data.addObserver(t);
                  
                  t.start();
                  
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
            
            try {
               server.close();
            } catch (IOException e) {
               e.printStackTrace();
               server = null;
            }
         }
      });
      
      t.start();
   }
   
   public void close(){
      isRunning = false;
   }   
}
