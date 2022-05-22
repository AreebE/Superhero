package loaders;

import java.io.*;
import java.util.*;

import battlesystem.Ability;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.State;
import battlesystem.objectMapImpls.*;
import game.Storage;
//this thing is kinda nuts and needs some work also it no longer 
// deals with files so i dont think it needs to be in FileParsing
public class CustomMaker {
    private interface InputSystem
    {
        public boolean askForBoolean(String[] prompts);
        public int askForInteger(int min, int max, String[] prompts);
        public String askForItem(String[] prompts, Storage items);
    }
    
    private static final int MAX_HEALTH = 2000;
    private static final int MIN_HEALTH = 200;
    private static final int MIN_SHIELD = 50;
    private static final int MAX_SHIELD = 300;
    private static final int MAX_SPEED = 1000;
    private static final int MIN_SPEED = 0;

    private static final int QUESTION = 0;
    private static final int BELOW_MINIMUM = 1;
    private static final int ABOVE_MAXIMUM = 2;
    private static final int ERROR_MESSAGE = 3;
    private static final int NO_ORIGINALITY = 4;
    private static final int AWFULLY_LOW = 5;

    private static final HashSet<Integer> COMMON_NUMBERS = new HashSet<Integer>()
    {{
        this.add(42);
        this.add(420);
        this.add(69);
        this.add(690);
        this.add(101);
        this.add(1010);
        this.add(0);
        this.add(1);
        this.add(2);
        this.add(3);
        this.add(123);
        this.add(1234);
    }};

    private static final HashSet<String> YES_INPUT = new HashSet<String>()
    {{
        this.add("yes");
        this.add("y");
        this.add("ye");
        this.add("yeah");
    }};

    private static final HashSet<String> NO_INPUT = new HashSet<String>()
    {{
        this.add("no");
        this.add("nah");
        this.add("nope");
        this.add("n");
    }};


  CustomMaker() {
  }
    
  public static Entity askNMakeSuperhero(
      Storage storage) 
    {
    Scanner sc = new Scanner(System.in);
    System.out.println("And so, the summoning sigil beckons you. \n \n");
    System.out.println("First, we must pick a name.");
    System.out.println("What will our creation be named? ");
    String name = sc.next();
    System.out.println(name + "... A good name for our creation. Now, we must play a little number game.");
    System.out.println("");
    int speed = askUserForInteger(sc, MIN_SPEED, MAX_SPEED, new String[]{
        "How fast do you desire " + name + " to be? A number will suffice.  ",
        "I'm sorry, but no creation of ours can travel back in time. At least, not without using an ability, but I digress.",
        "That is a bit too fast for our liking. Please input something smaller.",
        "I have little patience for games. Please enter a valid number. ",
        "I see you lack originality.",
        "I would have done something a bit higher, but nobody's perfect."
    });
      
    System.out.println("Good, very good. Now, the health must be determined.");
    int maxHealth = askUserForInteger(sc, MIN_HEALTH, MAX_HEALTH, new String[]{
        "What is the health level of our creation?",
        "I do wish you would take this seriously.",
        "A fighter must have some health. Please put in something higher.",
        "That amount of health is a bit too high. We do care about the balance of our system.",
        "We wish you weren't so predictable.",
        "Hmm... it seems a bit low. Are you sure you want to proceed with this amount? Then again, you wouldn't have said otherwise."
    });

    System.out.println("Time for the shield to be determined.");
    int shieldHealth = askUserForInteger(sc, MIN_SHIELD, MAX_SHIELD, new String[]{
        "How much shield will the creation start with for each fight?",
        "...",
        "We also wish a negative amount of shield was possible, but sadly, reality does not allow so.",
        "That shield... is a bit too strong.",
        "Must you pick such childish numbers?",
        "A bold choice, but perhaps too bold. Perhaps you would want to invest your points in other areas?"
    });
    System.out.println("Now, this is where the fun begins. Are you as excited as we are?");
    System.out.println("It will not do that your character lacks any sort of default condition.");
    System.out.println("As for what a \'default\' condition entails, think of a character\'s normal state, when no status effects are at play.");

    boolean useCustomState = askForBoolean(sc, new String[]
                                           {
                                               "Would you rather pick a custom one, or simply the standard?",
                                               "Why are you like this?",
                                               "",
                                               "",
                                               "We hoped for you to use some creativity.",
                                               ""
                                           });
    if (useCustomState)
    {
        System.out.println("Ah, how interesting. Very well!");
        boolean usePreloadedState = askForBoolean(sc, new String[]
                                           {
                                               "One more question: do you want to use a preloaded state?",
                                               "I... *Ahem!* We cannot recognize that.",
                                               "",
                                               "",
                                               "That is alright. It may be better to go with what is already made.",
                                               ""
                                           });
        if (usePreloadedState)
        {
            
        }
                
    }
    ArrayList<String> abilityNames = new ArrayList<String>();
    ArrayList<String> effectNames = new ArrayList<String>();
    ArrayList<String> shieldNames = new ArrayList<String>();
    String defaultState = "";
      
    EntityInfoItem eii = new EntityInfoItem(name,
        speed,
        abilityNames,
        effectNames,
        shieldNames,
        defaultState,
        maxHealth,
        shieldHealth);
    /*
    String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator
    */
      return null;
  }

    private static int askUserForInteger(
        Scanner input, 
        int min, 
        int max, 
        String[] prompts)
    {
        int result = -1;
                    System.out.println(prompts[QUESTION]);

        while (result == -1)
        {
            try
            {
                result = input.nextInt();
                if (result < min)
                {
                    System.out.println(prompts[BELOW_MINIMUM]);
                    result = -1;
                }
                else if (result > max)
                {
                    System.out.println(prompts[ABOVE_MAXIMUM]);
                    result = -1;
                }
            } catch(/*Mistyped exception*/ Exception e)
            {
                System.out.println(prompts[ERROR_MESSAGE]);       
            }
        }
        if (COMMON_NUMBERS.contains(result))
        {
            System.out.println(prompts[NO_ORIGINALITY]);
        }
        else if (result < (max - min) / 10 + min)
        {
            System.out.println(prompts[AWFULLY_LOW]);
        }
        return result;
    }

    private static boolean askForBoolean(
        Scanner input, 
        String[] prompts)
    {
        System.out.println(prompts[QUESTION]);
        String answer = null;
        while (answer == null)
        {
            answer = input.next().toLowerCase();
            if (!YES_INPUT.contains(answer)
                && !NO_INPUT.contains(answer))
            {
                System.out.println(prompts[ERROR_MESSAGE]);
            }
        }
        if (NO_INPUT.contains(answer))
        {
            System.out.println(prompts[NO_ORIGINALITY]);
        }
        return YES_INPUT.contains(answer);
    }

    private static String askForItem(
        String[] prompts, 
        Storage items,
        int category)
    {
        
        String result = "";
        return result;
    }
}