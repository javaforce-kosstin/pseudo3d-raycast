package engine;

public class Launcher {

	public static void main(String[] args) {
		Engine engine = new Engine(new MyGame());
		engine.start();
	}

}
