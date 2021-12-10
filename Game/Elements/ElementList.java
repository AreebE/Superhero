import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

public class ElementList{

  //private final static EnumMap<AbilityNames, Ability> LIST_OF_ABILITIES_2 = new EnumMap<>(AbilityNames.class)

  private ElementList(){

  }

  public static enum ElementNames{
    FIRE,
    ELECTRICITY,
    ICE,
    NULL
  }

   private final static EnumMap<ElementNames, Element> LIST_OF_ELEMENTS = new EnumMap<>(ElementNames.class){{

     put(ElementNames.FIRE, new Element("fire", "---"));
     put(ElementNames.ELECTRICITY, new Element("electricity", "---"));
     put(ElementNames.ICE, new Element("Ice", "---"));
     put(ElementNames.NULL, new Element("Null", "normal/nothing"));
     
   }};

  public static Element getElement(ElementNames name){
    return LIST_OF_ELEMENTS.get(name);
  }

  
}