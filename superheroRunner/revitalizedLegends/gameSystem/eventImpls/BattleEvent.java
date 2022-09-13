package revitalizedLegends.gameSystem.eventImpls;

import java.util.ArrayList;

import org.json.JSONObject;

import revitalizedLegends.game.InnerGame;
import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.EntityInfoItem;
import revitalizedLegends.gameSystem.Event;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.InputSystem;
import revitalizedLegends.gameSystem.OutputSystem;
import revitalizedLegends.gameSystem.Storage;

public class BattleEvent 
	extends Event{

	private static final String LOSS_LINE_KEY = "loss line";
	private String lossLine;
	
	public BattleEvent(JSONObject json)
	{
		super(json);
		lossLine = json.getString(LOSS_LINE_KEY);
	}
	
	public BattleEvent(
			String title, 
			ArrayList<String> preludeLines, 
			ArrayList<ArrayList<String>> postludeLines,
			ArrayList<String[]> choices, 
			String prompt,
			String lossLine) {
		super(title, preludeLines, postludeLines, choices, prompt);
		this.lossLine = lossLine;
	}



	@Override
	public boolean verifyValidity(Storage s) {
		ArrayList<String[]> choices = super.getChoices();
		for (int i = 0; i < choices.size(); i++)
		{
//			System.out.println(choices.get(i)[Event.ACTION]);
//			System.out.println(s.getEncounter(choices.get(i)[ACTION]));
			if (s.getEncounter(choices.get(i)[Event.ACTION]) == null)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean executeAction(
			String[] choice, 
			BattleLog log,
			Storage s,
			EntityInfoItem protag,
			InputSystem input,
			OutputSystem output) {
		// TODO Auto-generated method stub
		Game g = new InnerGame(s.getEncounter(choice[ACTION]), s, null, protag);
//		g.setInputSystem(input);
//		g.setOutputSystem(output);
		boolean isGameOver = g.startFight(protag.getName());
		if (isGameOver)
		{
			output.displayString(lossLine, 0);
		}
		return isGameOver;
	}

    @Override
    public boolean isBattleEvent()
    {
        return true;
    }

	@Override
	public JSONObject toJson()
	{
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, EventLoader.BATTLE);
		json.put(LOSS_LINE_KEY, lossLine);
		return json;
	}
}
