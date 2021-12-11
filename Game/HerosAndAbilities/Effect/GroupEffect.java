import java.util.ArrayList;

public class GroupEffect extends Effect {
  public ArrayList<Effect> listOfEffects;

  public GroupEffect(String name, String desc, Effect...effects){
    super(0, EffectList.EffectType.GROUP, 0, false, name, desc);
    listOfEffects = new ArrayList<>();
    for (int i = 0; i < effects.length; i++){
      listOfEffects.add(i, effects[i]);
    }
  }

  @Override 
  public void applyEffect(Superhero target){
    for (int i = listOfEffects.size() - 1; i >= 0; i++){
      Effect e = listOfEffects.get(i);
      e.applyEffect(target);
      if (e.getDuration() == 0){
        listOfEffects.remove(i);
      }
    }
  }

}