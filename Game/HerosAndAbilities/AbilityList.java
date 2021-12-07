
import java.util.ArrayList;
import java.util.HashMap;

public class AbilityList {
  // Prevents any instances of this class from being made

  //private ArrayList<Element> elements;

  private AbilityList(){}

  public static enum AbilityType{
    ATTACK, DEFENSE, SUPPORT
  }

  
  // Attack ability indexes 
  public final static int FIREBALL = 0000;
  public final static int SNOWBALL = 0001;
  public final static int LIGHTNING_STRIKE = 0002;
  public final static int PASS_TURN = 0003;

  // Defense indexes 
  public final static int HEAL_PULSE = 1000;
  public final static int PROTECT = 1001;
  public final static int COUNTERSTRIKE = 1002;

  // Support indexes
  public final static int ATTACK_UP = 2000;
  public final static int DEFENSE_UP = 2001;
  public final static int FLARE_UP = 2002;

  private final static HashMap<Integer, Ability> LIST_OF_ABILITIES = new HashMap<>(){{
    // Attack abilities
    put(FIREBALL, new AttackAbility("Fireball", "Launches a ball of fire", 1, 3, FIREBALL, new Element("Fire", "fire"), false, false));
    put(SNOWBALL, new AttackAbility("Snowball", "Launches a few snowballs. May cause frost or blindness.", 1, 3, SNOWBALL, new Element("Ice", "ice"), false, false));
    put(LIGHTNING_STRIKE, new AttackAbility("Lightning Strike", "Has a chance to shock the enemy.", 1, 3, LIGHTNING_STRIKE, new Element("Electricity", "electric"), false, false));
    put(PASS_TURN, new AttackAbility("Pass turn", "Allows the user to skip their turn", 0, 0, PASS_TURN, new Element("null", "null"), false, false));

    // Defense abilities
    put(HEAL_PULSE, new DefenseAbility("Heal pulse", "heals oneself", 2, 5, HEAL_PULSE, new Element("null", "null")));
    put(PROTECT, new DefenseAbility("Protect", "protects the user from any hit.", 3, 5, PROTECT, new Element("null", "null")));
    put(COUNTERSTRIKE, new DefenseAbility("Counterstrike", "Will counter any attack that comes forth", 5, 50, COUNTERSTRIKE, new Element("null", "null")));

    // Support abilities
    // System.out.println(listOfBuffs);
    put(ATTACK_UP, new SupportAbility("attack up", "boosts damage for x amount of time", 5, BuffList.getBuff(BuffList.ATTACK_BOOST), ATTACK_UP, new Element("null", "null")));
    put(DEFENSE_UP, new SupportAbility("defense up", "boosts defense for x amount of time", 5, BuffList.getBuff(BuffList.DEFENSE_BOOST), DEFENSE_UP, new Element("null", "null")));
    put(FLARE_UP, new SupportAbility("flare up", "Makes the user charge up their attacks", 10, BuffList.getBuff(BuffList.CHARGE), FLARE_UP, new Element("null", "null")));
  }};
  


  // Getting an ability's id, based on a name given
  private final static HashMap<String, Integer> namesToAbility = new HashMap<>(){{
    put("fireball", FIREBALL);

    put("snowball", SNOWBALL);

    put("lightning", LIGHTNING_STRIKE);

    put("protect", PROTECT);

    put("counterstrike", COUNTERSTRIKE);

    put("attack_up", ATTACK_UP);

    put("defense_up", DEFENSE_UP);

    put("pass", PASS_TURN);

    put("flare_up", FLARE_UP);
  }};

  // returns an ability's id
  public static Integer getID(String name){
    // System.out.println(name)
    // System.out.println(name + namesToAbility);
    Integer id = namesToAbility.get(
      name.toLowerCase());
    return id;
  }

  /**
  */
  public static void giveAbility(Superhero target, int ... ids){
    for (int i: ids){
      target.addAbility(LIST_OF_ABILITIES.get(i).copyAbility());
    }
  }
}