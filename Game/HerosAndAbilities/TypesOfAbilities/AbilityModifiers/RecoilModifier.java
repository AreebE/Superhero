public class RecoilModifier implements AbilityModifier<Void>{
  private int recoilDmg;
  private boolean piercesSheild;
  private boolean piercesDefense;
  
  public RecoilModifier(int recoilDmg, boolean piercesSheild, boolean piercesDefense){
    this.recoilDmg = recoilDmg;
    this.piercesSheild = piercesSheild;
    this.piercesDefense = piercesDefense;
  }

  @Override
  public Void triggerModifier(Superhero target, Superhero caster) {
    caster.dealDamage(recoilDmg, piercesSheild, piercesDefense);
    return null;
  };

  @Override
  public AbilityList.AbilityModifierNames getModifier(){
    return AbilityList.AbilityModifierNames.RECOIL;
  }
}