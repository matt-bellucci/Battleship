package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;

import fr.insarouen.battleship.controler.BattleshipControler;
import fr.insarouen.battleship.model.DataServer;

/**
 * Class BattleshipServer : Gère les connexions des clients
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class BattleshipServer {


   //On initialise les attributs du server par défault
   private int port = 1099;
   private String host = "127.0.0.1";
   private static final int MAX_CONNEXIONS = 100;
   private DataServer data;
   
   private ServerSocket server = null;
   private boolean isRunning = true;
   
   public BattleshipServer(){
      try {
         server = new ServerSocket(port, MAX_CONNEXIONS, InetAddress.getByName(host));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public BattleshipServer(String pHost, int pPort, DataServer data){
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
                  Thread t = new ClientThread(client, new BattleshipControler(data, client.getInetAddress()));
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
