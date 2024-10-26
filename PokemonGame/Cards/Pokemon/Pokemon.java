package PokemonGame.Cards.Pokemon;

import PokemonGame.Cards.Card;

public class Pokemon extends Card
{
    private int hp;
    
    public int getHp(){return hp;}
    public void setHp(int input){hp = input;}
    public void subtractHp(int dmg){hp -= dmg;}
    
}

