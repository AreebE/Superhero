package gameSystem.battlelogImpls;

import java.util.ArrayList;
import java.util.Arrays;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.objectMapImpls.Effects;

/**
 * A battle log for a basic text output
 *
 */
public class StringBattleLog extends BattleLog<ArrayList<String>>
{   
	/**
	 * Just a single method to get the full log.
	 * @return all entries in this list.
	 */
    @Override 
    public ArrayList<String> getFullLog()
    {
        ArrayList<BattleLog.Entry> entries = getEntries();
        ArrayList<String> log = new ArrayList<>();
        String targetName;
        String casterName;
        String effectName;
        for (BattleLog.Entry e: entries)
        {
            StringBuilder logEntry = new StringBuilder();
            Object[] contents = e.getContents();
            // System.out.println(Arrays.toString(contents));
            // System.out.println(e.getType());
            switch (e.getType())
            {
                case ATTACK_STATUS:
                    targetName = (String) contents[0];
                    effectName = (String) contents[1];                    
                    logEntry.append("* ")
                            .append(targetName)
                            .append(" was also afflicted with ")
                            .append(effectName);
                    break;

                case ATTACK:
                    targetName = (String) contents[0];
                    Integer healthLost = (Integer) contents[1];
                    Integer healthRemaining = (Integer) contents[2];
                    Integer shieldLost = (Integer) contents[3];
                    Integer shieldRemaining = (Integer) contents[4];
                   
                    logEntry.append("* ")
                            .append(targetName)
                            .append(" lost ");
                    if (healthLost == 0 && shieldLost == 0)
                    {
                        logEntry.append("no health nor shield");
                    }                    
                    else if (shieldLost != 0)
                    {
                        logEntry.append(shieldLost)
                                .append(" shield (Has ")
                                .append(shieldRemaining)
                                .append(" shield left) ");

                        if (healthLost != 0)
                        {
                            logEntry.append("and ");
                        }
                    }
                    if (healthLost != 0)
                    {
                        logEntry.append(healthLost)
                                .append(" health (Has ")
                                .append(healthRemaining)
                                .append(" health left)");
                    }
                    break;
                
                case SHIELD_TRIGGER:
                    targetName = (String) contents[0];
                    Integer shieldUses = (Integer) contents[1];
                    casterName = (String) contents[2];
                    String shieldEffect = (String) contents[3];
                    String shieldEffectDuo = (String) contents[4];
                    String shieldName = (String) contents[5];
    
                    logEntry.append("* ")
                            .append(targetName)
                            .append("\'s shield ")
                            .append(shieldName)
                            .append(" was triggered");

                    if (shieldUses != -1)
                    {
                        logEntry.append(" (has ")
                                .append(shieldUses)
                                .append(" uses left). ");
                    }
                    else 
                    {
                        logEntry.append(". ");
                    }
                    logEntry.append(casterName)
                            .append(" recieved the effect of ")    
                            .append(shieldEffect)
                            .append(". ");

                    if (shieldEffectDuo != null)
                    {
                        logEntry.append(targetName)
                                .append(" also recieved the effect of ")
                                .append(shieldEffectDuo)
                                .append(".");

                    }
                    // if (reflective)
                    // {
                    //     logEntry.append("The same effect was lost by " + targetName);
                    // }
                    break;

                case INTERRUPTED:
                    BattleLog.Entry.Interruption reason = (BattleLog.Entry.Interruption) contents[0];
                    logEntry.append("## ")
                            .append("The attack was unable to land, thanks to ");

                    switch (reason)
                    {
                        case SHIELD:
                            logEntry.append("a shield.");
                            break;
                        case RANDOM:
                            logEntry.append("poor luck.");
                            break;
                        case NO_CONDITION_MET:
                            Integer amountNeeded = (Integer) contents[1];
                            String type = (String) contents[2];
                            String comparison = (String) contents[3];
                            int equalStart = comparison.indexOf("equal");
                            System.out.println(comparison + equalStart);
                            if (equalStart != -1)
                            {
                                comparison = new StringBuilder(comparison.substring(0, equalStart))
                                            .append("about")
                                            .toString();
                            }
                            logEntry.append("the conditions not being met. (Needed ")
                                .append(comparison)
                                .append(" ")
                                .append(amountNeeded)
                                .append(" ")
                                .append(type)
                                .append(".)");
                            break;
                    }
                    break;
                case OVERCHARGED:
                    logEntry.append("* The ability has now been overcharged, so it can't be used for a while.");
                    break;
                case CLEANSE:
                case EFFECT_REMOVED:
                    targetName = (String) contents[0];
                    String[] effectsRemoved = (String[]) contents[1];
                    
                    logEntry.append("* ")
                            .append(targetName)
                            .append(" lost ");
                    if (effectsRemoved.length == 0)
                    {
                        logEntry.append("no effects.");
                    }
                    else if (effectsRemoved.length == 1)
                    {
                        logEntry.append(effectsRemoved[0]);
                    }
                    else if (effectsRemoved.length == 2)
                    {
                        logEntry.append(effectsRemoved[0])
                                .append(" and ")
                                .append(effectsRemoved[1]);
                    }
                    else 
                    {
                        logEntry.append(effectsRemoved[0])
                                .append(", ");
                        for (int i = 1; i < effectsRemoved.length - 1; i++)
                        {
                            logEntry.append(effectsRemoved[i])
                                    .append(", ");
                        }
                        logEntry.append("and ")
                                .append(effectsRemoved[effectsRemoved.length - 1]);
                    }

                    break;
                
                case DEFENSE:
                    targetName = (String) contents[0];
                    shieldName = (String) contents[1];

                    logEntry.append("* ")
                            .append(targetName)
                            .append(" gained the shield ")
                            .append(shieldName);
                    break;
                
                case PASS:
                    String playerName = (String) contents[0];
                    
                    logEntry.append("\n")
                            .append(playerName)
                            .append(" passed their turn");
                    break;

                case SPAWN:
                    String leaderName = (String) contents[0];
                    String familiarName = (String) contents[1];

                    logEntry.append("* ")
                            .append(familiarName)
                            .append(" was spawned. It will follow ")
                            .append(leaderName);
                    break;
                
                case STATE_CHANGE:
                    targetName = (String) contents[0];
                    String oldStateName = (String) contents[1];
                    String newStateName = (String) contents[2];
                    
                    logEntry.append("* ")
                            .append(targetName)
                            .append("\'s state was changed from ")
                            .append(oldStateName)
                            .append(" to ")
                            .append(newStateName);
                    break;
                
                case EFFECT_APPLIED:
                    targetName = (String) contents[0];
                    effectName = (String) contents[1];
                    logEntry.append("* ")
                            .append(targetName)
                            .append(" was afflicted with ")
                            .append(effectName);
                    break;    

                case ABILITY:
                    logEntry.append("\n");
                    casterName = (String) contents[0];
                    targetName = (String) contents[1];
                    String abilityName = (String) contents[2];
                    Integer otherTargets = (Integer) contents[3];

                    logEntry.append(casterName)
                            .append(" decided to target ")
                            .append(targetName)
                            .append(" with ")
                            .append(abilityName)
                            .append(". ");

                    if (otherTargets == 1)
                    {
                        logEntry.append("1 other was targetted.");
                    }
                    else if (otherTargets == -1)
                    {
                        logEntry.append("Everyone was targetted");
                    }
                    else if (otherTargets != 0)
                    {
                        logEntry.append(otherTargets)
                                .append(" others were targetted.");
                    }

                    break;

                case SHIELD_LOST:
                    targetName = (String) contents[0];
                    String[] shieldsLost = (String[]) contents[1];
                    logEntry.append("* ")
                            .append(targetName)
                            .append(" lost ");
                    if (shieldsLost.length == 0)
                    {
                        logEntry.append("no shields.");
                    }
                    else if (shieldsLost.length == 1)
                    {
                        logEntry.append(shieldsLost[0]);
                    }
                    else if (shieldsLost.length == 2)
                    {
                        logEntry.append(shieldsLost[0])
                                .append(" and ")
                                .append(shieldsLost[1]);
                    }
                    else 
                    {
                        logEntry.append(shieldsLost[0])
                                .append(", ");
                        for (int i = 1; i < shieldsLost.length - 1; i++)
                        {
                            logEntry.append(shieldsLost[i])
                                    .append(", ");
                        }
                        logEntry.append("and ")
                                .append(shieldsLost[shieldsLost.length - 1]);
                    }
                    break;
                    
                case CURRENT_STATE:
                    targetName = (String) contents[0];
                    String stateName = (String) contents[1];
                    
                    logEntry.append("* ")
                            .append(targetName)
                            .append("\' state is ")
                            .append(stateName);
                    break;

                case KNOCKED_OUT:
                    String victim = (String) contents[0];
                    logEntry
                            .append("* ")
                            .append(victim)
                            .append(" has been knocked out.");
                    break;

                case APPLY_EFFECT:
                    String victimName = (String) contents[0];
                    Effect.Type type = (Effect.Type) contents[1];
                    Integer power = (Integer) contents[2];
                    effectName = (String) contents[3];
                    String stackName = (String) contents[4];

                    logEntry
                            .append("* ")
                            .append(victimName)
                            .append("\'s ")
                            .append((type != Effect.Type.STACK)? type.name: stackName);
                    if (type != Effect.Type.DAMAGE && power > 0)
                    {
                        logEntry.append(" increased ");
                    }
                    else 
                    {
                        logEntry.append(" decreased ");
                    }
                    logEntry.append("by ")
                            .append(Math.abs(power))
                            .append(" (caused by ")
                            .append(effectName)
                            .append(")");
                    break;

                case END_OF_TURN:
                    targetName = (String) contents[0];
                    logEntry.append("\n")
                            .append(targetName)
                            .append("\'s end of turn.");

                    break;
                    
                case STAT_DISPLAY:
                    Integer health = (Integer) contents[0];
                    Integer maxHealth = (Integer) contents[1];
                    Integer shield = (Integer) contents[2];
                    Integer attack = (Integer) contents[3];
                    Integer defense = (Integer) contents[4];
                    Integer speed = (Integer) contents[5];
                    
                    logEntry.append("* Has ")
                            .append(health)
                            .append("/")
                            .append(maxHealth)
                            .append(" health, ")
                            .append(shield)
                            .append(" shield, ")
                            .append(attack)
                            .append(" attack, ")
                            .append(defense)
                            .append(" defense, and ")
                            .append(speed)
                            .append(" speed");
                    break;
                case REFLECT:
                	targetName = (String) contents[0];
                	casterName = (String) contents[1];
                	effectName = (String) contents[2];
                    shieldName = (String) contents[3];
                	logEntry.append("* ")
                			.append(effectName)
                			.append(" was reflected from ")
                			.append(targetName)
                			.append(" to ")
                			.append(casterName)
                            .append(". (Caused by ")
                            .append(contents[3])
                            .append(".)");
                    break;
                case REDUCE_AMOUNT:
                    Integer amount = (Integer) contents[0];
                    String name = (String) contents[1];
                    logEntry.append("* ")
                        .append(amount)
                        .append(" ")
                        .append(name)
                        .append(" was consumed.");
                    
            }
            log.add(log.size(), logEntry.toString());
        }

        return log;
    }

  
}