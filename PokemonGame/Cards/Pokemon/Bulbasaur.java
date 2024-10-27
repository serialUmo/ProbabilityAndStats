package PokemonGame.Cards.Pokemon;

import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Components.Player;

public class Bulbasaur extends Pokemon
{
    public Bulbasaur(){
        setName("Bulbasaur");
        setHp(70);
        setMove1Desc("Tackle (G) - 10 DMG");
        setMove2Desc("Bullet Seed (G)(N) - Flip 4 coins. This deals 10x the number of heads.");
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
        int g = 0;
        int n = 0;

        for(Energy e : getEnergies()){
            n++;
            if (e.getElement().equals("Grass")){
                g++;
            }
        }
        if(n > 1 && g > 0){
            int heads = 0;
            for(int i = 0; i < 4; i++){
                if (getRNG().nextBoolean()){
                    heads++;
                }
            }
            System.out.println("Flipped " + heads + "/4 heads!");
            opp.getActive().subtractHp(10 * heads);
            return true;
        }
        else{
            System.out.println("Not enough energy!");
            return false;
        }
    }
    
}
