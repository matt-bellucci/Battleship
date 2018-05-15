package fr.insarouen.battleship.model;

public class PlayerInGame extends Player {
    
    private Grid playerGrid;
    
    
    public PlayerInGame(){
        super();
        this.playerGrid = new Grid();          
        
    }
    
    public PlayerInGame(Player player){
        super(player.getName(), player.getIP());
        this.playerGrid = new Grid();
    }
    
    public PlayerInGame(Player player, Grid grid){
        super(player.getName(), player.getIP());
        this.playerGrid = grid;
    }
    
    
    /*public Case getCase(Point2D point){
        playerGrid.getCase(point).getId();
    }*/
    
}
