import java.util.ArrayList;
import java.util.HashMap;

public class ElementList{
  
  public static final int FIRE = 0;
  public static final int WATER = 1;
  public static final int ELECTRICITY = 2;
  public static final int ICE = 3;
  public static final int NULL = 4;

  private ElementList(){
    
  }

  private final static HashMap <Integer, Element> LIST_OF_ELEMENTS = new HashMap(){
    {
      put( FIRE, new Element("Fire", "weak against water"));
      put( WATER, new Element("Water", "weak against electricity"));
      put( ELECTRICITY, new Element("Electricity", "weak against ----"));
      put(ICE, new Element("Ice", "icey"));
      put( NULL, new Element("Null", "Reverts the stage to normal"));
    }
  };

  public Element getElement(int index){
    return LIST_OF_ELEMENTS.get(index);
  }

  
}