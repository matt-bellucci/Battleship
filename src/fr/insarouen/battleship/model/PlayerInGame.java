package fr.insarouen.battleship.model;

public class PlayerInGame extends Player {
    
    private Grid playerGrid;
    private Boats playerBoats;
    
    
    public PlayerInGame(){
        super();
        this.playerGrid = new Grid();
        this.playerBoats = new Boats();
        
    }
    
    public PlayerInGame(Player player){
        super(player.getName(), player.getIP());
        this.playerGrid = new Grid();
        this.playerBoats = new Boats(0,new Boat(2),new Boat(3),new Boat(3),new Boat(4),new Boat(4),new Boat(5));
    }
    
    public PlayerInGame(Player player, Boats boats){
        super(player.getName(), player.getIP());
        this.playerGrid = new Grid();
        this.playerBoats = boats;
    }
    
    public PlayerInGame(Player player, Grid grid){
        super(player.getName(), player.getIP());
        this.playerGrid = grid;
    }
    
    public void placeBoatsRandomly(){
    	this.playerGrid.placeBoatsRandomly(playerBoats);
    }
    
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
    
    public boolean hasLost(){
        return playerBoats.allSunk();
    }
    
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
    	return "Grille du joueur : "+getInitialGrid()"\n";
    }
}
