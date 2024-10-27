package PokemonGame.Cards.Pokemon;

import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Components.Player;

public class Pikachu extends Pokemon 
{
    public Pikachu(){
        setName("Pikachu");
        setHp(60);
        setMove1Desc("Gnaw (E) - 10 DMG");
        setMove2Desc("Thunder Jolt (E)(N) - Flip a coin. Take 10 self-damage if it lands on tails. - 30 DMG");
    }

    public boolean move1(Player p, Player opp) {
        for(Energy e : getEnergies()){
            if (e.getElement().equals("Grass")){
                opp.getActive().subtractHp(10);
                return true;
            }
        }
        System.out.println("Not enough energy!");
        return false;
    }

    public boolean move2(Player p, Player opp) {
        int elec = 0;
        int n = 0;

        for(Energy e : getEnergies()){
            n++;
            if (e.getElement().equals("Electric")){
                elec++;
            }
        }
        if(n > 1 && elec > 0){
            if(getRNG().nextBoolean()){
                System.out.println("TAILS!");
                p.getActive().subtractHp(10);
            }
            opp.getActive().subtractHp(30);
            return true;
        }
        else{
            System.out.println("Not enough energy!");
            return false;
        }
    }
}
