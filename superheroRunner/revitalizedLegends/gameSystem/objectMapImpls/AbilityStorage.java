package revitalizedLegends.gameSystem.objectMapImpls;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import revitalizedLegends.gameSystem.Ability;
import revitalizedLegends.gameSystem.Elements;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.GroupModifier;
import revitalizedLegends.gameSystem.abilityImpls.AttackAbility;
import revitalizedLegends.gameSystem.abilityImpls.AttackStatusAbility;
import revitalizedLegends.gameSystem.abilityImpls.CleanseAbility;
import revitalizedLegends.gameSystem.abilityImpls.DefenseAbility;
import revitalizedLegends.gameSystem.abilityImpls.PassAbility;
import revitalizedLegends.gameSystem.abilityImpls.SpawnableAbility;
import revitalizedLegends.gameSystem.abilityImpls.StateChangeAbility;
import revitalizedLegends.gameSystem.abilityImpls.SupportAbility;
import revitalizedLegends.modifiers.abilityMods.MultiCastModifier;
import revitalizedLegends.modifiers.abilityMods.PercentageModifier;
import revitalizedLegends.modifiers.abilityMods.RandomModifier;

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
          new GroupModifier(-1)
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
          new GroupModifier(2)
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
          "poison",
          false
      ),
      new PassAbility
      (
          "Pass turn", 
          "Allows the user to skip their turn",
          Elements.getElement(Elements.Name.NULL)
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
          "bleed",
          false
      ),

      new DefenseAbility
      (
          "Counter", 
          "counter the next attack that comes", 
          3, 
          "counter",  
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Warning", 
          "The user will be warned not to break your shield", 
          3, 
          "self destruct", 
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Witch spell", 
          "When attacked, cast a curse on whoever attacked you", 
          3, 
          "witch\'s curse",
          Elements.getElement(Elements.Name.NULL)
      ),

      new DefenseAbility
      (
          "Illusion spell", 
          "Create an illusion that retaliates when attacked", 
          3, 
          "illusion",
          Elements.getElement(Elements.Name.LIGHT)
      ),

      new DefenseAbility
      (
          "First aid", 
          "Heal oneself upon hit", 
          3, 
          "self care",
          Elements.getElement(Elements.Name.NULL)
      ),
      
      new SpawnableAbility
      (
          "Summon Squirrel",
          "Summon a small squirrel to help you",
          5,
          "squirrel",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SpawnableAbility
      (
          "Summon Golem",
          "Summon a crystal to help you",
          5,
          "crystal",
          Elements.getElement(Elements.Name.NULL)
      ),
      
      new SupportAbility
      (
          "Heal pulse", 
          "boosts defense for x amount of time",
          1, 
          "instant heal", 
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Spore", 
          "Gives the opponent an infection",
          9, 
          "fungal infection",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Protect", 
          "protects the user from any hit",
          3, 
          "instant shield",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "burn up", 
          "The target will burn up for a short amount of time",
          3, 
          "burnout",
          Elements.getElement(Elements.Name.FIRE)
      ),

      new SupportAbility
      (
          "Counterstrike", 
          "Adds a lot of shield",
          3, 
          "mega instant shield",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "attack up", 
          "boosts damage for x amount of time", 
          5, 
          "attack boost",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "defense up", 
          "boosts defense for x amount of time",
          5, 
          "defense up",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "flare up", 
          "Makes the user charge up their attacks", 
          10, 
          "charge",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Poison", 
          "Will poison the person targeted", 
          0, 
          "poison",
          Elements.getElement(Elements.Name.NULL),
          new RandomModifier(256 / 4 * 3)
      ),

      new SupportAbility
      (
          "Sacrifice", 
          "Injures the user to cause more damage later", 
          4, 
          "curse",
          Elements.getElement(Elements.Name.NULL)
//          new RecoilModifier
//          (
//              2, 
//              true, 
//              true
//          )
      ),

      new SupportAbility
      (
          "Defensive Stance", 
          "The user decides to be cautious for some time", 
          3, 
          "guard",
          Elements.getElement(Elements.Name.NULL)
      ),

      new SupportAbility
      (
          "Construct", 
          "The user starts to build up their stats", 
          2, 
          "build up",
          Elements.getElement(Elements.Name.NULL)
      ),

      new StateChangeAbility
      (
          "Stun Spore", 
          "Paralyze the target, preventing them from doing anything", 
          2, 
          "paralyzed",
          Elements.getElement(Elements.Name.NULL)
      ),

      new StateChangeAbility
      (
          "Invigorate", 
          "Energize the target, allowing them to make more moves per turn", 
          2, 
          "energized",
          Elements.getElement(Elements.Name.NULL)
      ),
      
      new CleanseAbility
      (
        "Pray",
        "cleanse oneself of all effects",
        5,
        Elements.getElement(Elements.Name.ALL)
      )
    };
    ArrayList<Ability> out = new ArrayList<>(Arrays.asList(a));
    return out;
  }
  
  public static JSONArray loadAbilities()
  {
  	JSONArray json = new JSONArray();
  	ArrayList<Ability> abilities = getAll();
//  	Iterator<Name> effects = EFFECTS.keySet().iterator();
  	for (int i = 0; i < abilities.size(); i++)
  	{
  		json.put(abilities.get(i).toJson());
  	}
	return json;
  }
}