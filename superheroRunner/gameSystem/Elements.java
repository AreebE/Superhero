package gameSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

/**
 * This will store all of the elements used. Despite being a place where elements are stored, this is NOT extending the database subclass, mainly because the elements are intended to be static and unchanging.
 *
 */
public final class Elements{

  //private final static EnumMap<AbilityNames, Ability> LIST_OF_ABILITIES_2 = new EnumMap<>(AbilityNames.class)

  private Elements(){

  }

  public static enum Name{
    FIRE,
    ELECTRICITY,
    ICE,
    NULL,
    EARTH,
    LIGHT,
    ALL
  }

   private final static EnumMap<Name, Element> LIST_OF_ELEMENTS = new EnumMap<Name, Element>(Name.class){{

    put(Name.FIRE, new Element("fire", "---", Name.FIRE));
    put(Name.ELECTRICITY, new Element("electricity", "---", Name.ELECTRICITY));
    put(Name.ICE, new Element("Ice", "---", Name.ICE));
    put(Name.NULL, new Element("Null", "normal/nothing", Name.NULL));
    put(Name.EARTH, new Element("Earth", "mother earth", Name.EARTH));
    put(Name.LIGHT, new Element("Light", "shiny", Name.EARTH));

    put(Name.ALL, new Element("All", "Will trigger for anything", Name.ALL));
   }};

  public static Element getElement(Name name){
    return LIST_OF_ELEMENTS.get(name);
  }

  public static Element getElement(String name)
  {
	  switch(name)
	  {
	  		case "fire":
	  			return getElement(Name.FIRE);
	  		case "electricity":
	  			return getElement(Name.ELECTRICITY);
	  		case "ice":
	  			return getElement(Name.ICE);
	  		case "null":
	  			return getElement(Name.NULL);
	  		case "earth":
	  			return getElement(Name.EARTH);
	  		case "light":
	  			return getElement(Name.LIGHT);
	  		case "all":
	  			return getElement(Name.ALL);
	  		default:
//	  			return getElement(Name.NULL);
	  }
	  return null;
  }
}