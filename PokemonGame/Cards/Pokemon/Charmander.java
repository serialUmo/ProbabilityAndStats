package PokemonGame.Cards.Pokemon;

import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Components.Player;

public class Charmander extends Pokemon
{
    public Charmander(){
        setName("Charmander");
        setHp(70);
        setMove1Desc("Scratch (N) - 10 DMG");
        setMove2Desc("Ember (F)(N) - Discard 1 (F) Energy Card attached to Charmander in order to use this attack. - 30 DMG");
    }

    public boolean move1(Player p, Player opp) {
        if (getEnergies().size() != 0){
            opp.getActive().subtractHp(10);
            return true;
        }

        System.out.println("Not enough energy!");
        return false;
    }

    public boolean move2(Player p, Player opp) {
        int f = 0;
        int n = 0;

        for(Energy e : getEnergies()){
            n++;
            if (e.getElement().equals("Fire")){
                f++;
            }
        }
        if(n > 1 && f > 0){
            opp.getActive().subtractHp(30);
            for(int i = 0; i < getEnergies().size(); i++){
                if (getEnergies().get(i).getElement().equals("Fire")){
                    p.discard(getEnergies().remove(i));
                    break;
                }
            }
            return true;
        }
        else{
            System.out.println("Not enough energy!");
            return false;
        }
    }
}
