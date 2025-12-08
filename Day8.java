import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
public class Day8 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayEight.txt");
            
            Scanner chop = new Scanner(txt);
            
            partOne(chop);
            //partTwo(chop);
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        ArrayList<ArrayList<int[]>> circuits = new ArrayList<>();
        int boxCount = 0;
        
        while(chop.hasNextLine())
        {
            String line = chop.nextLine();
            
            String[] temp = line.split(",");
            
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            int z = Integer.parseInt(temp[2]);
            
            circuits.add(new ArrayList<int[]>(Arrays.asList(new int[]{x, y, z})));
            
            boxCount++;
        }
        
        //Big(O) forgive me for what I'm about to do
        for(int count = 1; count <= 10; count++)
        {
            ArrayList<int[]> tempCircuit = new ArrayList<int[]>(); // for combining circuits
            
            double distance = Double.MAX_VALUE; // aiming for lowest
            int circuitIndexOne = -1;
            int circuitIndexTwo = -1;
            
            int oneIndex = 0;
            for(ArrayList<int[]> circuitOne : circuits)
            {
                int twoIndex = 0;
                for(ArrayList<int[]> circuitTwo : circuits)
                {
                    if(circuitOne.equals(circuitTwo)) // dont compare same list
                    {
                        twoIndex++;
                        continue;
                    }
                    
                    for(int[] boxOne : circuitOne)
                    {
                        int x, y, z;
                        x = boxOne[0];
                        y = boxOne[1];
                        z = boxOne[2];
                        for(int[] boxTwo : circuitTwo)
                        {
                            int xi, yi, zi;
                            xi = boxTwo[0];
                            yi = boxTwo[1];
                            zi = boxTwo[2];
                            
                            // distance formula
                            double math = Math.sqrt( Math.pow(x - xi,2) + Math.pow(y - yi,2) + Math.pow(z - zi,2) );
                            if(math < distance)
                            {
                                distance = math;
                                circuitIndexOne = oneIndex;
                                circuitIndexTwo = twoIndex;
                            }
                        }
                    }
                    twoIndex++;
                }
                oneIndex++;
            }
            
            circuits.get(circuitIndexOne).addAll(circuits.remove(circuitIndexTwo));
        }
        
        int[] max = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(ArrayList<int[]> circuit : circuits)
        {
            if(circuit.size() > max[0])
            {
                max[0] = circuit.size();
            }else if(circuit.size() > max[1])
            {
                max[1] = circuit.size();
            }else if(circuit.size() > max[2])
            {
                max[2] = circuit.size();
            }
        }
        
        System.out.println(max[0] + " " + max[1] + " " + max[2]);
    }
}