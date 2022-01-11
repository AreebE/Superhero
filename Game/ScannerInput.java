package battlesystem;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

//very wip i dont even know if i will use this...
class ScannerInput {
        private Scanner inputReader;
        private Entity target;
        public ScannerInput(Scanner inputReader)
        {
            this.inputReader = inputReader;
        }
        /*

        @Override
        public String getAbilityName()
        {
            System.out.println("Which ability to use?");
            return inputReader.next();
        }        
        
        @Override
        public Entity getSingleTarget()
        {
            System.out.println("Who to target?");
            String name = inputReader.next();
            Entity target = getEntity(name, Players);
            while (target == null){
                System.out.println("No target specified.");
                name = inputReader.next();
                target = getEntity(name, Players);
            }
            this.target = target;
            return target;
        }

        @Override
        public List<Entity> getSecondaryTargets(Integer limit)
        {
            ArrayList<Entity> otherTargets = new ArrayList<>();
            if (limit == -1)
            {
                for (int i = 0; i < Players.size(); i++)
                {
                    if (!Players.get(i).equals(currentPlayer) && !Players.get(i).equals(target))
                    {
                        otherTargets.add(Players.get(i));
                    }
                }
            }
            for (int i = 0; i < limit && otherTargets.size() < Players.size() - 1; i++ )
            {
                System.out.println("Who else to target?");
                String name = inputReader.next();
                Entity target = getEntity(name, Players);
                while (target == null && otherTargets.contains(target))
                {
                    System.out.println("No target specified.");
                    name = inputReader.next();
                    if (name.toLowerCase().equals("pass")){
                        return null;
                    }
                    target = getEntity(name, Players);
                }
                otherTargets.add(target);
            }
            return otherTargets;
        }*/
    }