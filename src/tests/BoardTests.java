package tests;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import game.core.AbstractBlock;
import game.core.Board;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class BoardTests {
	Board board;
	@Mock AbstractBlock block;
	
	@Before
	public void init() {
		// These don't really matter for the sake of testing
		Point2D location = new Point2D.Double(0, 0);
		int width = 640;
		int height = 480;
		
		board = new Board(location, width, height);
		board.init();
		
		MockitoAnnotations.initMocks(this);
		when(block.isSolid()).thenReturn(true);
	}

	@Test
	public void testCanPlaceAt() {
		assertTrue(board.canPlaceAt(0, 0));
		assertTrue(board.canPlaceAt(3, 3));
		assertFalse(board.canPlaceAt(-5, 0));
		assertFalse(board.canPlaceAt(1000, 1000));
	}
	
	@Test
	public void testBlockPlacing() {
		board.placeBlock(block, 0, 0);
		assertTrue(board.isBlockAtLocation(0, 0));
		board.placeBlock(block, 3, 2);
		assertTrue(board.isBlockAtLocation(3, 2));
	}
	
	@Test
	public void testBlockRemoval() {
		board.placeBlock(block, 0, 0);
		board.removeBlock(0, 0);
		assertFalse(board.isBlockAtLocation(0, 0));
		
		board.placeBlock(block, 2, 2);
		board.removeBlock(2, 2);
		assertFalse(board.isBlockAtLocation(2, 2));
	}
	
	@Test
	public void testCheckRows() {
		for (int i = 0; i < Board.NUM_COLS; ++i)
		{
			board.placeBlock(block, 0, i);
			board.placeBlock(block, 1, i);
			board.placeBlock(block, 3, i);
		}
		
		ArrayList<Integer> rows = board.checkRows();
		
		assertTrue(rows.contains(0));
		assertTrue(rows.contains(1));
		assertTrue(rows.contains(3));
		assertFalse(rows.contains(2));
	}
	
	@Test
	public void testRemoveRows() {
		for (int i = 0; i < Board.NUM_COLS; ++i)
		{
			board.placeBlock(block, 0, i);
			board.placeBlock(block, 1, i);
			board.placeBlock(block, 3, i);
		}
		
		ArrayList<Integer> rows = board.checkRows();
		
		int lines = board.removeRows(rows);
		
		assertEquals(3, lines);
		
		for (int i = 0; i < Board.NUM_COLS; ++i)
		{
			assertFalse(board.isBlockAtLocation(0, i));
			assertFalse(board.isBlockAtLocation(1, i));
			assertFalse(board.isBlockAtLocation(3, i));
		}
	}
	
	@Test
	public void testCheckLoss() {
		for (int i = 0; i < Board.NUM_ROWS; ++i)
		{
			board.placeBlock(block, i, 0);
		}
		
		assertTrue(board.checkLoss());
	}
}
