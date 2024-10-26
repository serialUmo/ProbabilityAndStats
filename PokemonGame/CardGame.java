package PokemonGame;

import java.util.Random;

public class CardGame
{
    private Random rng;
    private Player player;
    private Player opponent;
    
    public CardGame(){
        rng = new Random();
        player = new Player();
        opponent = new Player();
    }
    
    //Runs a game.
    /*
    public void run(){
        fillDeck();
        drawHand();
        handHasPokemon();
        //etc
        
        return;
    }
    */
    
}