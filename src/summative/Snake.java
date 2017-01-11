
package summative;
import java.util.*;

/*****************************************************
James Bonifacio		January 17, 2015

Purpose: 

The purpose of this class is to essentially
keep the game running. It handles the starting of the
program, the movement of the snake, the output of the 
board and the proper ending of the game

Methods:
Snake() - constructor
start() - void
getNext(Dot) - returns Dot
move() - void
checkGameIsOver(Dot) - void
update() - void
******************************************************/

public class Snake {
	
	//assign specific values for each direction
	public static int direction;
	public final static int UP    = 0;
	public final static int DOWN  = 1;
	public final static int LEFT  = 2;
	public final static int RIGHT = 3;
	
	//implement snake as linked list of Dot type.
	private static LinkedList<Dot> snakeBody;
	
	//dot containing the head
	private static Dot head;
	
	//boolean variable for running the game
	private static boolean gameIsOver;
	
	//Snake constructor initializes the snake linked list
	public Snake(){
		snakeBody = new LinkedList<>();
		
		//puts snake in the middle of the map and starts with a length of 3
		
		for(int i = 2; i >= 0; i--){
			head = new Dot(GameBoard.width/2, GameBoard.height/2 - i);
			snakeBody.addFirst(head);
		}
	}
	
	//Starts the game.
		public static void start() throws InterruptedException{	
			String choice;
			Scanner scan = new Scanner(System.in);
			
			//loop for play again option
			do{
	
				//initialize variables and setup game
				int points = 0;
				int time = 500;
				gameIsOver = false;
				GameBoard board = new GameBoard();
				board.initMap();
				
				//loops until game is finished (snake hits the wall)
				while(!gameIsOver){
					update();
					System.out.println("\n\n\n\n\n\n\n\n");
					board.outputGame();
				
					//gets points.
					points = snakeBody.size() - 3;
					
					//Time decreases 50 every 5 points.
					int decreaseTime = (points/5) * 50;
					
					
					Thread.sleep(time - decreaseTime);
				}
				
				System.out.println("Game over");
				System.out.println("You got " + points + " points.");
				
				//asks user to play again.
				do{
					System.out.print("Play again? (y/n):  ");
					choice = scan.next();
				}while(!choice.equals("y") && !choice.equals("n"));	
			
			}while(choice.equals("y"));	
			scan.close();
		}
		
	//moves to the next position.
	public static void move(Dot nextPos) {
		
		//Removes last node
	    Dot tail = snakeBody.removeLast();
        GameBoard.map[tail.row][tail.column] = Dot.EMPTY;

        //Adds to the front
        head = nextPos;
        snakeBody.addFirst(head);
        
        //Sets the snake's body.
        for (Dot index : snakeBody) 
            GameBoard.map[index.row][index.column] = Dot.SNAKE_BODY;   
	}
	
	//gets next Dot in linked list
	public static Dot getNext(Dot currentPos) {
		
		//coordinates of the next position.
		int row = currentPos.row;
		int column = currentPos.column;

		switch(direction){
		case   UP:	column--;
			break;
	    case DOWN:	column++;
	        break;
	    case LEFT:	row--;
	        break;
	    case RIGHT:	row++;
	        break;
	    }      
	    
		Dot nextPos = new Dot(row, column);

	    return nextPos;
	}
	
	
	
	//checks if game will be over
	public static void checkgameIsOver(Dot nextPos){
		
		int next = GameBoard.map[nextPos.row][nextPos.column];
		
		//if snake hits itself
        if (next == Dot.SNAKE_BODY) 
        	gameIsOver = true;
        
        //if snake runs into wall
        if(next == Dot.VERTICAL_WALL || next == Dot.HORIZONTAL_WALL)
        	gameIsOver = true;     
	}
	
	//updates the map
	public static void update() {
		Dot nextPos = getNext(head);
		checkgameIsOver(nextPos); 	
		
		//regular food
		if (GameBoard.map[nextPos.row][nextPos.column] == Dot.NRML_FOOD) {
			
			//increases length
			snakeBody.addFirst(head);	
			GameBoard.generateFood();
		}
		
		//special food
		if (GameBoard.map[nextPos.row][nextPos.column] == Dot.SPCL_FOOD) {
			
			//increases length by 2
			snakeBody.addFirst(head);
			nextPos = getNext(head);
			snakeBody.addFirst(head);
			
			GameBoard.generateFood();
		}
		
		move(nextPos);
	}
	
	
}
