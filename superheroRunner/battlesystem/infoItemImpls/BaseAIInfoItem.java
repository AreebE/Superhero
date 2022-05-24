package battlesystem.infoItemImpls;

import battlesystem.EntityInfoItem;
import org.json.*;
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