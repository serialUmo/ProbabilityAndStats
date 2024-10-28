package PokemonGame.Cards.Trainer;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Pokemon.Pokemon;
import PokemonGame.Components.Player;

public class PokeBall extends Trainer
{
    public PokeBall(){
        setName("Poke Ball");
        setDesc("Flip a coin. If heads, search your deck for a Pokemon and put it in your hand.");
        setSupporter(false);
    }

    public void use(Player p) {
        if(flipHeads()){
            checkPokemon:
            while(true){
                for(Card c : p.getDeck()){
                    if(c instanceof Pokemon){
                        break checkPokemon;
                    }
                }
            System.out.println("You don't have any Pokemon in your deck!");
            return;
            }

            System.out.println("Heads! What Pokemon do you want to draw? (Enter #)");
            p.printDeck();
            int choice = scan().nextInt();
            while(!(p.getDeck().get(choice) instanceof Pokemon)){
                System.out.println("That's not a Pokemon.");
                choice = scan().nextInt();
            }
            p.getHand().add(p.getDeck().remove(choice));
        }
        else{
            System.out.println("Tails.");
            return;
        }
    }   
}
