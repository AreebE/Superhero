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
    NULL,
    ALL
  }

   private final static EnumMap<ElementNames, Element> LIST_OF_ELEMENTS = new EnumMap<>(ElementNames.class){{

     put(ElementNames.FIRE, new Element("fire", "---", ElementNames.FIRE));
     put(ElementNames.ELECTRICITY, new Element("electricity", "---", ElementNames.ELECTRICITY));
     put(ElementNames.ICE, new Element("Ice", "---", ElementNames.ICE));
     put(ElementNames.NULL, new Element("Null", "normal/nothing", ElementNames.NULL));
     put(ElementNames.ALL, new Element("All", "Will trigger for anything", ElementNames.ALL));
   }};

  public static Element getElement(ElementNames name){
    return LIST_OF_ELEMENTS.get(name);
  }

  
}