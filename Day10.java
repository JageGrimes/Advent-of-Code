import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
public class Day10
{
    public static int pressCount = Integer.MAX_VALUE;
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayTen.txt");
            
            Scanner chop = new Scanner(txt);
            
            partOne(chop);
            //partTwo(chop);
            
            chop.close();
        }catch(Error e) {System.out.println(e);}
    }
    
    public static void partOne(Scanner chop)
    {
        HashMap<String, ArrayList<String>> list = new HashMap<>();
        
        while(chop.hasNext())
        {
            String line = chop.nextLine();
            
            Scanner chopper = new Scanner(line);
            
            String machine = chopper.next();
            
            ArrayList<String> buttons = new ArrayList<>();
            while(chopper.hasNext())
            {
                buttons.add(chopper.next());
            }
            
            buttons.remove(buttons.size() - 1);
            
            list.put(machine, buttons);
            
            chopper.close();
        }
        
        int total = 0;
        for(String machine : list.keySet())
        {
            recPartOne(machine, list.get(machine), 0);
            total += pressCount;
            pressCount = Integer.MAX_VALUE;
        }
        
        System.out.println(total);
    }

    public static void recPartOne(String machine, ArrayList<String> buttons, int count)
    {
        if(count > pressCount && pressCount != Integer.MAX_VALUE)
        {
           return;
        }

        if(machine.indexOf(".") == -1)
        {
            pressCount = Math.min(pressCount, count);
            return;
        }

        ArrayList<String> newButtons = new ArrayList<>();

        for(int i = 0; i < buttons.size(); i++)
        {
            newButtons.addAll(buttons);
            newButtons.remove(i);
            recPartOne(press(machine, buttons.get(i)), newButtons, count + 1);
            newButtons.clear();
        }
        
    }
    //helper method
    public static String press(String machine, String button)
    {
        button = button.substring(1, button.length() - 1); // remove ()
        
        String[] nums = button.split(",");
        
        char[] temp = machine.toCharArray();
        
        for(String num : nums)
        {
            int i = Integer.parseInt(num) + 1;
            if(temp[i] == '.')
            {
                temp[i] = '#';
            }else
            {
                temp[i] = '.';
            }
        }
        
        machine = "";
        
        for(int i = 0; i < temp.length; i++)
        {
            machine += temp[i];
        }
        
        return machine;
    }
}