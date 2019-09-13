package view;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import controller.PlayerCellRenderer;
import model.interfaces.Player;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlayerJList extends JList<Player> {
	private Collection<Player> players;
	private WheelGameViewModel wheelGameViewModel;
	private DefaultListModel<Player> dm;

	public PlayerJList(DefaultListModel<Player> dm, WheelGameViewModel wheelGameViewModel) {
		this.dm = dm;
		this.wheelGameViewModel = wheelGameViewModel;
		this.players = wheelGameViewModel.getAllPlayer();
		this.setJList();
	}

	public void setJList() {
		dm.clear();
		for (Player player : players) {
			dm.addElement(player);
		}
		this.setModel(dm);
		if (dm.getSize() >= 1) {
			this.setSelectedIndex(0);
		} else {
			wheelGameViewModel.setCurrentSelectedPlayer(null);
		}
		this.setCellRenderer(new PlayerCellRenderer(this.wheelGameViewModel));
	}
}
