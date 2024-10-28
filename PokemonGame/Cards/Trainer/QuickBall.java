package PokemonGame.Cards.Trainer;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Pokemon.Pokemon;
import PokemonGame.Components.Player;

public class  QuickBall extends Trainer
{
    public QuickBall(){
        setName("Quick Ball");
        setDesc("Discard a card from your hand. Search your deck for a Pokemon and put it in your hand.");
        setSupporter(false);
    }

    public void use(Player p) {
        if(p.getHand().size() < 2){
            System.out.println("You can't discard anything from your hand.\nThis card s till gets used up though. Maybe don't be stupid, kid.");
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

        System.out.println("What card do you want to throw away? (Enter #)");
        p.printHand();
        int choice = scan().nextInt();
        p.discard(p.getHand().remove(choice));

        System.out.println("What Pokemon do you want to draw? (Enter #)");
        p.printDeck();
        choice = scan().nextInt();
        while(!(p.getDeck().get(choice) instanceof Pokemon)){
            System.out.println("That's not a Pokemon.");
            choice = scan().nextInt();
        }
        p.getHand().add(p.getDeck().remove(choice));
    }   
}
