
public class Destroyer extends Ship {

	private static final int LENGTH = 20;
	private static final int WIDTH = 6;
	private static final double MAX_SPEED = 0.025;
	private static final double MIN_SPEED = 0;
	private static final int SPECIAL_RECHARGE = 15000;
	private static final int RECHARGE = 500;
	private static final double TORPEDO_SPEED = 0.08;
	private static final int TORPEDO_DAMAGE = 2;
	private static final int TORPEDO_LENGTH = 4;
	private static final int TORPEDO_WIDTH = 2;
	private static final int MAX_HEALTH = 120;
	private int recharge_dec = 0;

	public Destroyer(int x, int y) {
		super(x, y);
	}

	@Override
	public void fireTorpedo() {
		if (torpedoRecharge == 0) {
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction, TORPEDO_DAMAGE, TORPEDO_LENGTH, TORPEDO_WIDTH));
			torpedoRecharge = RECHARGE - recharge_dec;
		}
	}

	@Override
	public void specialMove() {
		if (specialMoveRecharge == 0) {
			recharge_dec += 50;
			specialMoveRecharge = SPECIAL_RECHARGE;
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
		return MAX_SPEED;
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
