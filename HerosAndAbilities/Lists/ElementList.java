import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

public class ElementList{

  //private final static EnumMap<AbilityNames, Ability> LIST_OF_ABILITIES_2 = new EnumMap<>(AbilityNames.class)

  private ElementList(){

  }

  public static enum Name{
    FIRE,
    ELECTRICITY,
    ICE,
    NULL,
    EARTH,
    ALL
  }

   private final static EnumMap<Name, Element> LIST_OF_ELEMENTS = new EnumMap<>(Name.class){{

     put(Name.FIRE, new Element("fire", "---", Name.FIRE));
     put(Name.ELECTRICITY, new Element("electricity", "---", Name.ELECTRICITY));
     put(Name.ICE, new Element("Ice", "---", Name.ICE));
     put(Name.NULL, new Element("Null", "normal/nothing", Name.NULL));
          put(Name.EARTH, new Element("Earth", "mother earth", Name.EARTH));

     put(Name.ALL, new Element("All", "Will trigger for anything", Name.ALL));
   }};

  public static Element getElement(Name name){
    return LIST_OF_ELEMENTS.get(name);
  }

  
}