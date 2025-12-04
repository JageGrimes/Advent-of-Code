import java.io.IOException;
import java.util.regex.*;

public class Runner 
{
    public static void main(String[] args) throws IOException 
    {
        //test();

        try
        {
            //Day1.run();
            Day2.run();
        }
        catch(Error e)
        {

        }
    }

    public static void test()
    {
        String temp = "123";
        int amount = 3;

        String regex = "(" + temp + "){" + amount + "}"; 

        String ID = "123123123";
        System.out.println(Pattern.matches(regex, ID));
    }
}
