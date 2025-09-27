package project;

import java.util.Random;
import java.util.Scanner;

class Tic1{	
		static char[][] board;
	  public Tic1(){
		board= new char[3][3] ; 
		initBoard();
	   }
	  void initBoard() {
		  for(int i=0;i<board.length;i++) {
			  
			  for(int j=0;j<board[i].length;j++) {
				  board[i][j]=' ';
			  }
		  }
	  }
	  void dispBoard() {
		  System.out.println("----------------");
		  for(int i=0;i<board.length;i++) {
			  System.out.print("| ");
			  for(int j=0;j<board[i].length;j++) {
				  System.out.print(board[i][j]+"  | ");  
			  }
			  System.out.println();
			  System.out.println(" ----------------");
		  }
	   }
	  
	  static void placeMark(int row,int col,char mark) {
		  if(row>=0 && row <=2 && col>=0 && col<=2) {
		  board[row][col]=mark;
	  }
		  else {
			  System.out.println("Invalid position");
		  }
	  }
	  
	  static boolean checkColWin() {

		  for(int j=0;j<=2;j++) {
			  if(board[0][j] !=' '&& board[0][j]== board[1][j] && board[1][j] ==board[2][j]) {
				  return true;
			  }
		  }
		return false;
	  }
	  
	  static boolean checkRowWin() {
		  for(int i=0;i<=2;i++) {
			  if(board[i][0] != ' ' && board[i][0] ==board [i][1] && board[i][1]== board[i][2]) {
				  return true;
			  }
		  }
		return false;
	  }
	  
	  static boolean checkDigWin() {
		if(board[0][0] != ' ' && board[0][0]== board[1][1] && board[1][1] ==board[2][2] || board[0][2] != ' '&& board[0][2]== board[1][1] && board[1][1] ==board[2][0] ){
			return true;	
		}else {
			return false;
		}
		   }
	
	  static boolean checkDraw() {
		  for(int i=0;i<=2;i++) {
			  for(int j=0;j<=2;j++) {
				  if(board[i][j]==' ') {
					  return false;
				  }
			  }
		  }
		return true;
	  }
	}
	
abstract class Player {
		String name;
		char mark;

		abstract void makeMove();
        boolean isValidMove(int row, int col) {
			if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
				if (Tic1.board[row][col] == ' ') {
					return true;
				}
			}
			return false;
		}
	}

class HumanPlayer1 extends Player{
       HumanPlayer1(String name, char mark) {
			this.name = name;
			this.mark = mark;
		}

		void makeMove() {
			Scanner sc = new Scanner(System.in);
			int row;
			int col;
			do {
				System.out.print("Enter the row and col");
				row = sc.nextInt(3);
				col = sc.nextInt(3);
			} while (!isValidMove(row, col));
			Tic1.placeMark(row, col, mark);
           }
        }

class AIPlayer extends Player {

		AIPlayer(String name, char mark) {
			this.name = name;
			this.mark = mark;
		}

		void makeMove() {
			Scanner sc = new Scanner(System.in);
			int row;
			int col;
			do {
				Random r = new Random();
				row = r.nextInt(3);
				col = r.nextInt(3);
			} while (!isValidMove(row, col));
			Tic1.placeMark(row, col, mark);

		}
	}

public class LaunchGame2 {
		public static void main(String[] args) {
			Tic1 t = new Tic1();
           HumanPlayer1 p1 = new HumanPlayer1("Sneha", 'x');
			AIPlayer p2 = new AIPlayer("TAI", 'o');
			Player cp1;
			cp1=p1;
			while (true) {
				System.out.println(cp1.name + " turn");
				cp1.makeMove();
				t.dispBoard();
				if (Tic1.checkColWin() || Tic1.checkRowWin() || Tic1.checkDigWin() ) {
					System.out.println(cp1.name + " has won");
					break;
				}
				else if(Tic1.checkDraw()) {
					System.out.println("Game is draw");
					break;
				}
				else {
					if (cp1 == p1) {
						cp1 = p2;
					} else {
						cp1 = p1;
					}
				}
			}

		}
	}

