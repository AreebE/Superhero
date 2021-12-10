
import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

public class AbilityList {
  // Prevents any instances of this class from being made

  //private ArrayList<Element> elements;

  private AbilityList(){}

  public static enum AbilityType{
    ATTACK, DEFENSE, SUPPORT
  }

  public static enum AbilityNames {
    // Attack
    FIREBALL, 
    SNOWBALL, 
    LIGHTNING_STRIKE, 
    PASS_TURN,

    // Defense 
    HEAL_PULSE,
    PROTECT,
    COUNTERSTRIKE,

    // Support
    ATTACK_UP,
    DEFENSE_UP,
    FLARE_UP,
    POISON
  }

  private final static EnumMap<AbilityNames, Ability> LIST_OF_ABILITIES = new EnumMap<>(AbilityNames.class){{
    put(AbilityNames.FIREBALL, new AttackAbility("Fireball", "Launches a ball of fire", 1, 3, AbilityNames.FIREBALL, ElementList.getElement(ElementList.ElementNames.FIRE), false, false));
    put(AbilityNames.SNOWBALL, new AttackAbility("Snowball", "Launches a few snowballs. May cause frost or blindness.", 1, 3, AbilityNames.SNOWBALL, ElementList.getElement(ElementList.ElementNames.ICE), false, true));
    put(AbilityNames.LIGHTNING_STRIKE, new AttackAbility("Lightning Strike", "Has a chance to shock the enemy.", 1, 3, AbilityNames.LIGHTNING_STRIKE, ElementList.getElement(ElementList.ElementNames.ELECTRICITY), true, false));
    put(AbilityNames.PASS_TURN, new AttackAbility("Pass turn", "Allows the user to skip their turn", 0, 0, AbilityNames.PASS_TURN, ElementList.getElement(ElementList.ElementNames.NULL), false, false));

    // Defense abilities
    put(AbilityNames.HEAL_PULSE, new HealAbility("Heal pulse", "heals oneself", 1, 10, AbilityNames.HEAL_PULSE, ElementList.getElement(ElementList.ElementNames.NULL)));

    put(AbilityNames.PROTECT, new DefenseAbility("Protect", "protects the user from any hit.", 3, 5, AbilityNames.PROTECT, ElementList.getElement(ElementList.ElementNames.NULL)));
    
    put(AbilityNames.COUNTERSTRIKE, new DefenseAbility("Counterstrike", "Will counter any attack that comes forth", 5, 50, AbilityNames.COUNTERSTRIKE, ElementList.getElement(ElementList.ElementNames.NULL)));

    // Support abilities
    // System.out.println(listOfBuffs);
    put(AbilityNames.ATTACK_UP, new SupportAbility("attack up", "boosts damage for x amount of time", 5, BuffList.getBuff(BuffList.BuffNames.ATTACK_BOOST), AbilityNames.ATTACK_UP, ElementList.getElement(ElementList.ElementNames.NULL
    )));
    
    put(AbilityNames.DEFENSE_UP, new SupportAbility("defense up", "boosts defense for x amount of time", 5, BuffList.getBuff(BuffList.BuffNames.DEFENSE_BOOST), AbilityNames.DEFENSE_UP, ElementList.getElement(ElementList.ElementNames.NULL)));
    
    put(AbilityNames.FLARE_UP, new SupportAbility("flare up", "Makes the user charge up their attacks", 10, BuffList.getBuff(BuffList.BuffNames.CHARGE), AbilityNames.FLARE_UP, ElementList.getElement(ElementList.ElementNames.NULL)));

    put(AbilityNames.POISON, new SupportAbility("Poison", "Will poison the person targeted", 5, BuffList.getBuff(BuffList.BuffNames.POISON), AbilityNames.POISON, ElementList.getElement(ElementList.ElementNames.NULL)));
  }};


  // Getting an ability's id, based on a name given
  private final static HashMap<String, AbilityNames> namesToAbility = new HashMap<>(){{
    put("fireball", AbilityNames.FIREBALL);

    put("snowball", AbilityNames.SNOWBALL);

    put("lightning", AbilityNames.LIGHTNING_STRIKE);

    put("protect", AbilityNames.PROTECT);

    put("counterstrike", AbilityNames.COUNTERSTRIKE);

    put("attack_up", AbilityNames.ATTACK_UP);

    put("defense_up", AbilityNames.DEFENSE_UP);

    put("pass", AbilityNames.PASS_TURN);

    put("flare_up", AbilityNames.FLARE_UP);

    put("heal", AbilityNames.HEAL_PULSE);

    put("poison", AbilityNames.POISON);
  }};

  // returns an ability's id
  public static AbilityNames getName(String name){
    // System.out.println(name)
    // System.out.println(name + namesToAbility);
    AbilityNames enumName = namesToAbility.get(
      name.toLowerCase());
    return enumName;
  }

  /**
  */
  // public static void giveAbility(Superhero target, int ... ids){
  //   for (int i: ids){
  //     target.addAbility(LIST_OF_ABILITIES_2.get(i).copyAbility());
  //   }
  // }

  public static void giveAbility(Superhero target, AbilityNames ... names){
    for (AbilityNames name: names){
      target.addAbility(LIST_OF_ABILITIES.get(name).copyAbility());
    }
  }

  public interface AbilityModifier {
    public void applyEffect
  }

}