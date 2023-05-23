package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import java.util.HashMap;


class InputUnit {
	private boolean pressed = false;
	private boolean down = false;
	private boolean up = false;
	
	public InputUnit() { }
	
	public void update(boolean state) {
		down = state && !pressed;
		up = !state && pressed;
		pressed = state;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public boolean isUp() {
		return up;
	}
}


class DiscreteHandler implements KeyListener {
	private Map<Integer, Boolean> keys = new HashMap<>();
	
	public Set<Integer> getIterator() {
		return keys.keySet();
	}
	
	public boolean getState(int code) {
		if (!keys.containsKey(code))
			keys.put(code, false);
		return keys.get(code);
	}
	
	private void setState(KeyEvent e, boolean state) {
		keys.put(e.getKeyCode(), state);
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		setState(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		setState(e, false);
	}
}


public class Input {
	public static final int A = KeyEvent.VK_A;
	public static final int B = KeyEvent.VK_B;
	public static final int C = KeyEvent.VK_C;
	public static final int D = KeyEvent.VK_D;
	public static final int E = KeyEvent.VK_E;
	public static final int F = KeyEvent.VK_F;
	public static final int G = KeyEvent.VK_G;
	public static final int H = KeyEvent.VK_H;
	public static final int I = KeyEvent.VK_I;
	public static final int J = KeyEvent.VK_J;
	public static final int K = KeyEvent.VK_K;
	public static final int L = KeyEvent.VK_L;
	public static final int M = KeyEvent.VK_M;
	public static final int N = KeyEvent.VK_N;
	public static final int O = KeyEvent.VK_O;
	public static final int P = KeyEvent.VK_P;
	public static final int Q = KeyEvent.VK_Q;
	public static final int R = KeyEvent.VK_R;
	public static final int S = KeyEvent.VK_S;
	public static final int T = KeyEvent.VK_T;
	public static final int U = KeyEvent.VK_U;
	public static final int V = KeyEvent.VK_V;
	public static final int W = KeyEvent.VK_W;
	public static final int X = KeyEvent.VK_X;
	public static final int Y = KeyEvent.VK_Y;
	public static final int Z = KeyEvent.VK_Z;
	
	public static final int SHIFT = KeyEvent.VK_SHIFT;
	public static final int SPACE = KeyEvent.VK_SPACE;
	
	private final DiscreteHandler handler;
	private final Map<Integer, InputUnit> keys;
	
	public Input(JFrame window) {
		handler = new DiscreteHandler();
		window.addKeyListener(handler);
		keys = new HashMap<>();
	}
	
	private InputUnit getUnit(int code) {
		InputUnit unit = keys.get(code);
		if (unit == null) {
			unit = new InputUnit();
			keys.put(code, unit);
		}
		return unit;
	}
	
	public void update() {
		for(int code : handler.getIterator())
			getUnit(code).update(handler.getState(code));
	}
	
	public boolean isKeyUp(int code) {
		return getUnit(code).isUp();
	}
	
	public boolean isKeyDown(int code) {
		return getUnit(code).isDown();
	}
	
	public boolean isKeyPressed(int code) {
		return getUnit(code).isPressed();
	}
	
	public float getCoef(int posCode, int negCode) {
		float coef = 0;
		if (isKeyPressed(posCode))
			coef += 1;
		if (isKeyPressed(negCode))
			coef -= 1;
		return coef;
	}
	
}
