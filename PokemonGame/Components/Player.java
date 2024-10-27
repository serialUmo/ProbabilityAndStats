package PokemonGame.Components;
import java.util.ArrayList;
import java.util.Random;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Energy.*;
import PokemonGame.Cards.Pokemon.*;
import PokemonGame.Cards.Trainer.*;

/**
 * A player is an actor in the Pokemon TCG who uses cards to interface with another player in the game.
 * A player has a hand, a deck, a prize pile, an active slot, a bench, and a discard pile.
 */
public class Player
{
    private String name;

    private Random rng;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizes;
    private ArrayList<Pokemon> bench;
    private ArrayList<Card> discardPile;
    private Pokemon active;
    
    //Constructor
    public Player(String n){
        name = n;
        rng = new Random();
        hand = new ArrayList<>();
        prizes = new ArrayList<>();
        bench = new ArrayList<>();
        discardPile = new ArrayList<>();
    }
    
    //Fill a deck with 60 cards.
    public void fillDeck(){
        deck = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            deck.add(new Energy("Fire"));
        }
        for(int i = 0; i < 30; i++){
            deck.add(new Charmander());
        }
    }
    
    //Draw 7 cards into hand.
    public void drawHand(){
        for (int i = 0; i < 7; i++){
            drawCard();
        }
    }
    
    //Draws 1 card into hand. Returns false if no card can be drawn.
    public boolean drawCard(){
        if(deck.size() == 0){
            return false;
        }

        int drawIndex = rng.nextInt(deck.size());
        hand.add(deck.remove(drawIndex));
        return true;
    }
    
    //Discards entire hand into deck.
    public void handToDeck(){
        while (!hand.isEmpty()){
            deck.add(hand.remove(0));
        }
    }

    //Takes card from hand and places as active.
    public void handToActive(int index){
        active = (Pokemon) hand.remove(index);
    }

    //Benches a card.
    public void handToBench(int index){
        bench.add((Pokemon) hand.remove(index));
    }

    //Discards a card from hand.
    public void discardHandCard(int index){
        discardPile.add(hand.remove(index));
    }

    //Places a card into the discard pile.
    public void discard(Card c){
        discardPile.add(c);
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
            deck.add(new Energy(""));
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
    public ArrayList<Card> getPrizes(){return prizes;}
    public ArrayList<Pokemon> getBench(){return bench;}
    public Pokemon getActive(){return active;}

    //Setters
    public void setActive(Pokemon input){
        active = input;
    }

    //Printers
    public void printHand(){
        for(int i = 0; i < hand.size(); i++){
            System.out.println(i + ": " + hand.get(i));
        }
    }
    public void printDeck(){
        for(int i = 0; i < deck.size(); i++){
            System.out.println(i + ": " + deck.get(i));
        }
    }
    public void printBench(){
        for(int i = 0; i < bench.size(); i++){
            System.out.println(i + ": " + bench.get(i));
        }
        for(int i = bench.size(); i < 5; i++){
            System.out.println(i + ": Empty");
        }
    }

    public String toString(){
        return name;
    }
    
}
