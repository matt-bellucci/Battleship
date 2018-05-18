package fr.insarouen.battleship.view;

public enum StateIHM {
	PLAYER_IDENTIFICATION("Choisissez un nom. Celui-ci ne doit pas être déjà utilisé par un autre joueur."),
	OPPONENT_CHOICE("Choisissez votre joueur adverse"),
	IN_GAME("Partie en cours");
	
	private String help = "";
	
	StateIHM(String help){
		this.help = help;
	}
	
	public String getHelp(){
		return this.help;
	}
	
}
