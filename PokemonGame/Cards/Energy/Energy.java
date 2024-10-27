package PokemonGame.Cards.Energy;

import PokemonGame.Cards.Card;

/**
 * An Energy Card is a Card that a Pokemon may hold in order to perform certain moves.
 * There are different types of energy cards with different elements.
 */
public class Energy extends Card
{
    private String element;

    public Energy(String type){
        setName(type);
        setElement(type);
    }
    
    public String getElement(){return element;}
    public void setElement(String input){element = input;}

    public String toString(){
        return "(" + element + ")";
    }
}
