import java.util.Scanner;
import java.util.Random;


class CoinStrip{

  public int[] spaces; //represents the arrangement of coins and blank spaces
  public Boolean player1 = true; //tells which player (1 or 2) has the move
  public int numSpaces; //the number of spaces on the board
  public int numCoins; //he number of coins in the game
  public String player = "Player 1: "; //addresses the correct player
  public Boolean playing; //tells whether the game is being played

  public static void main(String[] args){
    CoinStrip coinstrip = new CoinStrip();
  }

  public CoinStrip(){
    findInfo();
    setUpBoard();
    Boolean playing = isPlaying();
    while (playing){
      makeMove();
      playing = isPlaying();
    }
    switchPlayer();
    System.out.println( player + "You win!");
  }

  //find numSpaces and numCoins
  public void findInfo(){
    Scanner in = new Scanner(System.in);
    System.out.println("How many squares do you want to play with (choose 5-15)?");
    numSpaces = in.nextInt();
    System.out.println("How many coins do you want to play with (choose 3-10)?");
    numCoins = in.nextInt();
    if( numSpaces<5 || numSpaces>15){
      System.out.println("Invalid number of spaces.");
      findInfo();
    }
    if( numCoins<3 || numCoins>10){
      System.out.println("Invalid number of coins.");
      findInfo();
    }
    if(!(numCoins<numSpaces)){
      System.out.println("You need more spaces than coins to play.");
      findInfo();
    }
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
    playing = isPlaying();
    if (! playing){
      setUpBoard();
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
    System.out.println(player + "Which coin would you like to move?");
    int toMove = in.nextInt();
    System.out.println(player + "How many spaces would you like to move it?");
    int howFar = in.nextInt();
    //finds where the coin is being moved from
    int moveFrom = 0;
    for( int n=0; n<numSpaces; n++){
      if (spaces[n]== toMove){
        moveFrom = n;
      }
    }
    //checks if move is legal
    Boolean allowed = isLegal(moveFrom, howFar);
    //moves the coin
    if (allowed){
      spaces[moveFrom - howFar] = spaces[moveFrom];
      spaces[moveFrom] = 0;
      System.out.println(toString(spaces));
    } else{
      makeMove();
    }
    //switches the player
    switchPlayer();
  }

  //checks whether a move is legal
  public Boolean isLegal( int moveFrom, int howFar ){
    if (spaces[moveFrom] == 0){
      System.out.println("That's not a coin. Try again!");
      return false;
    }
    if (! (howFar > 0)){
      System.out.println("You have to move the coin.");
      return false;
    }
    if(moveFrom-howFar<0){
      System.out.println("The coin has to stay on the board. Try again!");
      return false;
    }
    for( int n=1; n<howFar+1; n++){
      if (! (spaces[moveFrom - howFar] == 0)){
        System.out.println("The coin you move can't cross any other coins. Try again!");
        return false;
      }
    }
    return true;
  }

  //checks whether the game is over
  public Boolean isPlaying(){
    int smallestSpace=numSpaces;
    for (int n=0; n<numSpaces; n++){
      if (spaces[n]==0 & n<smallestSpace){
        smallestSpace = n;
        if (smallestSpace+1>numCoins){
          return false;
        }
      }
    }
    return true;
  }

  //switches the player
  public void switchPlayer(){
    if (player1==true){
      player1=false;
      player = "Player 2: ";
    } else {
      player1=true;
      player = "Player 1: ";
    }

  }

}
