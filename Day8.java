import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        HashMap<String, ArrayList<int[]>> connected = new HashMap<>();
        
        while(chop.hasNextLine())
        {
            String line = chop.nextLine();
            
            String[] temp = line.split(",");
            
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            int z = Integer.parseInt(temp[2]);
            
            circuits.add(new ArrayList<int[]>(Arrays.asList(new int[]{x, y, z})));

            connected.put(x + " " + y + " " + z, new ArrayList<int[]>(Arrays.asList(new int[]{x, y, z})));
        }
        
        //Big(O) forgive me for what I'm about to do
        for(int count = 1; count <= 1000; count++)
        {
            double distance = Double.MAX_VALUE; // aiming for lowest
            int circuitIndexOne = -1;
            int circuitIndexTwo = -1;

            int boxIndexOne = -1;
            int boxIndexTwo = -1;
            
            int oneIndex = -1;
            for(ArrayList<int[]> circuitOne : circuits)
            {
                oneIndex++;
                int twoIndex = -1; // 75680
                for(ArrayList<int[]> circuitTwo : circuits)
                {
                    twoIndex++;
                    if(circuitOne.equals(circuitTwo)) // dont compare same list
                    {
                        continue;
                    }
                    
                    int boxOneIndex = -1;
                    for(int[] boxOne : circuitOne)
                    {
                        boxOneIndex++;
                        int x, y, z;
                        x = boxOne[0];
                        y = boxOne[1];
                        z = boxOne[2];

                        String keyOne = x + " " + y + " " + z;

                        int boxTwoIndex = -1;
                        for(int[] boxTwo : circuitOne)
                        {
                            
                            boxTwoIndex++;
                            if(Arrays.equals(boxOne, boxTwo))
                            {
                                continue;
                            }

                            int xi, yi, zi;
                            xi = boxTwo[0];
                            yi = boxTwo[1];
                            zi = boxTwo[2];

                            String keyTwo = xi + " " + yi + " " + zi;

                            ArrayList<int[]> valueOne = connected.get(keyOne);
                            ArrayList<int[]> valueTwo = connected.get(keyTwo);

                            boolean skip = false;

                            skip = valueOne.indexOf(boxTwo) != -1 || valueTwo.indexOf(boxOne) != -1;

                            if(skip)
                            {
                                continue;
                            }
                            
                            // distance formula
                            double math = Math.sqrt( Math.pow(xi - x,2) + Math.pow(yi - y,2) + Math.pow(zi - z,2) );
                            if(math < distance)
                            {
                                distance = math;
                                circuitIndexOne = oneIndex;
                                circuitIndexTwo = oneIndex;
                                boxIndexOne = boxOneIndex;
                                boxIndexTwo = boxTwoIndex;
                            }
                        }

                        boxTwoIndex = -1;
                        for(int[] boxTwo : circuitTwo)
                        {
                            boxTwoIndex++;
                            int xi, yi, zi;
                            xi = boxTwo[0];
                            yi = boxTwo[1];
                            zi = boxTwo[2];

                            String keyTwo = xi + " " + yi + " " + zi;

                            ArrayList<int[]> valueOne = connected.get(keyOne);
                            ArrayList<int[]> valueTwo = connected.get(keyTwo);

                            boolean skip = false;

                            skip = valueOne.indexOf(boxTwo) != -1 || valueTwo.indexOf(boxOne) != -1;

                            if(skip)
                            {
                                continue;
                            }
                            
                            // distance formula
                            double math = Math.sqrt( Math.pow(xi - x,2) + Math.pow(yi - y,2) + Math.pow(zi - z,2) );
                            if(math < distance)
                            {
                                distance = math;
                                circuitIndexOne = oneIndex;
                                circuitIndexTwo = twoIndex;
                                boxIndexOne = boxOneIndex;
                                boxIndexTwo = boxTwoIndex;
                            }
                        }
                    }
                }
            }

            int[] boxOne = circuits.get(circuitIndexOne).get(boxIndexOne);
            int[] boxTwo = circuits.get(circuitIndexTwo).get(boxIndexTwo);

            String keyOne = boxOne[0] + " " + boxOne[1] + " " + boxOne[2];
            String keyTwo = boxTwo[0] + " " + boxTwo[1] + " " + boxTwo[2];

            connected.get(keyOne).add(boxTwo);
            connected.get(keyTwo).add(boxOne);




            if(circuitIndexOne == circuitIndexTwo)
            {
                continue;
            }

            circuits.get(circuitIndexOne).addAll(circuits.remove(circuitIndexTwo));
        }
        
        long[] max = new long[]{Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        for(ArrayList<int[]> circuit : circuits)
        {
            if(circuit.size() > max[0])
            {
                max[2] = max[1];
                max[1] = max[0];
                max[0] = circuit.size();

            }else if(circuit.size() > max[1])
            {
                max[2] = max[1];
                max[1] = circuit.size();
            }else if(circuit.size() > max[2])
            {
                max[2] = circuit.size();
            }
        }
        System.out.println();
        for(int i = 0; i < 3; i++)
        {
            System.out.print(max[i] + " ");
        }

        System.out.println(max[0] * max[1] * max[2]);
    }
}