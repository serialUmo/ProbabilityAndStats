package PokemonGame.Cards.Trainer;

import PokemonGame.Components.Player;

public class ProfResearch extends Trainer
{
    public ProfResearch(){
        setName("Professor's Research");
        setSupporter(true);
        setDesc("Discard your hand and draw 7 cards.");
    }

    public void use(Player p) {
        while(p.getHand().size() != 0){
            p.discardHandCard(0);
        }
        p.drawHand();
    }
    
}
