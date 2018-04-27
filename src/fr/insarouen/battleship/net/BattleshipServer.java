package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;

/**
 * Class BattleshipServer 
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class BattleshipServer {


   //On initialise les attributs du server par défault
   private int port = 1099;
   private String host = "127.0.0.1";
   private ServerSocket server = null;
   private boolean isRunning = true;
   
   public BattleshipServer(){
      try {
         server = new ServerSocket(port, 100, InetAddress.getByName(host));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public BattleshipServer(String pHost, int pPort){
      host = pHost;
      port = pPort;
      try {
         server = new ServerSocket(port, 100, InetAddress.getByName(host));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   //On lance notre serveur
   public void open(){
      
      //Toujours dans un thread à part vu qu'il est dans une boucle infinie
      Thread t = new Thread( new Runnable(){
         public void run(){
            while(isRunning == true){
               
               try {
                  //On attend une connexion d'un client
                  Socket client = server.accept();
                  
                  //Une fois reçue, on la traite dans un thread séparé
                  System.out.println("Connexion cliente reçue.");                  
                  Thread t = new ClientThread(client);
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
