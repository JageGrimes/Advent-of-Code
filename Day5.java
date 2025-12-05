import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
public class Day5 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayFive.txt");
            
            Scanner chop = new Scanner(txt);
            
            //partOne(chop);

            Scanner chopper = new Scanner(new File("DaysFiles/DayFiveTwo.txt"));

            //partTwo(chopper);
            
            chop.close();
            chopper.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        String rangeTemp = "";
        String foodTemp = "";
        
        // Seperate ranges and food ID into two strings to be .split(" ") later
        while(chop.hasNext())
        {
            String line = chop.next();
            
            if(line.indexOf("-") != -1)
            {
                rangeTemp += line + " ";
            }else
            {
                foodTemp += line + " ";
            }
        }
        
        String[] ranges = rangeTemp.split(" ");
        String[] foods = foodTemp.split(" ");
        
        int count = 0;
        for(String food : foods)
        {
            long ID = Long.parseLong(food);
            for(String range : ranges)
            {
                long min = Long.parseLong(range.substring(0, range.indexOf("-")));
                long max = Long.parseLong(range.substring(range.indexOf("-") + 1));
            
                if(min <= ID && ID <= max)
                {
                    count++;
                    break;
                }
            }
        }
        
        System.out.println(count);
        
    }
    
    public static void partTwo(Scanner chop)
    {
        ArrayList<String> ranges = new ArrayList<>();
        while(chop.hasNext())
        {
            String line = chop.next(); //343329651880509
            
            ranges.add(line);
            
        }
        
        long[][] temp = new long[ranges.size()][2];
        
        int i = 0;
        for(String range : ranges)
        {
            long min = Long.parseLong(range.substring(0, range.indexOf("-")));
            long max = Long.parseLong(range.substring(range.indexOf("-") + 1));
            long[] tempArr = {min, max};
            temp[i] = tempArr;
            i++;
        }
        
        ArrayList<long[]>newRanges = mergeOverlap(temp);
        
        long count = 0;
        for(long[] minMax : newRanges)
        {
            count += (minMax[1] - minMax[0]) + 1;
        }
        
        System.out.println(count);
    }
    
    public static ArrayList<long[]> mergeOverlap(long[][] ranges) 
    {
        // Sort intervals based on start values
        Arrays.sort(ranges, (a, b) -> Long.compare(a[0], b[0]));

        ArrayList<long[]> res = new ArrayList<>();
        res.add(new long[]{ranges[0][0], ranges[0][1]});

        for (int i = 1; i < ranges.length; i++) 
        {
            long[] old = res.get(res.size() - 1);
            long[] current = ranges[i];

            // If current interval overlaps with the last merged interval = merge
            if (current[0] <= old[1])
            {
                old[1] = Math.max(old[1], current[1]);
            }
            else
            {
                res.add(new long[]{current[0], current[1]});
            }
        }

        return res;
    }
    
}