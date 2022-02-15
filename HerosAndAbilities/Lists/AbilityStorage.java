package battlesystem;

import java.util.ArrayList;
import java.util.Arrays;

public class AbilityStorage{
  AbilityStorage(){
  }
  //note SpawnableAbilitys arent working atm;
  
  public static ArrayList<Ability> getAll(){
    Ability[] a = new Ability[100];
    a=new Ability[]{
      new AttackAbility
      (
          "Ram",
          "Ram yourself into your opponent",
          1,
          5,
          Elements.getElement(Elements.Name.NULL),
          false,
          false,
          new PercentageModifier(10, Entity.Statistic.SPEED, true)
      ),

      new AttackAbility
      (
          "Telekinesis",
          "You slam a rock into your opponent",
          1, 
          10, 
          Elements.getElement(Elements.Name.NULL),
          false,
          true
      ),

      new AttackAbility
      (
          "Fireball", 
          "Launches a ball of fire", 
          1, 
          3, 
          Elements.getElement(Elements.Name.FIRE), 
          false, 
          false
      ),

      new AttackAbility 
      (
          "Earthquake",
          "Hits everyone",
          5,
          50,
          Elements.getElement(Elements.Name.EARTH),
          false, 
          false,
          new GroupModifier(100, -1)
      ),

      new AttackAbility
      (
          "Laser",
          "Repeatedly attack the opponent",
          1,
          1,
          Elements.getElement(Elements.Name.FIRE),
          false,
          true,
          new MultiCastModifier(10)
      ),
      new AttackAbility
      (
          "Scratch",
          "Do very minor damage",
          1,
          1,
          Elements.getElement(Elements.Name.NULL),
          true,
          true,
          new PercentageModifier(10, Entity.Statistic.SPEED, true)
      ),

      new AttackAbility
      (
          "Snowball", 
          "Launches a few snowballs. May cause frost or blindness.", 
          1, 
          3,  
          Elements.getElement(Elements.Name.ICE), 
          false, 
          true,
          new MultiCastModifier(4)
      ),

      new AttackAbility
      (
          "Lightning Strike", 
          "Has a chance to shock the enemy.", 
          9, 
          50, 
          Elements.getElement(Elements.Name.ELECTRICITY), 
          true, 
          true
      ),

      new AttackAbility
      (
          "Wide Slash",
          "Performs a sweeping attack, targetting two other characters",
          4,
          10,
          Elements.getElement(Elements.Name.NULL),
          false,
          false,
          new GroupModifier(50, 2)
      ),

      new AttackStatusAbility
      (
          "Poison Slash",
          "Poisons the enemy upon hit",
          4, 
          2,
          Elements.getElement(Elements.Name.NULL),
          false,
          false,
          Effects.getEffect(Effects.Name.POISON)
      ),
      new PassAbility
      (
          "Pass turn", 
          "Allows the user to skip their turn"
      ),

      new AttackStatusAbility 
      (
          "Ground Suction",
          "Pulls the user into the ground, cutting them on the rocks and they bleed out over time",
          5,
          10,
          Elements.getElement(Elements.Name.EARTH),
          true, 
          true,
          Effects.getEffect(Effects.Name.BLEED)
      ),

      new DefenseAbility
      (
          "Counter", 
          "counter the next attack that comes", 
          3, 
          Shields.getShield(Shields.Name.COUNTER),  
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Warning", 
          "The user will be warned not to break your shield", 
          3, 
          Shields.getShield(Shields.Name.SELF_DESTRUCT), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Witch spell", 
          "When attacked, cast a curse on whoever attacked you", 
          3, 
          Shields.getShield(Shields.Name.WITCH_CURSE), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Illusion spell", 
          "Create an illusion that retaliates when attacked", 
          3, 
          Shields.getShield(Shields.Name.ILLUSION), 
          Elements.getElement(Elements.Name.LIGHT)
      ),

      new DefenseAbility
      (
          "First aid", 
          "Heal oneself upon hit", 
          3, 
          Shields.getShield(Shields.Name.SELF_CARE), 
          Elements.getElement(Elements.Name.NULL)
      ),
      /*
      new SpawnableAbility
      (
          "Summon Squirrel",
          "Summon a small squirrel to help you",
          5,
          Spawnables.Name.SQUIRREL,
          Elements.getElement(Elements.Name.NULL)
      ),

      new SpawnableAbility
      (
          "Summon Golem",
          "Summon a crystal to help you",
          5,
          Spawnables.Name.CRYSTAL,
          Elements.getElement(Elements.Name.NULL)
      ),
      */
      new SupportAbility
      (
          "Heal pulse", 
          "boosts defense for x amount of time",
          1, 
          Effects.getEffect(Effects.Name.INSTANT_HEAL), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Spore", 
          "Gives the opponent an infection",
          9, 
          Effects.getEffect(Effects.Name.FUNGAL_INFECTION), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Protect", 
          "protects the user from any hit",
          3, 
          Effects.getEffect(Effects.Name.INSTANT_SHIELD), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "burn up", 
          "The target will burn up for a short amount of time",
          3, 
          Effects.getEffect(Effects.Name.BURNOUT), 
          Elements.getElement(Elements.Name.FIRE)
      ),

      new SupportAbility
      (
          "Counterstrike", 
          "Adds a lot of shield",
          3, 
          Effects.getEffect(Effects.Name.INSTANT_SHIELD_X), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "attack up", 
          "boosts damage for x amount of time", 
          5, 
          Effects.getEffect(Effects.Name.ATTACK_BOOST),  
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "defense up", 
          "boosts defense for x amount of time",
          5, 
          Effects.getEffect(Effects.Name.DEFENSE_BOOST), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "flare up", 
          "Makes the user charge up their attacks", 
          10, 
          Effects.getEffect(Effects.Name.CHARGE), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Poison", 
          "Will poison the person targeted", 
          0, 
          Effects.getEffect(Effects.Name.POISON), 
          Elements.getElement(Elements.Name.NULL), 
          new RandomModifier(256 / 4 * 3)
      ),

      new SupportAbility
      (
          "Sacrifice", 
          "Injures the user to cause more damage later", 
          4, 
          Effects.getEffect(Effects.Name.CURSE), 
          Elements.getElement(Elements.Name.NULL), 
          new RecoilModifier
          (
              2, 
              true, 
              true
          )
      ),

      new SupportAbility
      (
          "Defensive Stance", 
          "The user decides to be cautious for some time", 
          3, 
          Effects.getEffect(Effects.Name.GUARD), 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Construct", 
          "The user starts to build up their stats", 
          2, 
          Effects.getEffect(Effects.Name.BUILD_UP), 

          Elements.getElement(Elements.Name.NULL)
      ),

      new StateChangeAbility
      (
          "Stun Spore", 
          "Paralyze the target, preventing them from doing anything", 
          2, 
          States.get(States.Name.PARALYZED),
          Elements.getElement(Elements.Name.NULL)
      ),

      new StateChangeAbility
      (
          "Invigorate", 
          "Energize the target, allowing them to make more moves per turn", 
          2, 
          States.get(States.Name.ENERGIZED), 
          Elements.getElement(Elements.Name.NULL)
      )
        
    };
    ArrayList<Ability> out = new ArrayList<>(Arrays.asList(a));
    return out;
  }
}