package summative;

/*****************************************************
James Bonifacio		January 17, 2015

Purpose: 

The purpose of this class is to hold the snake's locations
and the type of dots. This class works in conjunction with
the Snake and GameBoard class in order to create a proper
linkedList implementation

Methods:
Dot(int,int) - constructor
******************************************************/

public class Dot {

	//assign constants for each type of dot
	final static int NRML_FOOD		    = 1;
	final static int SPCL_FOOD		    = 2;
	final static int SNAKE_BODY 		= 3;
	final static int VERTICAL_WALL		= 4;
	final static int HORIZONTAL_WALL	= 5;
	final static int EMPTY				= 6;
	
	//variables holding the snakes coordinates
	int row, column;
	
	//constructor for dot object
	public Dot(int row, int column){
		
		//assigns the coordinates
		this.row = row;
		this.column = column;
	}
}
