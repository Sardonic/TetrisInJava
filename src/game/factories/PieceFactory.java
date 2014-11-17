package game.factories;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import game.core.Board;
import game.core.Piece;

public class PieceFactory {
	
	private static Random rand = new Random();

	public static Piece generateRandomPiece(Board board, double secondsBetweenMoves) {
		int randInt = rand.nextInt(7);
		Piece randPiece;
		
		switch(randInt)
		{
		case 0:
			randPiece = generateLPiece(board, secondsBetweenMoves);
			break;
		case 1:
			randPiece = generateJPiece(board, secondsBetweenMoves);
			break;
		case 2:
			randPiece = generateZPiece(board, secondsBetweenMoves);
			break;
		case 3:
			randPiece = generateSPiece(board, secondsBetweenMoves);
			break;
        case 4:
			randPiece = generateTPiece(board, secondsBetweenMoves);
			break;
        case 5:
			randPiece = generateIPiece(board, secondsBetweenMoves);
			break;
        case 6:
			randPiece = generateOPiece(board, secondsBetweenMoves);
			break;
        default:
        	randPiece = generateLPiece(board, secondsBetweenMoves);
        	break;
		}
		
		return randPiece;
	}
	
	public static Piece generateLPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.BLUE;
		Point[] locations = new Point[4];
		locations[0] = new Point(3, 1);
		locations[1] = new Point(3, 0);
		locations[2] = new Point(4, 0);
		locations[3] = new Point(5, 0);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateJPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.ORANGE;
		//Point[] locations = {{1, 5}, {0, 3}, {0, 4}, {0, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(5, 1);
		locations[1] = new Point(3, 0);
		locations[2] = new Point(4, 0);
		locations[3] = new Point(5, 0);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateZPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.RED;
		//Point[] locations = {{0, 3}, {0, 4}, {1, 4}, {1, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(3, 0);
		locations[1] = new Point(4, 0);
		locations[2] = new Point(4, 1);
		locations[3] = new Point(5, 1);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateSPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.GREEN;
		//Point[] locations = {{1, 3}, {0, 4}, {1, 4}, {0, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(3, 1);
		locations[1] = new Point(4, 0);
		locations[2] = new Point(4, 1);
		locations[3] = new Point(5, 0);;
		
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateTPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.PINK;
		Point[] locations = new Point[4];
		locations[0] = new Point(3, 0);
		locations[1] = new Point(4, 0);
		locations[2] = new Point(5, 0);
		locations[3] = new Point(4, 1);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateIPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.CYAN;
		Point[] locations = new Point[4];
		locations[0] = new Point(3, 0);
		locations[1] = new Point(4, 0);
		locations[2] = new Point(5, 0);
		locations[3] = new Point(6, 0);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}

	public static Piece generateOPiece(Board board, double secondsBetweenMoves) {
		Color color = Color.MAGENTA;
		Point[] locations = new Point[4];
		locations[0] = new Point(4, 0);
		locations[1] = new Point(5, 0);
		locations[2] = new Point(4, 1);
		locations[3] = new Point(5, 1);
		
		Piece returnPiece = new Piece(board, locations, color, secondsBetweenMoves);
		return returnPiece;
	}
}
