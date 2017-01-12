
public class BattleShip extends Ship {

	private static final int LENGTH = 30;
	private static final int WIDTH = 8;
	private static final double MAX_SPEED = 0.01;
	private static final double MIN_SPEED = 0;
	private static final int SPECIAL_RECHARGE = 20000;
	private static final int RECHARGE = 10000;
	private static final double TORPEDO_SPEED = 0.08;
	private static final int TORPEDO_DAMAGE = 8;
	private static final int TORPEDO_LENGTH = 5;
	private static final int TORPEDO_WIDTH = 3;
	private static final int MAX_HEALTH = 200;

	public BattleShip(int x, int y) {
		super(x, y);
	}

	@Override
	public void fireTorpedo() {
		if (torpedoRecharge == 0) {
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction, TORPEDO_DAMAGE, TORPEDO_LENGTH, TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction + Math.PI / 6, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction - Math.PI / 6, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedoRecharge = RECHARGE;
		}
	}

	@Override
	public void specialMove() {
		if (specialMoveRecharge == 0) {
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction, TORPEDO_DAMAGE, TORPEDO_LENGTH, TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction + Math.PI / 4, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction - Math.PI / 4, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction + Math.PI / 2, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction - Math.PI / 2, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction + Math.PI * 3 / 4, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction - Math.PI * 3 / 4, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
			torpedos.add(new Torpedo(x, y, TORPEDO_SPEED, direction + Math.PI, TORPEDO_DAMAGE, TORPEDO_LENGTH,
					TORPEDO_WIDTH));
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
