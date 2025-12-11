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
            
            //partOne(chop);
            partTwo(chop);
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        HashMap<String, ArrayList<String>> inOut = new HashMap<>();
        
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
        for(String str : inOut.get("you"))
        {
            total += recPartOne(inOut, inOut.get(str), str);
        }
        
        System.out.println(total);
    }
    
    public static int recPartOne(HashMap<String, ArrayList<String>> inOut, ArrayList<String> out, String in)
    {
        if(in.equals("out"))
        {
            return 1;
        }
        int sum = 0;
        for(String str : out)
        {
            sum += recPartOne(inOut, inOut.get(str), str);
        }
        
        return sum;
    }
    
    public static void partTwo(Scanner chop)
    {
        HashMap<String, ArrayList<String>> inOut = new HashMap<>();

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
        for(String str : inOut.get("svr"))
        {
            total += recPartTwo(inOut, inOut.get(str), str, false, false);
        }

        System.out.println(total);
    }

    public static int recPartTwo(HashMap<String, ArrayList<String>> inOut, ArrayList<String> out, String in, boolean dac, boolean fft)
    {
        if(in.equals("out") && dac && fft)
        {
            return 1;
        }

        int sum = 0;
        for(String str : out)
        {
            if(str.equals("dac"))
            {
                dac = true;
            }else if(str.equals("fft"))
            {
                fft = true;
            }

            System.out.println(str);
            
            sum += recPartTwo(inOut, inOut.get(str), str, dac, fft);
        }
        return sum;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}