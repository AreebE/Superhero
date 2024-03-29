package revitalizedLegends.gameSystem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 * an item for creating an entity.
 *
 */
public class EntityInfoItem
    implements Saveable
{

	public static final String TYPE_KEY = "type";
	public static final String NAME_KEY = "name";
	public static final String SPEED_KEY = "speed";
	public static final String ABILITIES_KEY = "abilities";
	public static final String EFFECTS_KEY = "effects";
	public static final String SHIELDS_KEY = "shields";
	public static final String STATE_KEY = "state";
	public static final String MAX_HEALTH_KEY = "max health";
	public static final String SHIELD_HEALTH_KEY = "shield";
	
    public String name;
    public int speed;
    public ArrayList<String> abilities;
    public ArrayList<String> effects;
    public ArrayList<String> shields;
    public String defaultState;
    public int maxHealth;
    public int shieldHealth;

    public static final int ABILITY = 0;
    public static final int EFFECT = 1;
    public static final int SHIELD = 2;
    public static final int STATE = 3;
    public static final int MAX_HEALTH = 4;
    public static final int SHIELD_HEALTH = 5;
    public static final int SPEED = 6;
    
    public EntityInfoItem(JSONObject json)
    {
    	name = json.getString(NAME_KEY);
    	speed = json.getInt(SPEED_KEY);
    	defaultState = json.getString(STATE_KEY);
    	maxHealth = json.getInt(MAX_HEALTH_KEY);
    	shieldHealth = json.getInt(SHIELD_HEALTH_KEY);
    	abilities = new ArrayList<>();
    	JSONArray jsonAbilities = json.getJSONArray(ABILITIES_KEY);
    	for (int i = 0; i < jsonAbilities.length(); i++)
    	{
    		abilities.add(jsonAbilities.getString(i).toLowerCase());
    	}

    	shields = new ArrayList<>();
    	JSONArray jsonShields = json.getJSONArray(SHIELDS_KEY);
    	for (int i = 0; i < jsonShields.length(); i++)
    	{
    		shields.add(jsonShields.getString(i).toLowerCase());
    	}
    	
    	effects = new ArrayList<>();
    	JSONArray jsonEffects = json.getJSONArray(EFFECTS_KEY);
    	for (int i = 0; i < jsonEffects.length(); i++)
    	{
    		effects.add(jsonEffects.getString(i).toLowerCase());
    	}
    }
    /**
     * a basic constructor for an entity info item
     * @param name the name of the entity
     * @param speed the speed of the entity
     * @param abilityNames the abilities it starts with
     * @param startingEffects the starting effects it has
     * @param startingShields the starting shields it has
     * @param defaultState the default state of the entity
     * @param maxHealth the max health it starts with
     * @param shieldHealth the starting amount of shield health
     */
    public EntityInfoItem(
        String name,
        int speed,
        ArrayList<String> abilityNames,
        ArrayList<String> startingEffects,
        ArrayList<String> startingShields,
        String defaultState,
        int maxHealth,
        int shieldHealth)
    {
        this.name = name;
        this.speed = speed;
        this.abilities = abilityNames;
        this.effects = startingEffects;
        this.shields = startingShields;
        this.maxHealth = maxHealth;
        this.defaultState = defaultState;
        this.shieldHealth = shieldHealth;
    }


    /**
     * Create an entity object
     */
    public Entity create(
    		Entity creator,
    		Game g)
    {
        Entity e = new Entity(name, speed, maxHealth, shieldHealth, g.getState(defaultState), creator);
//        System.out.println(e.getName());
        addItems(e, g);
        return e;
    }

    /**
     * Add some abilities, effects, and shields to the entity
     * @param e the recipient
     */
    protected void addItems(
        Entity e,
        Game g
    )
    {
        for (String a: abilities)
        {
//        	System.out.println(a);
//        	System.out.println(a + ", " + g.getAbility(a));
//        	System.out.println(abilities);
        	e.addAbility(g.getAbility(a));
        }
        e.addAbility(g.getAbility("pass turn"));
        for (String ef: effects)
        {
//        	System.out.println(ef);
        	e.addStartingEffect(g.getEffect(ef));
        }
        for (String s: shields)
        {
//        	System.out.println(s);
//        	System.out.println("e");
        	e.addStartingShield(g.getShield(s));
//        	System.out.println("f");
        }
    }

    /**
     * the name of the person
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * get speed
     * @return speed
     */
    protected int getSpeed()
    {
        return this.speed;
    }

    /**
     * get max health
     * @return max health
     */
    protected int getMaxHealth()
    {
        return this.maxHealth;
    }

    /**
     * get starting shield
     * @return starting shield
     */
    protected int getShieldHealth()
    {
        return this.shieldHealth;
    }
    
    /**
     * get the ability names
     * @return the ability names.
     */
    private ArrayList<String> abilitiesNames(){
      return this.abilities;
    }
    
    public JSONObject toJson()
    {
    	JSONObject infoItem = new JSONObject();
    	infoItem.put(TYPE_KEY, "basic");
    	infoItem.put(NAME_KEY, name);
    	infoItem.put(SPEED_KEY, speed);
    	infoItem.put(STATE_KEY, defaultState);
    	infoItem.put(MAX_HEALTH_KEY, maxHealth);
    	infoItem.put(SHIELD_HEALTH_KEY, shieldHealth);
    	
    	JSONArray abilities = new JSONArray();
    	for (String name: this.abilities)
    	{
    		abilities.put(name);
    	}
    	infoItem.put(ABILITIES_KEY, abilities);
    	
    	JSONArray effects = new JSONArray();
    	for (String name: this.effects)
    	{
    		effects.put(name);
    	}
    	infoItem.put(EFFECTS_KEY, effects);
    	
    	JSONArray shields = new JSONArray();
    	for (String name: this.shields)
    	{
    		shields.put(name);
    	}
    	infoItem.put(SHIELDS_KEY, shields);
    	return infoItem;
    }

    @Override
    public boolean verifyValidity(Storage s)
    {
        for (int i = 0; i < effects.size(); i++)
        {
            if (s.getEffect(effects.get(i)) == null)
            {
                return false;    
            }
        }
        for (int i = 0; i < shields.size(); i++)
        {
            if (s.getShield(shields.get(i)) == null)
            {
                return false;    
            }    
        }
        for (int i = 0; i < abilities.size(); i++)
        {
            if (s.getAbility(abilities.get(i)) == null)
            {
                // System.out.println(abilities.get(i));
                return false;
            }
        }
        return s.getState(defaultState) != null;
    }

    public void adjustItems(String thingToChange, int attribute, boolean remove)
    {
        ArrayList<String> itemsToChange = null;
        System.out.println(attribute + ", " + thingToChange);
        switch(attribute)
        {
            case ABILITY:
                itemsToChange = abilities;
                break;
            case EFFECT:
                itemsToChange = effects;
                break;
            case SHIELD:
                itemsToChange = shields;
                break;
            case STATE:
                defaultState = thingToChange;
                break;
            case MAX_HEALTH:
            	System.out.println("here");
                maxHealth += Integer.parseInt(thingToChange);
                break;
            case SHIELD_HEALTH:
            	System.out.println("here");

                shieldHealth += Integer.parseInt(thingToChange);
                break;
            case SPEED:
                speed += Integer.parseInt(thingToChange);
                break;
            
        }
        if(itemsToChange == null)
        {
            return;
        }
        if (!remove)
        {
        	itemsToChange.add((String) thingToChange);
//        	System.out.println(itemsToChange);
//        	System.out.println(abilities);
        
        }
        else 
        {
        	 for (int i = 0; i < itemsToChange.size(); i++)
             {
                 if (itemsToChange.get(i).equals(thingToChange))
                 {
                     itemsToChange.remove(i);
                 }
             }
        }
    }
    
    public EntityInfoItem copy()
    {
    	return new EntityInfoItem(
    			name, 
    			speed, 
    			abilities, 
    			effects, 
    			shields, 
    			defaultState, 
    			maxHealth, 
    			shieldHealth
    			);
    }
    
    public static int getCategory(int item)
    {
    	switch(item)
    	{	
    		case ABILITY:
    			return Storage.ABILITIES;
    		case EFFECT:
    			return Storage.EFFECTS;
    		case SHIELD:
    			return Storage.SHIELDS;
    		case STATE:
    			return Storage.STATES;
    		case SPEED:
    		case MAX_HEALTH:
    		case SHIELD_HEALTH:
    			return Storage.UNDEF;
    		default:
    			return -1;
    	}
    }
}