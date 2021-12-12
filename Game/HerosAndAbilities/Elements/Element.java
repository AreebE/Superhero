public class Element {
  //private int type;
  private String name;
  private String description;
  private ElementList.ElementNames elementID;

  public Element(String name, String description, ElementList.ElementNames elementID){
    //this.type = type;
    this.name = name;
    this.description = description;
    this.elementID = elementID;
    //I think normal should null some of the effects
    // that would make more sense, yeah 
    // Like making the battlefield "more normal"
    // k, so letś kl

  }

  public ElementList.ElementNames getID(){
    return elementID;
  }
  
  @Override 
  public String toString(){
    return name + ", " + description;
  }
}

