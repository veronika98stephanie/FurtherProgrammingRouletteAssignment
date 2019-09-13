package client;

import javax.swing.SwingUtilities;

import view.WheelMainFrame;
import viewmodel.WheelGameViewModel;

public class GUIClient {
	public static void main(String[] args) {
		WheelGameViewModel wheelGameViewModel = new WheelGameViewModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new WheelMainFrame(wheelGameViewModel);
			}
		});
	}
}
