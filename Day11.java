import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
public class Day11
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayEleven.txt");
            
            Scanner chop = new Scanner(txt);
            
            partOne(chop);
            //partTwo(chop);
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        HashMap<String, ArrayList<String>> inOut = new HashMap<>();
        HashMap<String, Boolean> tracked = new HashMap<>();
        
        while(chop.hasNextLine())
        {
            String line = chop.nextLine();
            
            Scanner chopper = new Scanner(line);
            
            String in = chopper.next();
            in = in.substring(0, in.length() - 1);
            
            ArrayList<String> out = new ArrayList<>();
            
            while(chopper.hasNext())
            {
                out.add(chopper.next());
            }
            
            inOut.put(in, out);
            chopper.close();
        }
        
        int total = 0;
        for(String key : inOut.keySet())
        {
            tracked.put(key, true);
            recPartOne(inOut, tracked, inOut.get(key), key);
        }
        
        System.out.println(total);
    }
    
    public static int recPartOne(HashMap<String, ArrayList<String>> inOut, HashMap<String, Boolean> tracked, ArrayList<String> out, String in)
    {
        if(in.equals("out"))
        {
            return 1;
        }
        int sum = 0;
        for(String str : out)
        {
            if(tracked.containsKey(str))
            {
                continue;
            }
            sum += recPartOne(inOut, tracked, inOut.get(str), str);
        }
        
        return sum;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}