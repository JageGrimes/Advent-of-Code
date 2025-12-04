import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;
public class Day2 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayTwo.txt");

            Scanner chop = new Scanner(txt);

            //partOne(chop);
            partTwo(chop);

            chop.close();
        }catch(Error e) {}
    }

    public static void partOne(Scanner chop)
    {
        String line = chop.next();

        String[] ranges = line.split(",");

        long invalidNums = 0l;

        for(String range : ranges)
        {
            long min = Long.parseLong(range.substring(0, range.indexOf("-")));
            long max = Long.parseLong(range.substring(range.indexOf("-") + 1));

            for(long i = min; i <= max; i++) // check all num in range inclusive
            {
                String IDNum = i + "";
                if(IDNum.length() % 2 != 0)
                {
                    continue;
                }

                String regex = "(" + IDNum.substring(0, IDNum.length()/2) + "){2}";

                if(Pattern.matches(regex, IDNum))
                {
                    invalidNums += Long.parseLong(IDNum);
                }

            }
        }
        System.out.println(invalidNums);
    }

    public static void partTwo(Scanner chop)
    {
        String line = chop.next();
        
        String[] ranges = line.split(",");
        
        long invalidNums = 0l;
        
        for(String range : ranges)
        {
        
            long min = Long.parseLong(range.substring(0, range.indexOf("-")));
            long max = Long.parseLong(range.substring(range.indexOf("-") + 1));
            
            for(long i = min; i <= max; i++) // check all num in range inclusive
            {
                String IDNum = i + "";
                
                for(int count = 1; count < IDNum.length() / 2 + 1; count++)
                {
                    if(IDNum.length() % count != 0) // only check if divisible by count
                    {
                        continue;
                    }
                    
                    int amount = IDNum.length() / count; // howmany times the regex need to be added together to math origional string
                    
                    if(!(amount >= 2))
                    {
                        continue;
                    }
                    
                    String temp = IDNum.substring(0, count);
                    String regex = "(" + temp + "){" + amount + "}";
                    
                    if(Pattern.matches(regex, IDNum))
                    {
                        invalidNums += i;
                        break;
                    }
                }
            }
        }
        System.out.println(invalidNums);
        //18724308646 big text
        //3349553331 small text
    }
}
