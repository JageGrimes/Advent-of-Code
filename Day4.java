import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class Day4 
{
    public static void run() throws IOException
    {
        try
        {
            File txt = new File("DaysFiles/DayFour.txt");
            
            Scanner chop = new Scanner(txt);
            
            partOne(chop);
            //partTwo(chop);
            
            chop.close();
            
        }catch (Error e){}
    }
    
    public static void partOne(Scanner chop)
    {
        String str = chop.next();
        
        char[][] grid = new char[str.length()][str.length()];
        
        grid[0] = str.toCharArray();
        
        int i = 1;
        
        while(chop.hasNext())
        {
            char[] line = chop.next().toCharArray();
            grid[i] = line;
            i++;
        }
        
        int TP = 0;
        
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                if(grid[row][col] == '@')
                if(checkSurounding(grid, row, col) < 4)
                {
                    TP++;
                }
            }
        }
        
        System.out.println(TP);
        
    }
    
    public static void partTwo(Scanner chop)
    {
        String str = chop.next();
        
        char[][] grid = new char[str.length()][str.length()];
        
        grid[0] = str.toCharArray();
        
        int i = 1;
        
        while(chop.hasNext())
        {
            char[] line = chop.next().toCharArray();
            grid[i] = line;
            i++;
        }
        
        int totalRemoved = 0;
        
        ArrayList<String> cords = new ArrayList<>();
        
        boolean hasRemoved = true;
        while(hasRemoved)
        {
            int TP = 0;
            for(int row = 0; row < grid.length; row++)
            {
                for(int col = 0; col < grid[0].length; col++)
                {
                    if(grid[row][col] == '@')
                    if(checkSurounding(grid, row, col) < 4)
                    {
                        TP++;
                        cords.add(row + " " + col);
                        totalRemoved++;
                    }
                }
            }
            
            
            hasRemoved = TP != 0;
            
            for(String cord : cords)
            {
                int row = Integer.parseInt(cord.substring(0, cord.indexOf(" ")));
                int col = Integer.parseInt(cord.substring(cord.indexOf(" ") + 1));
                
                grid[row][col] = 'X';
            }
            
        }
        
        System.out.println(totalRemoved);
        
    }
    
    //Helper Method to check surounding 8 quadrants
    public static int checkSurounding(char[][] grid, int row, int col)
    {
        int count = 0;
        
        boolean left, right, up, down;
        
        left = col == 0;
        right = col == grid[0].length - 1;
        up = row == 0;
        down = row == grid[0].length - 1;
        
        if(!(left || right || up || down))// somewhere in the middle
        {
            count += checkSquare(grid, row - 1, col - 1) ? 1 : 0; // top left
            count += checkSquare(grid, row - 0, col - 1) ? 1 : 0; // middle left
            count += checkSquare(grid, row - 1, col - 0) ? 1 : 0; // top middle
            count += checkSquare(grid, row + 1, col - 1) ? 1 : 0; // bottom left
            count += checkSquare(grid, row - 1, col + 1) ? 1 : 0; // top right
            count += checkSquare(grid, row + 1, col + 1) ? 1 : 0; // bottom right
            count += checkSquare(grid, row + 1, col + 0) ? 1 : 0; // bottom middle
            count += checkSquare(grid, row + 0, col + 1) ? 1 : 0; // middle right
        }else if(left && !(up || down))
        {
            count += checkSquare(grid, row - 1, col - 0) ? 1 : 0; // top middle
            count += checkSquare(grid, row - 1, col + 1) ? 1 : 0; // top right
            count += checkSquare(grid, row + 1, col + 1) ? 1 : 0; // bottom right
            count += checkSquare(grid, row + 1, col + 0) ? 1 : 0; // bottom middle
            count += checkSquare(grid, row + 0, col + 1) ? 1 : 0; // middle right
        }else if(right && !(up || down))
        {
            count += checkSquare(grid, row + 1, col + 0) ? 1 : 0; // bottom middle
            count += checkSquare(grid, row - 1, col - 1) ? 1 : 0; // top left
            count += checkSquare(grid, row - 0, col - 1) ? 1 : 0; // middle left
            count += checkSquare(grid, row - 1, col - 0) ? 1 : 0; // top middle
            count += checkSquare(grid, row + 1, col - 1) ? 1 : 0; // bottom left
            
        }else if(up && !(left || right))
        {
            count += checkSquare(grid, row + 1, col + 1) ? 1 : 0; // bottom right
            count += checkSquare(grid, row + 1, col + 0) ? 1 : 0; // bottom middle
            count += checkSquare(grid, row + 0, col + 1) ? 1 : 0; // middle right
            count += checkSquare(grid, row + 1, col - 1) ? 1 : 0; // bottom left
            count += checkSquare(grid, row - 0, col - 1) ? 1 : 0; // middle left
            
        }else if(down && !(left || right))
        {
            count += checkSquare(grid, row + 0, col + 1) ? 1 : 0; // middle right
            count += checkSquare(grid, row - 1, col + 1) ? 1 : 0; // top right
            count += checkSquare(grid, row - 1, col - 1) ? 1 : 0; // top left
            count += checkSquare(grid, row - 0, col - 1) ? 1 : 0; // middle left
            count += checkSquare(grid, row - 1, col - 0) ? 1 : 0; // top middle
        }else if( (left && (up || down)) || (right && up || down) )
        {
            return count;
        }
         
        return count;
        
    }
    
    //helper of helper method
    public static boolean checkSquare(char[][] grid, int row, int col)
    {
        return grid[row][col] == '@';
    }
}