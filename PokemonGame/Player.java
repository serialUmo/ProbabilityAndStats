package PokemonGame;
import java.util.ArrayList;
import java.util.Random;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Energy.*;
import PokemonGame.Cards.Pokemon.*;
import PokemonGame.Cards.Trainer.*;

public class Player
{
    private Random rng;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizes;
    
    public Player(){
        rng = new Random();
        hand = new ArrayList<>();
        prizes = new ArrayList<>();
    }
    
    //Fill a deck with 60 cards.
    public void fillDeck(){
        deck = new ArrayList<>();
        for(int i = 0; i < 60; i++){
            deck.add(new Energy());
        }
    }
    
    //Draw 7 cards into hand.
    public void drawHand(){
        for (int i = 0; i < 7; i++){
            draw();
        }
    }
    
    //Draws 1 card into hand.
    public void draw(){
        int drawIndex = rng.nextInt(deck.size());
        hand.add(deck.remove(drawIndex));
    }
    
    //Discards entire hand into deck.
    public void discardHand(){
        while (!hand.isEmpty()){
            deck.add(hand.remove(0));
        }
    }
    
    //Checks if hand contains a Pokemon.
    public boolean handHasPokemon(){
        for (Card card : hand){
            if (card instanceof Pokemon)
            {
                return true;
            }
        }
        
        return false;
    }
    
    //Places 6 cards into prize pile.
    public void placePrizes(){
        for (int i = 0; i < 6; i++){
            int drawIndex = rng.nextInt(deck.size());
            prizes.add(deck.remove(drawIndex));
        }
    }
    
    //Take 1 prize into hand.
    public void takePrize(){
        hand.add(prizes.remove(0));
    }
    
    //Reset deck.
    public void resetDeck(){
        deck = new ArrayList<>();
    }
    
    //Reset hand.
    public void resetHand(){
        hand = new ArrayList<>();
    }
    //Reset prize pile.
    public void resetPrizePile(){
        prizes = new ArrayList<>();
    }
    
    //Monte Carlo
    //Fills a deck with a number of pokemon.
    public void validHandDeck(int pokemon){
        deck = new ArrayList<>();
        for(int i = 0; i < pokemon; i++){
            deck.add(new Charmander());
        }
        for(int i = pokemon; i < 60; i++){
            deck.add(new Energy());
        }
    }
    
    //Fills a deck with a number of candy.
    public void rareCandyDeck(int candies){
        deck = new ArrayList<>();
        for(int i = 0; i < candies; i++){
            deck.add(new RareCandy());
        }
        for(int i = candies; i < 60; i++){
            deck.add(new Charmander());
        }
    }
    
    //Getters
    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getDeck(){return deck;}
    
}
