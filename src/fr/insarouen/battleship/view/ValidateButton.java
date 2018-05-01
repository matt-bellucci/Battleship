package fr.insarouen.battleship.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class ValidateButton extends JButton implements ActionListener {

	private boolean etat = true;
	
	ValidateButton(String arg){
		super(arg);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setText("Demande en cours ...");
	}

}
