package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.interfaces.Player;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements PropertyChangeListener {
	private JScrollPane userScrollPane;
	private PlayerJList userJList;
	private DefaultListModel<Player> dm = new DefaultListModel<Player>();

	public PlayerPanel(WheelGameViewModel wheelGameViewModel) {
		wheelGameViewModel.addPropertyChangeListener(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel playerLabel = new JLabel("SELECTED PLAYER");
		this.userJList = new PlayerJList(dm, wheelGameViewModel);
		this.userScrollPane = new JScrollPane(userJList);

		userScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(playerLabel);
		add(userScrollPane);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == WheelGameViewModel.ADD_PLAYER
				|| evt.getPropertyName() == WheelGameViewModel.REMOVE_PLAYER) {
			this.userJList.setJList();
		}
	}

}
