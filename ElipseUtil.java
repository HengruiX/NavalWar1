
public class ElipseUtil {

	public static boolean isWithin(double x, double y, double length, double direction, double width, double other_x,
			double other_y) {
		double dx = other_x - x;
		double dy = -other_y + y;
		double theta = Math.atan2(dy, dx) - direction;
		double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double da = d * Math.cos(theta);
		double db = d * Math.sin(theta);
		return Math.pow(da * 2 / length, 2) + Math.pow(db * 2 / width, 2) <= 1;
	}

}
