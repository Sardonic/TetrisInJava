package game.factories;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import game.core.Board;
import game.core.Piece;

public class PieceFactory {
	
	private static Random rand = new Random();

	public static Piece generateRandomPiece(Board board) {
		int randInt = rand.nextInt(6);
		Piece randPiece;
		
		switch(randInt)
		{
		case 0:
			randPiece = generateLPiece(board);
			break;
		case 1:
			randPiece = generateJPiece(board);
			break;
		case 2:
			randPiece = generateZPiece(board);
			break;
		case 3:
			randPiece = generateSPiece(board);
			break;
        case 4:
			randPiece = generateTPiece(board);
			break;
        case 5:
			randPiece = generateIPiece(board);
			break;
        case 6:
			randPiece = generateOPiece(board);
			break;
        default:
        	randPiece = generateLPiece(board);
        	break;
		}
		
		return randPiece;
	}
	
	public static Piece generateLPiece(Board board) {
		Color color = Color.BLUE;
		Point[] locations = new Point[4];
		locations[0] = new Point(1, 3);
		locations[1] = new Point(0, 3);
		locations[2] = new Point(0, 4);
		locations[3] = new Point(0, 5);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateJPiece(Board board) {
		Color color = Color.ORANGE;
		//Point[] locations = {{1, 5}, {0, 3}, {0, 4}, {0, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(1, 5);
		locations[1] = new Point(0, 3);
		locations[2] = new Point(0, 4);
		locations[3] = new Point(0, 5);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateZPiece(Board board) {
		Color color = Color.RED;
		//Point[] locations = {{0, 3}, {0, 4}, {1, 4}, {1, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(0, 3);
		locations[1] = new Point(0, 4);
		locations[2] = new Point(1, 4);
		locations[3] = new Point(1, 5);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateSPiece(Board board) {
		Color color = Color.GREEN;
		//Point[] locations = {{1, 3}, {0, 4}, {1, 4}, {0, 5}};
		Point[] locations = new Point[4];
		locations[0] = new Point(1, 3);
		locations[1] = new Point(0, 4);
		locations[2] = new Point(1, 4);
		locations[3] = new Point(0, 5);;
		
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateTPiece(Board board) {
		Color color = Color.YELLOW;
		Point[] locations = new Point[4];
		locations[0] = new Point(0, 3);
		locations[1] = new Point(0, 4);
		locations[2] = new Point(0, 5);
		locations[3] = new Point(1, 4);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateIPiece(Board board) {
		Color color = Color.CYAN;
		Point[] locations = new Point[4];
		locations[0] = new Point(0, 3);
		locations[1] = new Point(0, 4);
		locations[2] = new Point(0, 5);
		locations[3] = new Point(0, 6);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}

	public static Piece generateOPiece(Board board) {
		Color color = Color.MAGENTA;
		Point[] locations = new Point[4];
		locations[0] = new Point(0, 4);
		locations[1] = new Point(0, 5);
		locations[2] = new Point(1, 4);
		locations[3] = new Point(1, 5);
		
		Piece returnPiece = new Piece(board, locations, color);
		return returnPiece;
	}
}
