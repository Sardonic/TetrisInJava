package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;
import org.junit.Before;

import game.GameManager;
import game.TetrisMomento;
import game.core.Board;
import game.core.Piece;

public class GameManagerTests {
	GameManager manager;

	@Before
	public void init() {
		manager = new GameManager(640, 480);
	}

	@Test
	public void testCreateMemento() {
		TetrisMomento memento = manager.createMomento();
		assertFalse(memento.empty());
	}
	
	@Test
	public void testLoadMemento() {
		TetrisMomento memento = manager.createMomento();
		manager.loadMomento(memento);
		assertTrue(memento.empty());
	}
	
	@Test
	public void testSwapPieces() {
		Board board = new Board(new Point2D.Double(0, 0), 480);
		Piece piece = manager.getCurrentPiece().copy(board);
		manager.swapPieces();
		Piece newPiece = manager.getCurrentPiece();
		assertFalse(piece == newPiece);
	}
	
	@Test
	public void testNextPiece() {
		Board board = new Board(new Point2D.Double(0, 0), 480);
		Piece nextPiece = manager.getNextPiece().copy(board);
		manager.swapPieces();
		Piece newPiece = manager.getCurrentPiece();
		assertTrue(nextPiece.equals(newPiece));
	}
}
