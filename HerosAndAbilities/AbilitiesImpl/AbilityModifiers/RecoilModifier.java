package battlesystem.abilityImpls.modifierImpl;

public class RecoilModifier implements AbilityModifier<String>{
  private int recoilDmg;
  private boolean piercesSheild;
  private boolean piercesDefense;
  
  public RecoilModifier(int recoilDmg, boolean piercesSheild, boolean piercesDefense){
    this.recoilDmg = recoilDmg;
    this.piercesSheild = piercesSheild;
    this.piercesDefense = piercesDefense;
  }

  @Override
  public String triggerModifier(Entity target, Entity caster) {
    caster.dealEffectDamage(recoilDmg, piercesSheild, piercesDefense);
    return "Recieved " + recoilDmg + " recoil damage.";
  };

  @Override
  public Ability.Modifier getModifier(){
    return Ability.Modifier.RECOIL;
  }
}