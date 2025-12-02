import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Day1 
{
    public static void main(String[] args) throws IOException
    {
        Scanner chop;
        try
        {
            File txt = new File("DaysFiles/DayOne.txt");
            chop = new Scanner(txt);
            partOne(chop);
        }catch(Error e) {}
    }

    public static void partOne(Scanner chop) // gave correct answer
    {
        int count0 = 0;
        int pointer = 50; // always starts at 50
        while(chop.hasNext())
        {
            String temp = chop.next();

            boolean adding = temp.charAt(0) == 'R';

            int changer = Integer.parseInt(temp.substring(1));

            pointer = wrapNum(pointer, changer, adding);

            if(pointer == 0)
                count0++;

            // System.out.println(pointer);
        }

        System.out.println("The dial points at 0 :: " + count0 + " times");
    }

    //helper method
    //chack if the pointer num changed by changer num is over 99 or less then 0, wrap to the opposit side and continue;
    public static int wrapNum(int pointer, int changer, boolean adding)
    {
        // cut down on multiple full rotations
        while(changer > 99 || changer < 0)
        {
            if(changer > 99)
            {
                changer-= 100;
            }
            if(changer < 0)
            {
                changer+= 100;
            }
        }
        if(adding)
        {
            if(pointer + changer > 99)
            {
                return (pointer + changer) - 100;
            }
            return pointer + changer;
        }else
        {
            if(pointer - changer < 0)
            {
                return 100 + (pointer - changer);
            }
            return pointer - changer;
        }

    }
}
