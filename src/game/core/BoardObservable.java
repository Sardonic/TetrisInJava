package game.core;

public interface BoardObservable {
	void addObserver(BoardObserver o);
	void removeObserver(BoardObserver o);
	void notifyObservers(int rowsRemoved);
}
