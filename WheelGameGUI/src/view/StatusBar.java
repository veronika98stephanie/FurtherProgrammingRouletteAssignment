package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements PropertyChangeListener {
	private JLabel slotStatus;
	private WheelGameViewModel wheelGameViewModel;

	public StatusBar(WheelGameViewModel wheelGameViewModel) {
		this.wheelGameViewModel = wheelGameViewModel;
		this.wheelGameViewModel.addPropertyChangeListener(this);
		setLayout(new FlowLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.slotStatus = new JLabel();
		setBorder(border);
		this.slotStatus.setText("Spin the wheel!");
		add(slotStatus);
	}

	public void setSlotStatus(String status) {
		this.slotStatus.setText(status);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == WheelGameViewModel.NEXT_SLOT) {
			this.setSlotStatus((String) evt.getNewValue());
		}

	}
}
