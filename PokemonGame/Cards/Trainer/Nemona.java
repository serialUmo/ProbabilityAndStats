package PokemonGame.Cards.Trainer;

import PokemonGame.Components.Player;

public class Nemona extends Trainer
{
    public Nemona(){
        setName("Nemona");
        setSupporter(true);
        setDesc("Draw 3 cards.");
    }

    public void use(Player p) {
        for(int i = 0; i < 3; i++){
            p.drawCard();
        }
    }
    
}
