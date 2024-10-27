package PokemonGame.Cards.Trainer;

import PokemonGame.Cards.Card;
import PokemonGame.Components.Player;

/**
 * A Trainer is a Card that can be used similarly to an item in play.
 * A Trainer card labelled as a "supporter" can only be used once per turn.
 */
public abstract class Trainer extends Card
{
    private boolean supporter;
    private String description;

    public boolean isSupporter(){return supporter;}
    public String getDesc(){return description;}
    public void setSupporter(boolean input){supporter = input;}
    public void setDesc(String input){description = input;}

    /**
     * The action the card performs upon use.
     * @param p The player.
     */
    public abstract void use(Player p);
    
    public String toString(){
        return getName() + " - " +  description;
    }
}
