package gameSystem;

import org.json.JSONObject;

public interface Saveable
{
    public JSONObject toJson();        
    public boolean verifyValidity(Storage s);
}