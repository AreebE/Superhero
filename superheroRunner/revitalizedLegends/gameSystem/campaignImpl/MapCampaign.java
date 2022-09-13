package revitalizedLegends.gameSystem.campaignImpl;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import revitalizedLegends.gameSystem.*;

public class MapCampaign extends Campaign
{
    private static final String GRID_KEY = "grid";
    private static final String START_POINT_KEY = "start point";

    private static final String TRADE_COLOR = "\033[0;32m";
    private static final String BATTLE_COLOR = "\033[0;31m";
    private static final String COMPLETED_COLOR = "\033[0;33m";
    
    public static final int WALL = -2;
    public static final int EMPTY = -1;
    
    private int[][] grid;
    private boolean[] usedEvents;
    private int[] startPoint;
    private int[] currentPoint;
    
    public MapCampaign(JSONObject json)
    {
        super(json);
        JSONArray gridJson = json.getJSONArray(GRID_KEY);
        grid = new int[gridJson.length()][gridJson.getJSONArray(0).length()];
        for (int i = 0; i < grid.length; i++)
        {
            JSONArray currentRow = gridJson.getJSONArray(i);
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = currentRow.getInt(j);   
            }
        }
        startPoint = new int[2];
        JSONArray startPointJson = json.getJSONArray(START_POINT_KEY);
        startPoint[0] = startPointJson.getInt(0);
        startPoint[1] = startPointJson.getInt(1);
        currentPoint = new int[2];
        currentPoint[0] = startPoint[0];
        currentPoint[1] = startPoint[1];
    }
    
    public MapCampaign
    (
        ArrayList<String> events,
        String protagName,
        String name,
        int[][] grid,
        int[] startPoint
    )
    {
        super(events, protagName, name);
        this.grid = grid;
        this.startPoint = startPoint;
        this.currentPoint = new int[2];
        currentPoint[0] = startPoint[0];
        currentPoint[1] = startPoint[1];
    }

    public void beginCampaign(
			Storage s,
			BattleLog log)
	{
		EntityInfoItem protagonist = s.getEntity(getProtagName()).copy();
		usedEvents = new boolean[getEvents().size()];
        OutputSystem output = getOutput();
        InputSystem input = getInput();
        output.displayString(getName() + "\n__________________________________________", 0);
        ArrayList<String> events = getEvents();
        boolean atEnd = false;
        while (!atEnd)
        {
            output.displayString(processGrid(s, events), 0);
            int typeOfLocation = grid[currentPoint[1]][currentPoint[0]];
            if (typeOfLocation != EMPTY && !usedEvents[typeOfLocation])
            {
                Event current = s.getEvent(events.get(typeOfLocation));
    			boolean isGameOver = current.executeEvent(protagonist, output, input, s, log);
                usedEvents[typeOfLocation] = true;
                if (isGameOver)
    			{
    				return;
    			}
                else if (typeOfLocation == events.size() - 1)
                {
                    atEnd = true;
                }
            }
            else 
            {
                Campaign.Direction directionToMove = input.getDirection(getPossibleDirections());
                switch (directionToMove)
                {
                    case UP:
                        currentPoint[1]--;
                        break;
                    case DOWN:
                        currentPoint[1]++;
                        break;
                    case LEFT:
                        currentPoint[0]--;
                        break;
                    case RIGHT:
                        currentPoint[0]++;
                        break;
                }
            }
           
        }
	}

    public String processGrid(Storage s, ArrayList<String> events)
    {
        StringBuilder gridDisplay = new StringBuilder();
        StringBuilder lineLayout = new StringBuilder();
        for (int j = 0; j < grid[0].length; j++)
        {
            lineLayout.append("\033[0;37m|---");
        }
        String singleLine = lineLayout.append("|").toString();
        gridDisplay.append(singleLine + "\n");
        for (int i = 0; i < grid.length; i++)
        {
            gridDisplay.append("| ");
            for (int j = 0; j < grid[0].length; j++)
            {
                if (currentPoint[1] == i && currentPoint[0] == j)
                {
                    gridDisplay
                        .append("\033[0;34m")
                        .append("*");  
                }
                else
                {
                    int num = grid[i][j];
                    switch(num)
                    {
                        case WALL:
                            gridDisplay
                                .append("\033[0;37m")
                                .append("*");  
                            break;
                        case EMPTY:
                            gridDisplay
                                .append("\033[0;37m")
                                .append(" ");
                            break;
                        default:
                            if (usedEvents[num])
                            {
                                gridDisplay.append(COMPLETED_COLOR)
                                    .append("*");
                                break;
                            }
                            else 
                            {
                                Event e = s.getEvent(events.get(num));
                                gridDisplay.append(((e.isBattleEvent()))? BATTLE_COLOR: TRADE_COLOR)
                                    .append("*");
                                break;
                            }
                                
                    }
                }
                gridDisplay.append("\033[0;37m").append(" | ");
            }
            gridDisplay.append("\n").append(singleLine).append("\n");
        }
        return gridDisplay.toString();
    }

    private ArrayList<Campaign.Direction> getPossibleDirections()
    {
        int[][] pointsToCheck = new int[][]
        {
            {currentPoint[0] - 1, currentPoint[1]},
            {currentPoint[0], currentPoint[1] - 1},
            {currentPoint[0] + 1, currentPoint[1]},
            {currentPoint[0], currentPoint[1] + 1},
        }; 

        Campaign.Direction[] directions = new Campaign.Direction[]
            {
                Campaign.Direction.LEFT,
                Campaign.Direction.UP,
                Campaign.Direction.RIGHT,
                Campaign.Direction.DOWN
            };
        ArrayList<Campaign.Direction> possibleDirections = new ArrayList<>();
        for (int i = 0; i < pointsToCheck.length; i++)
        {
            int[] point = pointsToCheck[i];
            if (
                point[0] >= 0
                && point[0] < grid[0].length
                && point[1] >= 0
                && point[1] < grid.length
                && grid[point[1]][point[0]] != WALL
            )        
            {
                System.out.println(point[0] + ", " + point[1]);
                possibleDirections.add(directions[i]);
            }
        }
        return possibleDirections;
    }
    
    @Override
	public JSONObject toJson() {
		JSONObject json = super.toJson();
	    JSONArray gridJson = new JSONArray();
        for (int i = 0; i < grid.length; i++)
        {
            JSONArray currentRow = new JSONArray();
            for (int j = 0; j < grid[i].length; j++)
            {
                currentRow.put(j, grid[i][j]);
            }
            gridJson.put(i, currentRow);
        }
        json.put(GRID_KEY, gridJson);

        JSONArray startJson = new JSONArray();
        startJson.put(0, startPoint[0]);
        startJson.put(1, startPoint[1]);
        json.put(START_POINT_KEY, startJson);
		return json;
	}
}