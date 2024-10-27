package PokemonGame.Cards;

/**
 * A card is an object with a name that is able to perform some action depending on its card type.
 * It is used by the Player to interface with the game.
 */
public class Card
{
    private String name;
    
    public String getName(){return name;}
    public void setName(String input){name = input;}

    public String toString(){
        return name;
    }
}

