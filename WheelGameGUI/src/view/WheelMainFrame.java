package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class WheelMainFrame extends JFrame {
	private WheelPanel wheelPanel;
	private WheelGameToolbar wheelGameToolbar;
	private WheelGameMenuBar wheelGameMenuBar;
	private PlayerInfoPanel playerInfoPanel;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private StatusBar statusBar;
	private int initialHeight;
	private int initialWidth;

	public WheelMainFrame(WheelGameViewModel wheelGameViewModel) {
		super("Wheel Game");
		initialHeight = screenSize.height / 2;
		initialWidth = screenSize.width / 2;
		this.wheelPanel = new WheelPanel(initialWidth, initialHeight, wheelGameViewModel);
		this.wheelGameToolbar = new WheelGameToolbar(wheelGameViewModel);
		this.wheelGameMenuBar = new WheelGameMenuBar(wheelGameViewModel);
		this.statusBar = new StatusBar(wheelGameViewModel);
		this.playerInfoPanel = new PlayerInfoPanel(wheelGameViewModel, this);

		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(initialWidth, initialHeight));

		add(wheelPanel, BorderLayout.CENTER);
		add(wheelGameToolbar, BorderLayout.NORTH);
		add(statusBar, BorderLayout.SOUTH);
		add(playerInfoPanel, BorderLayout.EAST);
		setJMenuBar(wheelGameMenuBar);

		setSize(initialWidth, initialHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
