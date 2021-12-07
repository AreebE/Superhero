public class Element {
  //private int type;
  private String name;
  private String description;

  public Element(String name, String description){
    //this.type = type;
    this.name = name;
    this.description = description;
    //I think normal should null some of the effects
    // that would make more sense, yeah 
    // Like making the battlefield "more normal"
    // k, so letś kl

  }
  
  @Override 
  public String toString(){
    return name + ", " + description;
  }
}

