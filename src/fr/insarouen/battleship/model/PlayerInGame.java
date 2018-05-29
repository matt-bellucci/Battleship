package fr.insarouen.battleship.model;

/**
* Describes a player that is "currently" in game.
*/

public class PlayerInGame extends Player {
    
    private Grid playerGrid;
    private Boats playerBoats;
    
    /**
      * Constructs a standard player in game with a collection of boats and a grid.
      */
    
    public PlayerInGame(){
        super();
        this.playerGrid = new Grid();
        this.playerBoats = new Boats();
        
    }
    
    /**
      * Constructs a new player in game.
      */

    public PlayerInGame(Player player){
        super(player.getName(), player.getIP());
        this.playerGrid = new Grid();
        this.playerBoats = new Boats(0, new SailingBoat(), new Boat(2),new Boat(3),new Boat(3),new Boat(4),new Boat(4),new Boat(5));
    }
    
    /**
      * Adds a new player, knowing both his name and ip adress.
      */

    public PlayerInGame(Player player, Boats boats){
        super(player.getName(), player.getIP());
        this.playerGrid = new Grid();
        this.playerBoats = boats;
    }
    
    public PlayerInGame(Player player, Grid grid){
        super(player.getName(), player.getIP());
        this.playerGrid = grid;
    }
    
    /**
      * Allows to place randomly each boats on the grid.
      */

    public void placeBoatsRandomly(){
    	this.playerGrid.placeBoatsRandomly(playerBoats);
    }
    
    /**
      * Return "SUNK", "TOUCHED" or "SPLASH" according to the result of a specific play.
      */

    public String discover(int x, int y){
		System.out.println("Decouvrir in player: "+x+y);
        playerGrid.discover(x, y);
        int id = playerGrid.getId(x, y);
        if (id<0){return "PLOUF";}
        else{
            Boat boat = playerBoats.getBoat(id);
            boat.touched();
            if (boat.isSunk()){return "COULE";}
            else{return "TOUCHE";}
        }
    }
    
    /**
      * Says if the player has lost or not by checking that all of his boats are sunk or not.
      */

    public boolean hasLost(){
        return playerBoats.allSunk();
    }
    
    /**
      * Allows to 
      */

    public String getInitialGrid(){
    	String str ="";
    	for (int l=0;l<playerGrid.getSize();l++){
    		for (int c=0;c<playerGrid.getSize();c++){
    			if (playerGrid.getId(l, c)==-1)
    				str+="0";
    			else 
    				str+="1";
    		}
    	}
    	return str;
    }
    
    @Override
    public String toString(){
    	return "Grille du joueur : "+getInitialGrid()+"\n";
    }
}
