package battlesystem.infoItemImpls;

import battlesystem.EntityInfoItem;
import org.json.*;
import java.util.List;
import java.util.ArrayList;


public abstract class BaseAIInfoItem<T> extends EntityInfoItem
{
    private static final String MOVES_KEY = "moves";
    private ArrayList<MoveItem<T>> moves;
    
    public BaseAIInfoItem(JSONObject json)
    {
        super(json);    
        loadMoves(json.getJSONArray(MOVES_KEY));
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
        ArrayList<MoveItem<T>> moves
    )
    {
        super(name, speed, abilityNames, startingEffects, startingShields, defaultState, maxHealth, shieldHealth);
        this.moves = moves;
    }

    protected abstract void loadMoves(JSONArray moves);
    
    @Override
    public JSONObject toJson()
    {
        JSONObject json = super.toJson();
        JSONArray moveArray = new JSONArray();
        for (int i = 0; i < moves.size(); i++)
        {
            moveArray.put(i, moves.get(i).toJson());        
        }
        json.put(MOVES_KEY, moveArray);
        return json;
    }
}