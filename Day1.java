import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Day1 
{

    public static int count0_1 = 0;
    public static void run() throws IOException
    {
        Scanner chop;
        try
        {
            File txt = new File("DaysFiles/DayOne.txt");
            chop = new Scanner(txt);
            partOne(chop);

            chop = new Scanner(txt);
            count0_1 = 0;
            System.out.println(partTwo(chop));

            chop.close();
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
        count0_1 += changer / 100;
        changer %= 100;
        if(adding)
        {
            if(pointer + changer > 99)
            {
                count0_1++;
                return (pointer + changer) - 100;
            }
            return pointer + changer;
        }else
        {
            if(pointer - changer < 0)
            {
                count0_1++;
                return 100 + (pointer - changer);
            }
            return pointer - changer;
        }

    }

    public static int partTwo(Scanner chop)
    {
        int pointer = 50; // always starts at 50
        while(chop.hasNext())
        {
            String temp = chop.next();

            boolean isAdding = temp.charAt(0) == 'R';

            int changer = Integer.parseInt(temp.substring(1));

            count0_1 += changer / 100;
            changer %= 100;
            for(int i = 0; i < changer; i++)
            {
                pointer = isAdding ? pointer + 1 : pointer - 1;

                if(pointer == -1)
                {
                    pointer = 99;
                }
                else if(pointer == 100)
                {
                    pointer = 0;
                }

                if(pointer == 0)
                {
                    count0_1 ++;
                }
                    
            }
        }

        return count0_1;
    }
}
