package PokemonGame.Cards.Pokemon;

import java.util.ArrayList;
import java.util.Random;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Components.Player;

/**
 * A Pokemon is a Card the Player uses to fight other Pokemon.
 * Pokemon have actions to perform that do a variety of things.
 * In the real TCG, pokemon may have 1 to 4 moves. For sake of simplicity,
 * pokemon will always have at most 2.
 * Pokemon may hold energy cards needed to perform certain moves.
 */
public abstract class Pokemon extends Card
{
    private Random rng;

    private int hp;
    private String move1Desc;
    private String move2Desc;
    private ArrayList<Energy> energies;

    public Pokemon(){
        rng = new Random();
        energies = new ArrayList<>();
    }

    /**
     * One of two moves a Pokemon can perform.
     * @param p The player.
     * @param opp The opponent.
     * @return If the move can be and was performed.
     */
    public abstract boolean move1(Player p, Player opp);

    /**
     * One of two moves a Pokemon can perform.
     * @param p The player.
     * @param opp The opponent.
     * @return If the move can be and was performed.
     */
    public abstract boolean move2(Player p, Player opp);

    public int getHp(){return hp;}
    public void setHp(int input){hp = input;}
    public void subtractHp(int dmg){
        System.out.println(getName() + " took " + dmg + " damage!");
        hp -= dmg;
    }

    public String getMove1Desc(){return move1Desc;}
    public String getMove2Desc(){return move2Desc;}
    public void setMove1Desc(String input){move1Desc = input;}
    public void setMove2Desc(String input){move2Desc = input;}

    public ArrayList<Energy> getEnergies(){return energies;}

    public Random getRNG(){return rng;}
    
    public String toString(){
        return getName() + " (HP: " + hp + ")";
    }
}

