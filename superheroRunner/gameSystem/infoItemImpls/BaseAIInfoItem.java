package gameSystem.infoItemImpls;

import org.json.*;

import gameSystem.Entity;
import gameSystem.EntityInfoItem;
import gameSystem.Game;
import gameSystem.entityImpls.BasicAIEntity;

import java.util.List;
import java.util.ArrayList;


public class BaseAIInfoItem extends EntityInfoItem
{
    private static final String MOVES_KEY = "moves";
    private ArrayList<MoveItem> moves;
    
    public BaseAIInfoItem(JSONObject json)
    {
        super(json);    

        JSONArray jsonMoves = json.getJSONArray(MOVES_KEY);
        moves = new ArrayList<>(jsonMoves.length());
        for (int i = 0; i < jsonMoves.length(); i++)
        {
                        	    	// System.out.println("basic attributes");

        	moves.add(InfoItemReader.loadMoveItem(jsonMoves.getJSONObject(i)));
        }
    }
    
    public BaseAIInfoItem(
        String name,
        int speed,
        ArrayList<String> abilityNames,
        ArrayList<String> startingEffects,
        ArrayList<String> startingShields,
        String defaultState,
        int maxHealth,
        int shieldHealth,
        ArrayList<MoveItem> moves
    )
    {
        super(name, speed, abilityNames, startingEffects, startingShields, defaultState, maxHealth, shieldHealth);
        this.moves = moves;
    }

    @Override
  public Entity create(
		  Entity creator,
		  Game g) 
  {
      /*
public BasicAIEntity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator,
        List<MoveItem> moves
    )
    {*/
    BasicAIEntity ai = new BasicAIEntity(
        getName(), 
        getSpeed(), 
        getMaxHealth(),                                  
        getShieldHealth(), 
        g.getState(super.defaultState),
        creator, 
        moves);
    super.addItems(ai, g);
    return ai;
  }
    
    @Override
    public JSONObject toJson()
    {
        JSONObject json = super.toJson();
        JSONArray moveArray = new JSONArray();
        for (int i = 0; i < moves.size(); i++)
        {
            moveArray.put(i, moves.get(i).toJson());        
        }
        json.put(EntityInfoItem.TYPE_KEY, InfoItemReader.SIMPLE_AI_INFO_ITEM);
        json.put(MOVES_KEY, moveArray);
        System.out.println(json);
        return json;
    }
}