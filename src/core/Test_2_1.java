package src.core;
import src.core.Base;

public class Test_2_1 extends Base{
    public void initialize(){
        System.out.println(" Initializing program...");
    }
    public void update(){}
    // driver method
    public static void main(String[] args){
        try{
            new Test_2_1().run();
        }
        catch(Exception ex){
            System.out.println(" Exception caught in main: " + ex.getMessage());
        }
    }
}
