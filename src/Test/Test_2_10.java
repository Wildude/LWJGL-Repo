package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import src.core.*;
public class Test_2_10 extends Base{
    public void initialize()
    {
        System.out.println("Initializing...");
    }
    public void update()
    {
        // typical usage
        if (input.isKeyDown(GLFW_KEY_SPACE))
        System.out.println( "space key" );
        if (input.isKeyPressed(GLFW_KEY_RIGHT))
        System.out.println( "right arrow key" );
        if (input.isKeyUp(GLFW_KEY_A))
        System.out.println( "letter \'A\' key" );
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_2_10().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}