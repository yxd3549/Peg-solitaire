package Model;

/**
 * @author lshadler
 * Prints the model for testing
 */
public class testModel {
    public static void main(String [] args){
        Model test = new Model();
        System.out.println(test.toString());

        int [] adjTest = test.getAdjInds(4);

        System.out.println(test.getRow(4));
        for(int x : adjTest){
            System.out.println(x);
        }
    }
}
