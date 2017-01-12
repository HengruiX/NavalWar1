import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	public static final int DELAY = 50;
	public static final Dimension size = new Dimension(800, 800);
	private static final double dtheta = Math.PI / 20;
	private static final double dspeed = 0.001;
	private boolean[] enableAutoPilot;
	private List<Ship> ships;
	private Landscape landscape;

	/**
	 * constructs a new game with player ships and the specified landscape
	 *
	 * @param landscape
	 *            the landscape chosen for the game
	 * @param ships
	 *            the player ships at their initial location
	 * @param enableAutoPilot
	 *            whether the autopilot mode is enabled for the specific player
	 *            ship, the first element must be false
	 * @precondition landscape.isAvailable() for all the player ships at their
	 *               initial point, all initial points should be distinct from
	 *               each other, enableAutoPilot.length == ships.length
	 */
	public Game(Landscape landscape, boolean[] enableAutoPilot, Ship... ships) {
		this.ships = Arrays.asList(ships);
		this.landscape = landscape;
		this.enableAutoPilot = enableAutoPilot;
		for (int i = 1; i < this.ships.size(); i++) {
			if (enableAutoPilot[i] == true)
				this.ships.get(i).attachAutoPilot(landscape, this.ships.get(0));
		}
	}

	public void init() {
		List<Ship> temp = new ArrayList<>(ships.size());
		for (int i = 0; i < this.ships.size(); i++) {
			Ship ship;
			Class<? extends Ship> clazz = ships.get(i).getClass();
			if (clazz == BattleShip.class) {
				ship = new BattleShip((int) GameBoard.center.getX(), (int) GameBoard.center.getY());
			} else if (clazz == Destroyer.class) {
				ship = new Destroyer((int) GameBoard.center.getX(), (int) GameBoard.center.getY());
			} else {
				ship = new FastBoat((int) GameBoard.center.getX(), (int) GameBoard.center.getY());
			}
			temp.add(ship);
			if (enableAutoPilot[i] == true)
				ship.attachAutoPilot(landscape, this.ships.get(0));
		}
		ships = temp;
	}

	public void left(int i) {
		ships.get(i).turn(dtheta, landscape);
	}

	public void right(int i) {
		ships.get(i).turn(-dtheta, landscape);
	}

	public void up(int i) {
		if (ships.get(i).getSpeed() < ships.get(i).getMaxSpeed())
			ships.get(i).speedUp(dspeed);
	}

	public void down(int i) {
		if (ships.get(i).getSpeed() > ships.get(i).getMinSpeed())
			ships.get(i).speedUp(-dspeed);
	}

	public void fire(int i) {
		ships.get(i).fireTorpedo();
	}

	public void specialMove(int i) {
		ships.get(i).specialMove();
	}

	public void draw(Graphics graphics) {
		landscape.draw(graphics);
		for (Ship ship : ships)
			ship.draw(graphics);
	}

	public void advance(int dt) {
		for (Ship ship : ships) {
			ship.advance(dt, landscape);
			ship.checkHit(ships);
		}
		int i = checkLost();
		if (i != -1) {
			if (JOptionPane.showConfirmDialog(null,
					"Ship number " + i + " is sunk! Play again?") == JOptionPane.YES_OPTION)
				init();
			else
				System.exit(0);
		}

	}

	private int checkLost() {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getHealth() <= 0)
				return i;
		}
		return -1;
	}

	public double getPercetage(int i) {
		return (double) ships.get(i).getHealth() / ships.get(i).getMaxHealth();
	}

	public boolean specialMoveReady(int i) {
		return ships.get(i).specialMoveReady();
	}
}
