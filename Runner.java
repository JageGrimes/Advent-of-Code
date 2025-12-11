import java.io.IOException;

public class Runner 
{
    public static void main(String[] args) throws IOException 
    {
        long startTime = System.currentTimeMillis();
        //test();

        try
        {
            //Day1.run();
            //Day2.run();
            //Day3.run();
            //Day4.run();
            //Day5.run();
            //Day6.run();
            //Day7.run();
            //Day8.run();
            //Day9.run();
            //Day10.run();
            Day11.run();
        }
        catch(Error e) {}

        long endTime = System.currentTimeMillis();

        System.out.println("Time it took in mili: " + (endTime - startTime));
    }
}
