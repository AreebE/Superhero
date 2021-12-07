// package Game.ablilites.Buffs;

import java.util.HashMap;

public class BuffList {

  public static enum BuffType{
    ATTACK, DEFENSE, SUPPORT
  } 

  private BuffList(){

  }

  // Stat buffs
  public final static int ATTACK_BOOST =    0000;
  public final static int DEFENSE_BOOST =   0001;
  public final static int CHARGE        =   0002;

  // Health buffs 
  
  
  // Damage buffs
  public final static int POISON        =   1000;
  
  // Field buffs

  // Hashmap of buffs
  private final static HashMap<Integer, Buff> listOfBuffs = new HashMap<>(){{
    put(ATTACK_BOOST, new InstantBuff(2, BuffType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
    put(DEFENSE_BOOST,  new InstantBuff(2, BuffType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
    put(CHARGE, new Buff(1, BuffType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));
    // put(1, 3, POISON, new Buff("poison", "The user takes one damage per turn, piercing sheild, over 3 turns"));
    
  }};


  /**
   * A method for allowing a buff to be called with a given index
   *
   * @param   index   the index of the buff to be returned
   *
   * @return  the buff that corresponds to the given index
  */
  public static Buff getBuff(int index){
    return listOfBuffs.get(index);
  }
  
}