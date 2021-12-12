import java.util.EnumMap;
import java.util.HashMap;

public class EffectList {

  public static enum EffectType{
    ATTACK, DEFENSE, HEALTH, DAMAGE, GROUP
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
    PERMAGEN,

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
    put(EffectNames.ATTACK_BOOST, new InstantEffect(2, EffectType.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass", ElementList.getElement(ElementList.ElementNames.NULL)));
    put(EffectNames.DEFENSE_BOOST,  new InstantEffect(2, EffectType.DEFENSE, "defense up", "Reduces all future damage the character takes by 2", ElementList.getElement(ElementList.ElementNames.NULL)));
    put(EffectNames.CHARGE, new Effect(1, EffectType.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn", ElementList.getElement(ElementList.ElementNames.NULL)));
    put(EffectNames.REGEN, new Effect(1, EffectType.HEALTH, 5, true, "Regen", "The player will recover health for ten turns, 1 health per turn. ", ElementList.getElement(ElementList.ElementNames.NULL)
    ));
    put(EffectNames.POISON, new Effect(1, EffectType.DAMAGE, 5, true, "Poison", "Will decrease the health of the player by one.", ElementList.getElement(ElementList.ElementNames.NULL), true, true));

    put(EffectNames.GUARD, new OneTimeEffect(4, EffectType.DEFENSE, 5, "Guard", "Will let the user adopt a defensive stance", ElementList.getElement(ElementList.ElementNames.NULL)));

    put(EffectNames.CURSE, new DelayedEffect(20, EffectType.DAMAGE, 4, "Curse", "Deals a hefty amount of damage after some time has passed", ElementList.getElement(ElementList.ElementNames.NULL),  false, false));

    put(EffectNames.PERMAGEN, new Passive(1, EffectType.HEALTH, "Permanent regeneration", "Just as the name says.", ElementList.getElement(ElementList.ElementNames.NULL), true, true));

    put(EffectNames.BUILD_UP, new GroupEffect("Build up", "The target begins to increase all of their stats.", ElementList.getElement(ElementList.ElementNames.NULL), this.get(EffectNames.CHARGE), this.get(EffectNames.DEFENSE_BOOST), this.get(EffectNames.REGEN)));
    // template
    // put(EffectNames., new __Effect(1, EffectType., 1, "", "", ElementList.getElement(ElementList.ElementNames.), true, true));
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