
public class FastBoat extends Ship {

	private static final int LENGTH = 12;
	private static final int WIDTH = 4;
	private static final double MAX_SPEED = 0.035;
	private static final double MIN_SPEED = 0;
	private static final int RECHARGE = 2000;
	private static final int SPECIAL_RECHARGE = 10000;
	private static final double TORPEDO_SPEED = 0.1;
	private static final int TORPEDO_DAMAGE = 5;
	private static final int TORPEDO_LENGTH = 3;
	private static final int TORPEDO_WIDTH = 2;
	private static final int MAX_HEALTH = 80;
	private double speed_inc;

	public FastBoat(int x, int y) {
		super(x, y);
		speed_inc = 0;
	}

	@Override
	public void fireTorpedo() {
		if (torpedoRecharge == 0) {
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction, TORPEDO_DAMAGE, TORPEDO_LENGTH, TORPEDO_WIDTH));
			torpedoRecharge = RECHARGE;
		}
	}

	@Override
	public void specialMove() {
		if (specialMoveRecharge == 0) {
			speed_inc += 0.005;
			specialMoveRecharge = SPECIAL_RECHARGE;
			System.out.println(speed_inc);
		}
	}

	@Override
	public int getLength() {
		return LENGTH;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public double getMaxSpeed() {
		return (MAX_SPEED + speed_inc);
	}

	@Override
	public double getMinSpeed() {
		return MIN_SPEED;
	}

	@Override
	public int getMaxHealth() {
		return MAX_HEALTH;
	}
}
