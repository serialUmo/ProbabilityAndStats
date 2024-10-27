package PokemonGame.Cards.Energy;

import PokemonGame.Cards.Card;

public class Energy extends Card
{
    private String element;

    public Energy(String type){
        setName(type);
        setElement(type);
    }
    
    public String getElement(){return element;}
    public void setElement(String input){element = input;}
}
