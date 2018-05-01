package fr.insarouen.battleship.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.insarouen.battleship.net.BattleshipClient;

public class ListPlayers extends JPanel implements ActionListener {
	
	private JList<String> list;
	private DefaultListModel<String> listModel;
	
	private String playerName;
	private JButton play = new JButton("");
	
	private BattleshipClient com;
	
	// Classe interne gérant les changement de sélection
	class GestionSelection implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				String item = list.getSelectedValue();
				if (item != null) {
					play.setEnabled(true);
					play.setText("Jouer contre "+item);
				}
				else {
					play.setEnabled(false);
				}
			}
		}
	}
	
	
	public ListPlayers(BattleshipClient com, String u) {
		
		this.com = com;
		
		setBorder(BorderFactory.createTitledBorder("Choix adversaire"));
		setLayout(new BorderLayout(3,5));
		
		
		listModel = new DefaultListModel<String>();
		setDefaultList(u);
		
		list = new JList<String>(listModel);
		list.addListSelectionListener(new GestionSelection());
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane listScrollPane = new JScrollPane(list);
		add(listScrollPane, BorderLayout.CENTER);
		
		JPanel panneauBouton = new JPanel();
		add(panneauBouton, BorderLayout.SOUTH);
		
		
		play.addActionListener(this);
		add(play,BorderLayout.SOUTH);
	}
	

	// Implémentation de l'interface ActionListener
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JButton) {
			play.setText("Demande envoyée ..");
			com.send("DemandePartie:"+list.getSelectedValue());	
		} 
	}
	
	
	// Fonction qui permet de passer de la liste brut des joueur en listModel pour l'interface
	private void setDefaultList(String str){
		String lim = "\n";
		int l1=0, l2=str.indexOf(lim,l1);
		while (l2+1<str.length()){
			String sstr = str.substring(l1, l2);
			l1=l2+1;
			l2=str.indexOf(lim, l1);
			listModel.addElement(sstr);
		}
	}
	
}
