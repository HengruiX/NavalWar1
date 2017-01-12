import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoPilot {

	private static final int DELAY = 400;
	private static final double dtheta = Math.PI / 20;
	private Ship ship;
	private Ship target;
	private Landscape landscape;
	private Timer timer;

	public AutoPilot(Ship ship, Ship target, Landscape landscape) {
		this.ship = ship;
		this.target = target;
		this.landscape = landscape;
		timer = new Timer(DELAY, new AutoPilotListener());
	}

	public void start() {
		timer.start();
		ship.speedUp(ship.getMaxSpeed());
	}

	private class AutoPilotListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			double dx = target.getX() - ship.getX();
			double dy = -target.getY() + ship.getY();
			double direction = ship.getDirection();
			direction = reduceAngle(direction);
			double theta = Math.atan2(dy, dx) - direction;
			if (Math.abs(theta) > Math.PI)
				theta = -theta;
			ship.turn(Math.signum(theta) * dtheta * ship.getSpeed() * 100, landscape);
			if (ship.torpedoReady()) {
				ship.fireTorpedo();
			}
			if (ship.specialMoveReady()) {
				ship.specialMove();
			}
			if (ship.getSpeed() < ship.getMaxSpeed()) {
				ship.speedUp(ship.getMaxSpeed() - ship.getSpeed());
			}
		}

		private double reduceAngle(double theta) {
			while (theta > Math.PI)
				theta -= Math.PI * 2;
			while (theta < -Math.PI)
				theta += Math.PI * 2;
			return theta;
		}
	}

}
