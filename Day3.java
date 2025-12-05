import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class Day3 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayThree.txt");

            Scanner chop = new Scanner(txt);

            //only one at a time
            //partOne(chop);
            partTwo(chop);
        }catch(Error e){}
    }

    public static void partOne(Scanner chop)
    {
        int totalPower = 0;
        while(chop.hasNext())
        {
            String line = chop.next();
            
            String[] nums = line.split("");
            
            int max = Integer.MIN_VALUE;
            int maxIndex = 999;
            for(int i = 0; i < nums.length; i++)
            {
                int num = Integer.parseInt(nums[i]);
                
                if(max == 9)
                {
                    break;
                }
                
                
                if(num > max && i != nums.length - 1)
                {
                    maxIndex = i;
                    max = num;
                }
            }
            
            int maxTwo = nextBiggest(maxIndex + 1, nums);
            
            totalPower += (max * 10 + maxTwo);
        }
        System.out.println(totalPower);
    }
    
    public static int nextBiggest(int start, String[] nums)
    {
        int max = Integer.MIN_VALUE;
        for(int i = start; i < nums.length; i++)
        {
            int num = Integer.parseInt(nums[i]);
            
            if(max == 9)
            {
                break;
            }
            
            if(num > max)
            {
                max = num;
            }
        }
        return max;
    }
    public static void partTwo(Scanner chop)
    {
        long totalJoltage = 0;
        while(chop.hasNext())
        {
            String line = chop.next();
            
            String[] nums = line.split("");
            
            int[] joltage = new int[12];
            
            int maxIndex = -1;
            for(int i = 11; i >= 0; i--) 
            {
                int[] max_index = nextBiggestPartTwo(maxIndex + 1, i, nums);
                joltage[i] = max_index[0];
                maxIndex = max_index[1];
            }
            
            String temp = "";
            for(int i = 11; i >= 0; i--)
            {
                temp += joltage[i];
            }
            //System.out.println(temp);
            
            totalJoltage += Long.parseLong(temp);
        }
        System.out.println(totalJoltage);
    }

    public static int[] nextBiggestPartTwo(int start, int nthMax, String[] nums)
    {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int i = start; i < nums.length - nthMax; i++)
        {
            int num = Integer.parseInt(nums[i]);
            
            if(num > max)
            {
                max = num;
                maxIndex = i;
            }
            
            if(max == 9)
            {
                break;
            }
        }
        
        int[] tempArr = new int[2];
        tempArr[0] = max;
        tempArr[1] = maxIndex;
        return tempArr;
    }
}
