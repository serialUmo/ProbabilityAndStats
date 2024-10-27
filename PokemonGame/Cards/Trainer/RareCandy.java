package PokemonGame.Cards.Trainer;

import PokemonGame.Components.Player;

public class RareCandy extends Trainer
{
    public RareCandy(){
        setName("Rare Candy");
        setSupporter(false);
        setDesc("Does nothing.");
    }

    public void use(Player p) {
        System.out.println("You stare at the candy like an idiot.");
    }
}

