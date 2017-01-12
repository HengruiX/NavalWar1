import javax.swing.*;

public class Runner {

	public static void main(String[] args) {
		try {
			JFrame jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Controller controller = new Controller();
			controller.init(jFrame);
			jFrame.pack();
			jFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
