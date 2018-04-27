package fr.insarouen.battleship;

import fr.insarouen.battleship.net.*;
import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class TestNetwork {

    public static void main(String[] args){
    	
    	 String host = "127.0.0.1";
    	 int port = 1099;
    	 BattleshipServer serverThread = new BattleshipServer(host, port);
    	 serverThread.open();
    	 System.out.println("Serveur initialisé.");
    }
}
