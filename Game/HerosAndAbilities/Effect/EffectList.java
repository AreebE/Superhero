import java.util.EnumMap;
import java.util.HashMap;

public class EffectList {

  public static enum EffectType{
    ATTACK, DEFENSE, HEALTH, DAMAGE
  } 

  private EffectList(){

  }

  public static enum EffectNames{
    // Base stat Effects
    ATTACK_BOOST,
    DEFENSE_BOOST,
    CHARGE,
    BUILD_UP,
    GUARD,

    // health Effects
    REGEN, 
    
    // Damage
    POISON,
    CURSE
  };
  // Field Effects

  // Hashmap of Effects
  // private final static HashMap<Integer, Effect> listOfEffects = new HashMap<>(){{
  //   put(ATTACK_BOOST, new InstantEffect(2, EffectType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
  //   put(DEFENSE_BOOST,  new InstantEffect(2, EffectType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
  //   put(CHARGE, new Effect(1, EffectType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));
  //   // put(1, 3, POISON, new Effect("poison", "The user takes one damage per turn, piercing sheild, over 3 turns"));
    
  // }};

  private final static EnumMap<EffectNames, Effect> listOfEffects = new EnumMap<>(EffectNames.class){{
    put(EffectNames.ATTACK_BOOST, new InstantEffect(2, EffectType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
    put(EffectNames.DEFENSE_BOOST,  new InstantEffect(2, EffectType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
    put(EffectNames.CHARGE, new Effect(1, EffectType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));
    put(EffectNames.REGEN, new Effect(1, EffectType.HEALTH, 10, true, "Regen", "The player will recover health for ten turns, 1 health per turn. "));
    put(EffectNames.POISON, new Effect(1, EffectType.DAMAGE, 5, true, "Poison", "Will decrease the health of the player by one.", true, true));

    put(EffectNames.GUARD, new OneTimeEffect(4, EffectType.DEFENSE, 5, "Guard", "Will let the user adopt a defensive stance"));

    put(EffectNames.CURSE, new DelayedEffect(20, EffectType.DAMAGE, 4, "Curse", "Deals a hefty amount of damage after some time has passed", false, false));
  }};


  /**
   * A method for allowing a Effect to be called with a given index
   *
   * @param   index   the index of the Effect to be returned
   *
   * @return  the Effect that corresponds to the given index
  */
  public static Effect getEffect(EffectNames name){
    return listOfEffects.get(name);
  }
  
}