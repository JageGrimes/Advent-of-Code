import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
public class Day6 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DaySix.txt");
            
            Scanner chop = new Scanner(txt);
            
            //partOne(chop);
            partTwo(chop);
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        ArrayList<ArrayList<Long>> nums = new ArrayList<ArrayList<Long>>();
        ArrayList<Character> operations = new ArrayList<Character>();
        
        int temp = 0;
        while(chop.hasNextLine())
        {
            
            Scanner chopper = new Scanner(chop.nextLine());
            
            int i = 0;
            while(chopper.hasNextLong())
            {
                if(temp == 0)
                {
                    nums.add(new ArrayList<Long>());
                }
                nums.get(i).add(chopper.nextLong());
                i++;
            }
            temp ++;
            
            
            while(chopper.hasNext())
            {
                operations.add(chopper.next().charAt(0));
            }
            chopper.close();
        }
        

        long total = 0;
        
        for(int i = 0; i < nums.size(); i++)
        {

            switch(operations.get(i))
            {
                case '+' : total += (nums.get(i).get(0) + nums.get(i).get(1) + nums.get(i).get(2) + nums.get(i).get(3));
                    break;
                case '*' : total += (nums.get(i).get(0) * nums.get(i).get(1) * nums.get(i).get(2) * nums.get(i).get(3));
                    break;
            }
        }
        System.out.println(total);
    }
    
    public static void partTwo(Scanner chop)
    {
        String[] lines = new String[5];
        
        int i = 0;
        while(chop.hasNextLine())
        {
            lines[i] = chop.nextLine();
            i++;
        }
        
        chop = new Scanner(lines[lines.length - 1]);
        
        ArrayList<Character> operations = new ArrayList<>();
        
        while(chop.hasNext())
        {
            operations.add(chop.next().charAt(0));
        }

        chop.close();
        
        Object[][] oper_length = new Object[operations.size()][lines.length - 1];
        
        for(i = 0; i < oper_length.length; i++)
        {
            oper_length[i][0] = operations.get(i);
        }
        
        String temp = lines[lines.length - 1];
        
        temp = temp.replace('*', '+'); // make using indexOf easier; the operations are stored else where and this string is not used for them
        
        int start = 0;
        int end = 0;
        
        int index = 0;
        
        do
        {
            if(temp.indexOf("+") == temp.lastIndexOf("+"))
            {
                start = temp.indexOf("+");
                
                end = temp.length();
            }else
            {
                start = temp.indexOf("+");
                
                temp = temp.replaceFirst("\\+", " ");
                
                end = temp.indexOf("+");
            }
            
            oper_length[index][1] = start;
            oper_length[index][2] = end;
            
            index++;
            
        }while(end < temp.length());
        
        ArrayList<ArrayList<char[]>> numbers = new ArrayList<ArrayList<char[]>>();
        
        for(i = 0; i < lines.length - 1; i++)
        {
            String line = lines[i];
            for(int count = 0; count < oper_length.length; count++)
            {
                start = (int)oper_length[count][1];
                end = (int)oper_length[count][2];
                
                //if(!(end > line.length()))
                {
                    end--;
                }
                
                char[] tempArr = line.substring(start, end).toCharArray();
                
                if(i == 0)
                {
                    numbers.add(new ArrayList<char[]>());
                }
                
                numbers.get(count).add(tempArr);
            }
        }
        
        long total = 0;
        
        for(i = 0; i < operations.size(); i++)
        {
            start = (int)oper_length[i][1];
            end = (int)oper_length[i][2];
            
            int size = end - 1 - start;
            
            int[] nums = new int[size];
            
            for(int j = size; j > 0; j--)
            {
                String combined = "";
                for(int count = 0; count < numbers.get(i).size(); count++)
                {
                    combined += numbers.get(i).get(count)[j - 1];
                }
                    
                String newStr = "";
                
                for(int count = 0; count < combined.length(); count++)
                {
                    if(!(combined.charAt(count) == ' '))
                    {
                        newStr += combined.charAt(count);
                    }
                }
                combined = newStr;
                
                nums[j - 1] = Integer.parseInt(combined);
            }
            
            long sum = 0;
            switch(operations.get(i))
            {
                case '+' :
                    for(int count = 0; count < nums.length; count++)
                    {
                        sum += nums[count];
                    }
                    break;
                case '*' : 
                    long tempSum = 1;
                    for(int count = 0; count < nums.length; count++)
                    {
                        tempSum *= nums[count];
                    }
                    sum += tempSum;
                    break;
                    
            }
            String newStr = "";
            for(int count = 0; count < nums.length; count++)
            {
                newStr += nums[count] + " ";
            }
            
            System.out.println(i + "\t" + newStr + ":: " + sum);
            
            total += sum;
        }
        
        System.out.println(total); // 11044319474679
                                   // 11044319475191
                                   // 8907730960817
    }
}