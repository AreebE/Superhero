package battlesystem;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

// very wip i dont even know if i will use this...
/*
class ScannerInput implements InputSystem{
        private Scanner inputReader;
        private Entity target;
        private ArrayList<Entity> players;

        public ScannerInput(Scanner inputReader, ArrayList<Entity> players)
        {
            this.inputReader = inputReader;
            this.players = players;
        }
        

        @Override
        public String getAbilityName()
        {
            System.out.println("Which ability to use?");
            return inputReader.next();
        }        
        
        @Override
        public Entity getSingleTarget()
        {
            System.out.println("Who to target? e");
            String name = inputReader.next();
            Entity target = getEntity(name);
            while (target == null){
                System.out.println("No target specified.");
                name = inputReader.next();
                target = getEntity(name);
            }
            this.target = target;
            return target;
        }

        @Override
        public List<Entity> getSecondaryTargets(Integer limit, Entity caster)
        {
            ArrayList<Entity> otherTargets = new ArrayList<>();
            if (limit == -1)
            {
                for (int i = 0; i < players.size(); i++)
                {
                    if (!players.get(i).equals(caster) && !players.get(i).equals(target))
                    {
                        otherTargets.add(players.get(i));
                    }
                }
            }
            for (int i = 0; i < limit && otherTargets.size() < players.size() - 1; i++ )
            {
                System.out.println("Who else to target?");
                String name = inputReader.next();
                Entity target = getEntity(name);
                while (target == null && otherTargets.contains(target))
                {
                    System.out.println("No target specified.");
                    name = inputReader.next();
                    if (name.toLowerCase().equals("pass")){
                        return null;
                    }
                    target = getEntity(name);
                }
                otherTargets.add(target);
            }
            return otherTargets;
        }

        private Entity getEntity(String name)
        {
            for (Entity e: players)
            {
                if (e.getName().equals(name))
                {
                    return e;
                }
            }
            return null;
        }
    }*/