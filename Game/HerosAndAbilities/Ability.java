

public abstract class Ability{
  private String name;
  private String description;
  private int cooldown;
  private int turnsSinceUse;
  private AbilityList.AbilityType type;
  private AbilityList.AbilityNames enumName;
  private Element em;

  public Ability(String name, String desc, int cooldown, AbilityList.AbilityType type, AbilityList.AbilityNames enumName, Element em){
    this.name = name;
    this.description = desc;
    this.cooldown = cooldown;
    this.type = type;
    this.turnsSinceUse = cooldown;
    this.enumName = enumName;
    this.em = em;
  }
  //alt const. for customMaker
  // Note: AbilityType is only in the ability list
  public Ability(String name, String desc){
    this.name = name;
    this.description = desc;
    this.cooldown = 2;
    this.type = AbilityList.AbilityType.ATTACK;
    this.turnsSinceUse = cooldown;
    this.enumName = enumName;
    this.em = em;
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public int getCooldown(){
    return cooldown;
  }



  public Element getElement(){
    return em;
  }

  public AbilityList.AbilityNames getEnumName(){
    return enumName;
  }

  public boolean useAbility(Superhero target, Superhero caster){
    turnsSinceUse = 0;
    castAbility(target, caster);
    return true;
  }
  
  protected abstract void castAbility(Superhero target, Superhero caster);
  
  @Override
  public String toString(){
    StringBuilder output = new StringBuilder();
    output.append(name).append(": ").append(description).append(". ");
    if (cooldown != 0){
      output.append("Has a cooldown of ").append(cooldown).append(" turns.");
    }
    else {
      output.append("It is a passive ability.");
    }
    return output.toString();
  }

  @Override
  public boolean equals(Object other){
    Ability otherAbility = (Ability) other;
    return this.name.equals(otherAbility.getName());
  }

  public void reduceCooldown(){
    if (turnsSinceUse < cooldown){
      turnsSinceUse++;
    }
  }


  public boolean ableToUseAbility(){
    // System.out.println("turns since use for " + name + ": " + turnsSinceUse);
    return turnsSinceUse == cooldown;
  }

  public int getTurnsNeeded(){
    return cooldown - turnsSinceUse;
  }

  public abstract Ability copyAbility();
}