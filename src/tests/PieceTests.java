package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import game.GameManager;
import game.core.Board;
import game.core.Piece;

public class PieceTests {
	private Piece piece;
	private Board board;
	private static final Point[] ORIG_LOCATIONS =
		{new Point(1, 0),
		 new Point(1, 1),
		 new Point(1, 2),
		 new Point(1, 3)};
	
	@Before
	public void init() {
		Point2D boardPos = new Point2D.Double(0, 0);
		int width = 640;
		int height = 480;
		board = new Board(boardPos, width, height);
		board.init();
		Point[] blockLocs = new Point[ORIG_LOCATIONS.length];
		for (int i = 0; i < ORIG_LOCATIONS.length; ++i) {
			blockLocs[i] = new Point();
			blockLocs[i].x = ORIG_LOCATIONS[i].x;
			blockLocs[i].y = ORIG_LOCATIONS[i].y;
		}
		piece = new Piece(board, blockLocs, Color.BLACK, 0.5);
	}
	
	@Test
	public void testMove() {
		piece.move(GameManager.DOWN);
		for (Point point : ORIG_LOCATIONS) {
			assertTrue(board.isBlockAtLocation(point.y + 1, point.x));
		}
		
		init();
		
		piece.move(GameManager.RIGHT);
		for (Point point : ORIG_LOCATIONS) {
			assertTrue(board.isBlockAtLocation(point.y, point.x + 1));
		}
		
		init();
		
		piece.move(GameManager.LEFT);
		for (Point point : ORIG_LOCATIONS) {
			assertTrue(board.isBlockAtLocation(point.y, point.x - 1));
		}
	}
	
	@Test
	public void testCheckCollision() {
		piece.move(GameManager.LEFT);
		assertTrue(piece.checkCollision(GameManager.LEFT));
	}
}
