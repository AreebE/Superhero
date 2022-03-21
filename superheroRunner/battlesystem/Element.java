package battlesystem;

/**
 * A class for storing elemental properties.
 *`
 */


public class Element {
  //private int type;
  
  private String name;
  private String description;
  private Elements.Name elementID;

  /**
   * A constructor for the element
   * @param name the name of this element
   * @param description The description of the element.
   * @param elementID This element's id
   */
  public Element(String name, String description, Elements.Name elementID){
    //this.type = type;
    this.name = name;
    this.description = description;
    this.elementID = elementID;
    //I think normal should null some of the effects
    // that would make more sense, yeah 
    // Like making the battlefield "more normal"
    // k, so letś kl

  }

  /**
   * get this element's id
   * @return the element's id
   */
  public Elements.Name getID(){
    return elementID;
  }
  
 
  /**
   * Represent how this object looks like as a string.
   * @return the name and the description.
   */
  @Override
  public String toString(){
    return name + ", " + description;
  }
  

  
}

