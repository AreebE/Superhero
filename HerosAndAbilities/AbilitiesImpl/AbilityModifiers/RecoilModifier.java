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
  public Void triggerModifier(Entity target, Entity caster) {
    caster.dealDamage(recoilDmg, piercesSheild, piercesDefense, target, null);
    return null;
  };

  @Override
  public AbilityList.ModifierName getModifier(){
    return AbilityList.ModifierName.RECOIL;
  }
}