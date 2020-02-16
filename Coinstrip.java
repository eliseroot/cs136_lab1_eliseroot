import java.util.Scanner;
import java.util.Random;


class CoinStrip{

  public int[] spaces; //represents the arrangement of coins and blank spaces
  public int player; //tells which player (1 or 2) has the move
  public int numSpaces = 10; //the number of spaces on the board
  public int numCoins = 5; //he number of coins in the game

  public static void main(String[] args){
    CoinStrip coinstrip = new CoinStrip();
  }

  public CoinStrip(){
    setUpBoard();
    makeMove();
  }

  //sets up a board to play a new game
  public void setUpBoard(){
    spaces = new int[numSpaces];
    for( int n=0; n< numSpaces; n++){
      spaces[n] = 0;
    }
    Random r = new Random();
    //gets all the coins in random spaces on the board
    for( int n=1; n<numCoins+1; n++){
      int blank = r.nextInt(numSpaces);
      if( spaces[blank] == 0){
        spaces[blank] = n;
      } else {
        n--;
      }
    }
    System.out.println(toString(spaces));
  }

  //converts the array that represents the board to a string to be displayed
  public String toString(int[] spaces){
    String board = "|";
    for (int n=0; n<numSpaces; n++){
      if (spaces[n] == 0){
        board = board + "  ";
      } else{
      board = board + " " + spaces[n];
      }
      board = board + " |";
    }
    return board;
  }

  //allows a player to move a coin
  public void makeMove(){
    //asks user which coin to move and how far
    Scanner in = new Scanner(System.in);
    System.out.println("Which coin would you like to move?");
    int toMove = in.nextInt();
    System.out.println("How many spaces would you like to move it?");
    int howFar = in.nextInt();
    //checks if the move is legal
    //moves the coin
    int moveFrom = 0;
    for( int n=0; n<numSpaces; n++){
      if (spaces[n]== toMove){
        moveFrom = n;
      }
    }
    spaces[moveFrom - howFar] = spaces[moveFrom];
    spaces[moveFrom] = 0;
    System.out.println(toString(spaces));
  }

}
