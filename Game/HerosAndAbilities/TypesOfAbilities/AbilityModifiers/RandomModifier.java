import java.util.Random;

public class RandomModifier implements AbilityModifier<Boolean>{
  private static final Random randomizer = new Random();
  private int chance;
  
  public RandomModifier(int chance){
    this.chance = chance;
  } 

  @Override 
  public Boolean triggerModifier(Superhero target, Superhero caster){
    if (randomizer.nextInt(AbilityList.MAX_CHANCE) < chance){
      return true;
    } else {
      return false;
    }
  }

  @Override
  public AbilityList.AbilityModifierNames getModifier(){
    return AbilityList.AbilityModifierNames.RANDOM;
  }
}