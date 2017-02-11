package Model;

/**
 * @author lshadler
 * Prints the model for testing
 */
public class testModel {
    public static void main(String [] args){

        // Create a new instance of the model
        Model test = new Model();

        // Print the peg board
        System.out.println("=============================================");
        System.out.println("===         SAMPLE BOARD PRINTING         ===");
        System.out.println("=============================================\n\n");
        System.out.println(test.toString());
        System.out.println("\n\n=============================================");
        System.out.println("===         SAMPLE ADJACENCY TEST         ===");
        System.out.println("=============================================\n\n");

        // test indexes

        int i;
        if( args.length == 0) i = 4;
        else i = Integer.parseInt(args[0]);

        int [] adjTest = test.getAdjInds(i);

        System.out.println("Index: " + i);
        System.out.println("Row: " + test.getRow(i) + "\n");
        System.out.printf("    %02d  %02d    \n",adjTest[5],adjTest[0]);
        System.out.printf("  %02d  %02d  %02d\n",adjTest[4],i,adjTest[1]);
        System.out.printf("    %02d  %02d    \n",adjTest[4],adjTest[2]);

        System.out.println("\n\n=============================================");
        System.out.println("=============================================");
        System.out.println("=============================================\n\n");

    }
}
