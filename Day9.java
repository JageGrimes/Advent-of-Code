import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
public class Day9 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayNine.txt");
            
            Scanner chop = new Scanner(txt);
            
            partOne(chop);
            //partTwo(chop);
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        ArrayList<int[]> points = new ArrayList<>();
        while(chop.hasNextLine())
        {
            String[] line = chop.nextLine().split(",");
            
            int[] temp = new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])};
            
            points.add(temp);
        }
        
        ArrayList<Long> areas = new ArrayList<>();
        int[] one, two;
        one = new int[2];
        two = new int[2];
        
        double distance = Double.MIN_VALUE;
        for(int i = 0; i < points.size() - 1; i++)
        {
            int[] pointOne = points.get(i);
            
            int x = pointOne[0];
            int y = pointOne[1];
            for(int j = i + 1; j < points.size(); j++)
            {
                int[] pointTwo = points.get(j);
                
                int xi = pointTwo[0];
                int yi = pointTwo[1];
                
                double math = Math.sqrt( Math.pow(x - xi,2) + Math.pow(y - yi,2) );
                
                if(math > distance)
                {
                    one = pointOne;
                    two = pointTwo;
                    distance = math;
                }
            }
        }
        
        System.out.println(Arrays.toString(one) + " " + Arrays.toString(two));
        
        long length = Math.abs(one[0] - two[0]) + 1;
        long height = Math.abs(one[1] - two[1]) + 1;
        
        System.out.println(length * height); // 4735222687
    }
}