package summative;
import java.util.Random;

/*****************************************************
James Bonifacio		January 17, 2015

Purpose: 

This class handles everything to do with the actual
game board. It serves to initialize and display the 
game along with the generation of food with the method
generateFood().

Methods:
GameBoard() - constructor
generateFood() - void
createWalls() - void
initMap() - void
getValue(int) - returns char
outputGame() - void
******************************************************/

public class GameBoard {
	
	
	
	//Board's dimensions.
	public static int width = 20;
	public static int height = 20;
	
	public static int[][] map;
	
	//Initializes the board as empty type.
	public GameBoard(){
		map = new int[width][height];
		for (int row = 0; row < width; row++) 
			 for (int column = 0; column < height; column++) 
				 map[row][column] = Dot.EMPTY;
	}
	
	//generate food method
	public static void generateFood() {
		Random rand = new Random();


		//variables holding location of food
		int row = 0;
		int column = 0;
		
		do{
			row = rand.nextInt(width - 2) + 1;
	        column = rand.nextInt(height - 2) + 1;
		} while (map[row][column] != Dot.EMPTY);

		//More chance to get regular food than special food.
		switch(rand.nextInt(3) + 1){
		case 1: map[row][column]= Dot.NRML_FOOD;		//regular.
			break;
		case 2: map[row][column]= Dot.NRML_FOOD;		//regular.
			break;
		case 3: map[row][column]= Dot.SPCL_FOOD;		//special.
			break;
		}
    }

	//Sets the frame of the board.
	public void createWalls(){
		
		//Top and bottom walls.
		for (int x = 0; x < width; x++) {
			map[x][0] = Dot.HORIZONTAL_WALL;
			map[x][height-1] = Dot.HORIZONTAL_WALL;
		}
		//Left and right walls.
	    for (int y = 0; y < height; y++) {
	        map[0][y] = Dot.VERTICAL_WALL;
	        map[width - 1][y] = Dot.VERTICAL_WALL;
	    }
	}
	
	//Initializes the board.
	public void initMap(){
		createWalls();
		generateFood();	
		new Snake();    
	}
	
	//Gets the map values.
	public static char getValue(int type){
		switch(type){
		case Dot.NRML_FOOD: 		return  'x';	
		case Dot.SPCL_FOOD: 		return  'X';	
		case Dot.EMPTY: 			return  ' ';
		case Dot.HORIZONTAL_WALL: 	return  '=';	
		case Dot.VERTICAL_WALL: 	return  '|';	
		case Dot.SNAKE_BODY: 		return  'O';
		default: return ' ';
		}
	}
	
	//Displays Snake Game.
	public void outputGame(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++)
				System.out.print(getValue(map[x][y]));
			System.out.println();
		}
	}
}