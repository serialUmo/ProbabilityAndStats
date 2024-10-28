package PokemonGame.Cards.Trainer;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Pokemon.Pokemon;
import PokemonGame.Components.Player;

public class NestBall extends Trainer
{
    public NestBall(){
        setName("Nest Ball");
        setDesc("Search your deck for a Pokemon and put it in your bench.");
        setSupporter(false);
    }

    public void use(Player p) {
        if(p.getBench().size() == 5){
            System.out.println("Your bench is full! This card is still used up, though. Tough luck!");
            return;
        }
        
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

        System.out.println("What Pokemon do you want to bench? (Enter #)");
        p.printDeck();
        int choice = scan().nextInt();
        while(!(p.getDeck().get(choice) instanceof Pokemon)){
            System.out.println("That's not a Pokemon.");
            choice = scan().nextInt();
        }
        p.getBench().add((Pokemon) p.getDeck().remove(choice));
    }   
}
