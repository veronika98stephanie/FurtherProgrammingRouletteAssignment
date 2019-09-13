package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.enumeration.BetType;
import view.PlaceBetDialogBox;

public class BetTypeChangeListener implements ItemListener {
	private PlaceBetDialogBox placeBetDialogBox;

	public BetTypeChangeListener(PlaceBetDialogBox placeBetDialogBox) {
		this.placeBetDialogBox = placeBetDialogBox;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			BetType item = (BetType) e.getItem();
			placeBetDialogBox.setBetTypeSelectedValue(item);
		}
	}
}
