package PokemonGame.Cards.Trainer;

import PokemonGame.Components.Player;

public class RareCandy extends Trainer
{
    public RareCandy(){
        setName("Rare Candy");
        setSupporter(false);
    }

    public void use(Player p) {
        System.out.println("You stare at the candy like an idiot.");
    }
}

