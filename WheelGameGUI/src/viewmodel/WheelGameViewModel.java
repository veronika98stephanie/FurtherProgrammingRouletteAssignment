package viewmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.GameEngineImpl;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.AddPlayerDialogBox;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class WheelGameViewModel {
	public static final String ADD_PLAYER = "add player";
	public static final String REMOVE_PLAYER = "remove player";
	public static final String NEXT_SLOT = "next slot";
	public static final String UPDATE_SUMMARY = "update summary";
	public static final String DISABLE_BET = "disable bet";
	public static final String ENABLE_BET = "enable bet";
	public static final String DISABLE_SPIN = "disable spin";
	public static final String ENABLE_SPIN = "enable spin";
	public static final String UPDATE_WHEEL = "update wheel";
	public static final String DISABLE_ADD_PLAYER = "disable add player";
	public static final String ENABLE_ADD_PLAYER = "enable add player";
	public static final String DISABLE_REMOVE_PLAYER = "disable add player";
	public static final String ENABLE_REMOVE_PLAYER = "enable add player";
	public static final String ENABLE_EXIT = "enable exit";
	public static final String DISABLE_EXIT = "disable exit";

	private GameEngine gameEngine;
	private GameEngineCallback gameEngineCallbackGUI;
	private Player currentSelectedPlayer = null;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private Map<String, Integer> participatingPlayers = new HashMap<>();

	public WheelGameViewModel() {
		this.gameEngine = new GameEngineImpl();
		this.gameEngineCallbackGUI = new GameEngineCallbackGUI(this);
		GameEngineCallback gameEngineCallbackImpl = new GameEngineCallbackImpl();
		this.gameEngine.addGameEngineCallback(gameEngineCallbackImpl);
		this.gameEngine.addGameEngineCallback(this.gameEngineCallbackGUI);
	}

	public void addPropertyChangeListener(PropertyChangeListener newListener) {
		pcs.addPropertyChangeListener(newListener);
	}

	public void addPlayer(Player newPlayer) {
		this.gameEngine.addPlayer(newPlayer);
		pcs.firePropertyChange(ADD_PLAYER, null, gameEngine.getAllPlayers());
		this.enableBet();
		this.enableSpin();
	}

	public void removePlayer() {
		if (this.currentSelectedPlayer != null) {
			this.gameEngine.removePlayer(this.currentSelectedPlayer);
			pcs.firePropertyChange(REMOVE_PLAYER, null, this.gameEngine.getAllPlayers());
			if (this.gameEngine.getAllPlayers().size() == 0) {
				this.disableBet();
				this.disableSpin();
			}
		}
	}

	public Collection<Player> getAllPlayer() {

		return this.gameEngine.getAllPlayers();
	}

	public void setCurrentSelectedPlayer(Player selectedPlayer) {
		this.currentSelectedPlayer = selectedPlayer;
	}

	public Player getCurrentSelectedPlayer() {
		return this.currentSelectedPlayer;
	}

	public void showAddPlayerDialogBox() {
		new AddPlayerDialogBox(this).setVisible(true);
	}

	public boolean placeBet(int bet, BetType betType) {
		return this.gameEngine.placeBet(this.currentSelectedPlayer, bet, betType);
	}

	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		new Thread() {
			@Override
			public void run() {
				gameEngine.spin(initialDelay, finalDelay, delayIncrement);
			}
		}.start();
		for (Player player : this.gameEngine.getAllPlayers()) {
			this.participatingPlayers.put(player.getPlayerId(), player.getPoints());
		}
	}

	public void removeParticipatingPlayers() {
		this.participatingPlayers.clear();
	}

	public int calculateResult(Player player) {
		if (this.participatingPlayers.containsKey(player.getPlayerId())) {
			int initialPoints = this.participatingPlayers.get(player.getPlayerId());
			int result = player.getPoints() - initialPoints;
			return result;
		}
		return 0;
	}

	public void updateStatus(String newStatus) {
		pcs.firePropertyChange(NEXT_SLOT, null, newStatus);
	}

	public void updateSummary() {
		pcs.firePropertyChange(UPDATE_SUMMARY, null, this.gameEngine.getAllPlayers());
	}

	public void updateSummary(Slot winningSlot) {
		pcs.firePropertyChange(UPDATE_SUMMARY, winningSlot, this.gameEngine.getAllPlayers());
	}

	public void disableBet() {
		pcs.firePropertyChange(DISABLE_BET, null, true);
	}

	public void enableBet() {
		pcs.firePropertyChange(ENABLE_BET, null, true);
	}

	public void disableSpin() {
		pcs.firePropertyChange(DISABLE_SPIN, null, true);
	}

	public void updateWheelDot(Integer slotNumber) {
		pcs.firePropertyChange(UPDATE_WHEEL, null, slotNumber);
	}

	public void enableSpin() {
		pcs.firePropertyChange(ENABLE_SPIN, null, true);
	}

	public void enableAddPlayer() {
		pcs.firePropertyChange(ENABLE_ADD_PLAYER, null, true);
	}

	public void disableAddPlayer() {
		pcs.firePropertyChange(DISABLE_ADD_PLAYER, null, true);
	}

	public void enableRemovePlayer() {
		pcs.firePropertyChange(ENABLE_REMOVE_PLAYER, null, true);
	}

	public void disableRemovePlayer() {
		pcs.firePropertyChange(DISABLE_REMOVE_PLAYER, null, true);
	}

	public void enableExit() {
		pcs.firePropertyChange(ENABLE_EXIT, null, true);
	}

	public void disableExit() {
		pcs.firePropertyChange(DISABLE_EXIT, null, true);
	}
}
