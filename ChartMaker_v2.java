import java.util.*;
import java.io.*;

public class ChartMaker_v2 {
    
    static Scanner keyb = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        if (args.length != 4) {
            System.out.println("Required command line arguments are:");
            System.out.println("\t(1) input file, (2) output file, (3) chart width,"+
                              " and (4) symbol");
        } else {
            String inFilename = args[0];
            String outFilename = args[1];
            int chartWidth = Integer.parseInt(args[2]); 
            char symbol = args[3].charAt(0);
            
            // chart description
            String title = getChartTitle(inFilename);
            String units = getChartUnits(inFilename);
            
            // compute the scale of the chart (units per symbol)
            double max = findChartMax(inFilename);
            double scale = max / chartWidth;
            
            // print the chart to the display
            System.out.print("\n"+title+"\n");
            System.out.printf("One '%s' represents %,.0f %s\n\n",symbol,scale,units);
           
            printChart(scale, symbol, inFilename);
            
            // print the chart to the file
            try (PrintWriter pw = new PrintWriter(outFilename)) {
                pw.printf("%s\n",title);
                pw.printf("One '%s' represents %,.0f %s\n\n",symbol,scale,units);

                printChart(scale, symbol, inFilename, pw);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.printf("Could not open '%s' for output.\n",outFilename);
            }

            System.out.println("\nDone. Thanks for using the Chart Maker, v2"); 
        }   
    }
    
    // String title = getChartTitle(inFilename);
    public static String getChartTitle(String inFilename) {
        String title = "";
        try (Scanner fileScan = new Scanner(new File(inFilename))) {
            while (title.equals("")) {
                title = fileScan.nextLine();
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }
    
    // String units = getChartUnits(inFilename);
    public static String getChartUnits(String inFilename) {
        String units = "";
        try (Scanner fileScan = new Scanner(new File(inFilename))) {
            while (units.equals("")) {
                String title = fileScan.nextLine();
                units = fileScan.nextLine();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return units;
    }
    
    // double max = findChartMax(inFilename);
    public static Double findChartMax(String inFilename) {
        double max = 0.0;
        try (Scanner fileScan = new Scanner(new File(inFilename))) {
            String units = "";
            while (units.equals("")) {
                String title = fileScan.nextLine();
                units = fileScan.nextLine();
            } 
            
            while (fileScan.hasNext()) {
                Double val = Double.parseDouble(fileScan.nextLine());
                String label = fileScan.nextLine();
                
                if (val > max) {
                    max = val;
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
    
    // printChart(scale, symbol, inFilename);
    public static void printChart(double scale, char symbol, String inFilename) {
        try (Scanner fileScan = new Scanner(new File(inFilename))) {
            String units = "";
            while (units.equals("")) {
                String title = fileScan.nextLine();
                units = fileScan.nextLine();
            } 
            
            while (fileScan.hasNext()) {
                Double val = Double.parseDouble(fileScan.nextLine());
                String label = fileScan.nextLine();
                int numSymbols = (int)(val / scale);
                
                for(int n = 0; n < numSymbols; n++) {
                    System.out.print(symbol); 
                }
                System.out.printf(" (%,.0f) %s\n",val,label);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // printChart(scale, symbol, inFilename, pw);
     public static void printChart(double scale, char symbol, String inFilename,
                                   PrintWriter pw) {
        try (Scanner fileScan = new Scanner(new File(inFilename))) {
            String units = "";
            while (units.equals("")) {
                String title = fileScan.nextLine();
                units = fileScan.nextLine();
            } 
            
            while (fileScan.hasNext()) {
                Double val = Double.parseDouble(fileScan.nextLine());
                String label = fileScan.nextLine();
                int numSymbols = (int)(val / scale);
                
                for(int n = 0; n < numSymbols; n++) {
                    pw.print(symbol); 
                }
                pw.printf(" (%,.0f) %s\n",val,label);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}