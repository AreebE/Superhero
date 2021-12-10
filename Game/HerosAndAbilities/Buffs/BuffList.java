import java.util.EnumMap;
import java.util.HashMap;

public class BuffList {

  public static enum BuffType{
    ATTACK, DEFENSE, SUPPORT, NONE
  } 

  private BuffList(){

  }

  public static enum BuffNames{
    // Base stat buffs
    ATTACK_BOOST,
    DEFENSE_BOOST,
    CHARGE,

    // health buffs
    REGEN, 
    
    // Damage
    POISON
  };
  // Field buffs

  // Hashmap of buffs
  // private final static HashMap<Integer, Buff> listOfBuffs = new HashMap<>(){{
  //   put(ATTACK_BOOST, new InstantBuff(2, BuffType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
  //   put(DEFENSE_BOOST,  new InstantBuff(2, BuffType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
  //   put(CHARGE, new Buff(1, BuffType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));
  //   // put(1, 3, POISON, new Buff("poison", "The user takes one damage per turn, piercing sheild, over 3 turns"));
    
  // }};

  private final static EnumMap<BuffNames, Buff> listOfBuffs = new EnumMap<>(BuffNames.class){{
    put(BuffNames.ATTACK_BOOST, new InstantBuff(2, BuffType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
    put(BuffNames.DEFENSE_BOOST,  new InstantBuff(2, BuffType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
    put(BuffNames.CHARGE, new Buff(1, BuffType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));

    put(BuffNames.POISON, new DamageDebuff(1, 5, true, "Poison", "Will decrease the health of the player by one.", true, true));
  }};


  /**
   * A method for allowing a buff to be called with a given index
   *
   * @param   index   the index of the buff to be returned
   *
   * @return  the buff that corresponds to the given index
  */
  public static Buff getBuff(BuffNames name){
    return listOfBuffs.get(name);
  }
  
}