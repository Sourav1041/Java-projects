import java.util.*;

public class Game {
	static char [][] board =new char [10][10];
	static int [][] records = new int[10][10];
	static boolean status = true;
	static int hidden= board.length * board.length;
	static int mineDetected =0;
	static int mine;
	static {
		for(int i=0; i<records.length; i++) {
			for (int j=0; j<records.length; j++) {
				records [i][j]=1;
			}
		}
	}
	public static void GenrateMine(int count) {
		mine = count;
		for(int i=0; i<count; i++) {
			int a= (int) ( board.length *Math.random());	
			int b= (int) ( board.length *Math.random());	
			if(board[a][b]== 'X') {
				i--;
			}
			else {
				board[a][b]= 'X';
			}
			
		}
		
	}
	public static void GenrateHints() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				int countMine=0;
				if(board[i][j]!='X') { 
				if(i==0) {
					if(j==0) {
						if(board[1][0] =='X') countMine++;
						if(board[1][1]=='X' ) countMine++;
						if(board[0][1]=='X' ) countMine++;
					}
					else if(j== board.length -1) {
						
						if(board[0][j-1] =='X') countMine++;
						if(board[1][j]=='X' ) countMine++;
						if(board[1][j-1]=='X' ) countMine++;
					}
					else {
						if(board[i][j-1] =='X') countMine++;
						if(board[i][j+1]=='X' ) countMine++;
						if(board[i+1][j-1]=='X' ) countMine++;
						if(board[i+1][j+1]=='X' ) countMine++;
						if(board[i+1][j]=='X' ) countMine++;
						}
						
					}
				else if(j==0) {
					if(i==board.length-1) {
						if(board[i-1][0] =='X') countMine++;
						if(board[i-1][1]=='X' ) countMine++;
						if(board[i][j+1]=='X' ) countMine++;
					}
					else {
						if(board[i-1][j] =='X') countMine++;
						if(board[i-1][j+1]=='X' ) countMine++;
						if(board[i][j+1]=='X' ) countMine++;
						if(board[i+1][j]=='X' ) countMine++;
						if(board[i+1][j+1]=='X' ) countMine++;
						}
					}
				else if(i== board.length-1) {
					if(j== board.length-1) {
						if(board[i][j-1]=='X' ) countMine++;
						if(board[i-1][j-1]=='X' ) countMine++;
						if(board[i-1][j]=='X' ) countMine++;
					
					}
					else {
						if(board[i-1][j] =='X') countMine++;
						if(board[i-1][j-1]=='X' ) countMine++;
						if(board[i-1][j+1]=='X' ) countMine++;
						if(board[i][j-1]=='X' ) countMine++;
						if(board[i][j+1]=='X' ) countMine++;
						}
					}
				else if(j== board.length-1) {
					if(board[i-1][j-1] =='X') countMine++;
					if(board[i+1][j-1]=='X' ) countMine++;
					if(board[i][j-1]=='X' ) countMine++;
					if(board[i-1][j]=='X' ) countMine++;
					if(board[i+1][j]=='X' ) countMine++;
					}
				else {
					if(board[i-1][j]=='X' ) countMine++;
					if(board[i-1][j+1]=='X' ) countMine++;
					if(board[i-1][j-1]=='X' ) countMine++;
					if(board[i][j+1]=='X' ) countMine++;
					if(board[i][j-1]=='X' ) countMine++;
					if(board[i+1][j]=='X' ) countMine++;
					if(board[i+1][j+1]=='X' ) countMine++;
					if(board[i+1][j-1]=='X' ) countMine++;
					}
				board[i][j]= (char) (48+countMine);
				}
			}
		}
	}
	public static void display() {
		System.out.print("  ");
		for(int i=0; i<board.length; i++){
			System.out.print( i + " ");
		}
		System.out.println();
		System.out.print("  ");
		for(int i=0; i<board.length; i++){
			System.out.print( "--");
		}
		System.out.println();
		for(int i=0; i<board.length;i++) {
			System.out.print(i+ "|");
			for(int j=0; j< board.length; j++) {
				if(records[i][j]==2) System.out.print(board[i][j] +"|");
				else if(records[i][j]==3) System.out.print("M"+"|");
				else System.out.print(" " +"|");
			}
			System.out.println();
			System.out.print("  ");
			for(int k=0;k<board.length; k++) {
				System.out.print("--");
			}
			System.out.println();
		}
		
	}
	public static void guessing(){
		System.out.println("do you want to guess or remove previously guessed posn of mine ");
		System.out.println("for guessing type y \n for removing your guess type r \n else type n");
		Scanner sc = new Scanner(System.in);
		char guess= sc.next().charAt(0);
		while( guess != 'y' && guess != 'n' && guess != 'r'){
			System.out.println("Invalid responce");
			System.out.println("try Again ");
			System.out.println("for guessing type y \n for removing your guess type r \n else type n");	
			guess= sc.next().charAt(0);
			
		}
		while(guess=='y' || guess=='r'){
			System.out.println("Enter the coordinates in which you want to guess");
			int a=sc.nextInt();
			int b= sc.nextInt();
			if( records[a][b] ==2){
				System.out.println("Alredy opened");
				System.out.println("try again");
				a=sc.nextInt();
				b= sc.nextInt();
			}
			
			if( a>=0 && a< board.length &&b>=0 && b< board.length && records[a][b] ==1 && guess=='y'){
				records[a][b] = 3;
				mineDetected++;
			}
			if (a>=0 && a< board.length &&b>=0 && b< board.length && records[a][b] ==3 && guess=='r') {
				records[a][b]= 1;
				mineDetected--;
			}
			display();
			if(mineDetected== mine){
				System.out.println("do you want to submit");
				char g= sc.next().charAt(0);
				if(g=='y'){
					submit();
				}
			}
			System.out.println("do you want to guess more or remove your guess");
			System.out.println("for guessing type y \n for removing your guess type r \n else type n");
			guess= sc.next().charAt(0);
			while( guess != 'y' && guess != 'n' && guess != 'r'){
			System.out.println("Invalid responce");
			System.out.println("try Again ");
			System.out.println("for guessing type y \n for removing your guess type r \n else type n");	
			guess= sc.next().charAt(0);
			
		}
		}
	}
	public static void moves() {
		System.out.println(" Enter the coordinates for which you want to check for mines");
		Scanner sc= new Scanner (System.in);
		int a= sc.nextInt();
		int b= sc.nextInt();
		if(board[a][b]=='X') {
			GameOver();
			return;
		}
		
		CheckZero(a,b);
		display();
		guessing();

	}

	
	public static void CheckZero(int a, int b) {
	if(a>=0 && b>=0 && a< board.length && b<board.length && records[a][b] ==1) {
			records[a][b]=2;
			if(board[a][b] =='0') {
				CheckZero(a-1,b+1);
				CheckZero(a-1,b-1);
				CheckZero(a-1,b);
				CheckZero(a,b+1);
				CheckZero(a,b-1);
				CheckZero(a+1,b+1);
				CheckZero(a+1,b-1);
				CheckZero(a+1,b);
			}	
		}
	}

	public static void running(){
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter mine count");
		GenrateMine(sc.nextInt());
		GenrateHints();
		display();
		while (status) {
			moves();
			if(mineDetected== mine){
				System.out.println("do you want to submit");
				char g= sc.next().charAt(0);
				if(g=='y'){
					submit();
				}
			}
		}	
	}
	public static void submit(){
		System.out.println("do you want to end your round");
		status= false;
		int finalc =0;
		for(int i=0; i<board.length+1; i++){
			System.out.print("--");
		}
		System.out.println();
		for(int i=0; i< board.length; i++){	
			System.out.print('|');
			for(int j=0; j< board.length; j++){
				if(board[i][j] =='X' && records[i][j] ==3 ) finalc++;
				System.out.print(board[i][j] +"|");
			}
			System.out.println();
			for(int k=0; k<board.length+1; k++){
				System.out.print("--");
			}
			System.out.println();
				
		}
		System.out.println("Thankyou for playing");
		System.out.println("you scored " + finalc);
		}
	public static void GameOver() {
		status= false;
		System.out.println("you found a mine GameOver");
		System.out.println();
		System.out.println();
		for(int i=0; i<board.length+1; i++) {
			System.out.print("--");
		}
		System.out.println();
		for(int i=0; i<board.length;i++) {
			System.out.print("|");
			for(int j=0; j< board.length; j++) {
				System.out.print(board[i][j] +"|");
			}
			System.out.println();
			for(int k=0; k<board.length +1; k++ ) {
				System.out.print("--");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		running();
	}

}
