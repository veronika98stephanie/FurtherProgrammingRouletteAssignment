package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private List<Player> players = new ArrayList<Player>();
	private List<Slot> slots = new ArrayList<Slot>();
	private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private Random random;
	private int currentSlot;

	public GameEngineImpl() {
		this.createWheel();
	}

	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		random = new Random();
		currentSlot = random.nextInt(Slot.WHEEL_SIZE);

		for (int delay = initialDelay; delay < finalDelay; delay += delayIncrement) {
			for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
				gameEngineCallback.nextSlot(slots.get(currentSlot % Slot.WHEEL_SIZE), this);
			}

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			currentSlot += 1;
		}
		
		for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
			gameEngineCallback.result(slots.get(currentSlot % Slot.WHEEL_SIZE), this);
		}

	}

	@Override
	public void calculateResult(Slot winningSlot) {
		for (Player player : players) {
			try {
				player.getBetType().applyWinLoss(player, winningSlot);
			} catch (NullPointerException e) {
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		for (int i = 0; i < this.players.size();) {
			if (players.get(i).getPlayerId() == player.getPlayerId()) {
				players.set(i, player);
				return;
			} else {
				players.add(player);
				return;
			}
		}
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : players) {
			if (player.getPlayerId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		if (players.remove(player)) {
			return true;
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return this.gameEngineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(this.players);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if (player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Slot> getWheelSlots() {
		return Collections.unmodifiableCollection(this.slots);
	}

	private void createWheel() {
		slots = new ArrayList<Slot>(Arrays.asList(new SlotImpl(0, Color.GREEN00, 00), new SlotImpl(1, Color.RED, 27),
				new SlotImpl(2, Color.BLACK, 10), new SlotImpl(3, Color.RED, 25), new SlotImpl(4, Color.BLACK, 29),
				new SlotImpl(5, Color.RED, 12), new SlotImpl(6, Color.BLACK, 8), new SlotImpl(7, Color.RED, 19),
				new SlotImpl(8, Color.BLACK, 31), new SlotImpl(9, Color.RED, 18), new SlotImpl(10, Color.BLACK, 6),
				new SlotImpl(11, Color.RED, 21), new SlotImpl(12, Color.BLACK, 33), new SlotImpl(13, Color.RED, 16),
				new SlotImpl(14, Color.BLACK, 4), new SlotImpl(15, Color.RED, 23), new SlotImpl(16, Color.BLACK, 35),
				new SlotImpl(17, Color.RED, 14), new SlotImpl(18, Color.BLACK, 2), new SlotImpl(19, Color.GREEN0, 0),
				new SlotImpl(20, Color.BLACK, 28), new SlotImpl(21, Color.RED, 9), new SlotImpl(22, Color.BLACK, 26),
				new SlotImpl(23, Color.RED, 30), new SlotImpl(24, Color.BLACK, 11), new SlotImpl(25, Color.RED, 7),
				new SlotImpl(26, Color.BLACK, 20), new SlotImpl(27, Color.RED, 32), new SlotImpl(28, Color.BLACK, 17),
				new SlotImpl(29, Color.RED, 5), new SlotImpl(30, Color.BLACK, 22), new SlotImpl(31, Color.RED, 34),
				new SlotImpl(32, Color.BLACK, 15), new SlotImpl(33, Color.RED, 3), new SlotImpl(34, Color.BLACK, 24),
				new SlotImpl(35, Color.RED, 36), new SlotImpl(36, Color.BLACK, 13), new SlotImpl(37, Color.RED, 1)));
	}
}
