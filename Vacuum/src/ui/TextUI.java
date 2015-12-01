package ui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Scanner;

import game.VacuumGame;

public class TextUI implements UI {
	private VacuumGame game;
	
	
	
	
	public TextUI(VacuumGame game) {
		this.game = game;
		
	}
	

	@Override
	public void launchGame(){

        Scanner sc = new Scanner(System.in);
        while (!(game.gameOver())){
        	System.out.print(game.getGrid().toString());
        	String moveIn = sc.nextLine();
        	for (int i = 0; i < moveIn.length(); i++){
        		char nextMove = moveIn.charAt(i);
        		this.game.move(nextMove);
        	}
		}
		sc.close();
		}					
		
	
	@Override
	public void displayWinner() {

	    if (!this.game.gameOver()) {
	        return;
	    }
	    
	    int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + ".";
		}
		System.out.println(message);
	}
}

