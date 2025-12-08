import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
public class Day7 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DaySeven.txt");
            
            Scanner chop = new Scanner(txt);
            
            //partOne(chop);
            partTwo(chop); // 1712387603
            
            chop.close();
        }catch(Error e) {}
    }
    
    public static void partOne(Scanner chop)
    {
        
        ArrayList<char[]> grid = new ArrayList<>(); // 2D char array
        
        while(chop.hasNextLine())
        {
            grid.add(chop.nextLine().toCharArray());
        }
        
        int row = 1; // belowthe S
        int col = grid.get(0).length / 2; // S is in the middle
        
        boolean hasReachedBottom = false;
        
        HashMap<int[], Integer> tachLoc = new HashMap<>(); //store all line col/row locations in int[]; doesn't store multiples
        
        tachLoc.put(new int[]{row, col}, 1);
        int countSplit = 0;
        do // move each line down until reaching the bottom
        {
            HashMap<int[], Integer> temp = new HashMap<>();
            for(int[] tach : tachLoc.keySet())
            {
                char below = grid.get(tach[0] + 1)[tach[1]];
                
                if(below == '|') // merge
                    continue;
                
                if(below == '^') //split
                {
                    countSplit++;
                    grid.get(tach[0] + 1)[tach[1] - 1] = '|';
                    grid.get(tach[0] + 1)[tach[1] + 1] = '|';
                    
                    temp.put(new int[]{(tach[0] + 1), (tach[1] - 1)}, 1);
                    temp.put(new int[]{(tach[0] + 1), (tach[1] + 1)}, 1);
                    
                    continue;
                }
                
                grid.get(tach[0] + 1)[tach[1]] = '|';
                temp.put(new int[]{(tach[0] + 1), (tach[1])}, 1);
            }
            row++;
            hasReachedBottom = (row == grid.get(0).length - 1);
            
            tachLoc = temp;
            
        }while(!hasReachedBottom);
        
        System.out.println(countSplit);
    }
    
    public static void partTwo(Scanner chop)
    {
        ArrayList<char[]> grid = new ArrayList<>(); // 2D char array
        
        while(chop.hasNextLine())
        {
            grid.add(chop.nextLine().toCharArray());
        }
        
        int row = 1; // belowthe S
        int col = grid.get(0).length / 2; // S is in the middle
        
        boolean hasReachedBottom = false;
        
        //Changed from int[] to ArrayList due to overiding key hashset in hasmap
        HashMap<ArrayList<Integer>, Long> tachLoc = new HashMap<>(); //store all line col/row locations in int[]; doesn't store multiples
        
        tachLoc.put(new ArrayList<Integer>(Arrays.asList(row, col)), 1l);
        do // move each line down until reaching the bottom
        {
            HashMap<ArrayList<Integer>, Long> temp = new HashMap<>();
            for(ArrayList<Integer> tach : tachLoc.keySet())
            {
                char below = grid.get(tach.get(0) + 1)[tach.get(1)];
                
                
                if(below == '|') // merge
                {
                    temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1))), temp.get(Arrays.asList(tach.get(0) + 1, tach.get(1))) + tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                }else if(below == '^') //split
                {
                    if(grid.get(tach.get(0) + 1)[tach.get(1) - 1] == '.') // spot on the left is empty
                    {
                        grid.get(tach.get(0) + 1)[tach.get(1) - 1] = '|';
                        temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1) - 1)), tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                    }else
                    {
                        temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1) - 1)), temp.get(Arrays.asList(tach.get(0) + 1, tach.get(1) - 1)) + tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                    }
                    
                    if(grid.get(tach.get(0) + 1)[tach.get(1) + 1] == '.') // spot on the right is empty
                    {
                        grid.get(tach.get(0) + 1)[tach.get(1) + 1] = '|';
                        temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1) + 1)), tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                    }else
                    {
                        temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1) + 1)), temp.get(Arrays.asList(tach.get(0) + 1, tach.get(1) + 1)) + tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                    }
                }else
                {
                    //else add the current line to the line below it
                    grid.get(tach.get(0) + 1)[tach.get(1)] = '|';
                    temp.put(new ArrayList<Integer>(Arrays.asList(tach.get(0) + 1, tach.get(1))), tachLoc.get(Arrays.asList(tach.get(0), tach.get(1))));
                }
            }
            row++;
            hasReachedBottom = (row == grid.size() - 1);
            
            tachLoc = temp;
            
        }while(!hasReachedBottom);
        
        long count = 0;
        for(ArrayList<Integer> key : tachLoc.keySet())
        {
            if(key.get(0) == grid.size() - 1)
            {
                count += tachLoc.get(key);
            }
        }
        
        System.out.println(count);
    }
}