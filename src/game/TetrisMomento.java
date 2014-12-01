package game;

import java.util.ArrayList;

public class TetrisMomento {
	ArrayList<Object> objArray;
	ArrayList<Integer> intArray;

	public void addObj(Object obj) {
		objArray.add(obj);
	}
	
	public Object removeObj() {
		return objArray.remove(0);
	}

	public void addInt(int someInt) {
		intArray.add(someInt);
	}
	
	public int removeInt() {
		return intArray.remove(0);
	}
}