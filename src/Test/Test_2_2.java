package src.Test;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL40.*;
import src.core.*;
public class Test_2_2 extends Base{
    public int programRef;
    public void initialize(){
        // load code, send to GPU, and compile;
        // store program reference
        // System.out.println("Current working directory: " + System.getProperty("user.dir")); (it's the entire repo)
        // as a result use path relative to repo (pretty much like absolute path but the root is the repo)
        programRef = OpenGLUtils.initFromFiles("src/shaders/Test_2_2.vert", "src/shaders/Test_2_2.frag");
        // set up vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);
        // render settings (optional)
        // set point width and height
        glPointSize(10);
    }
    public void update(){
        // select program to use when rendering
        glUseProgram(programRef);
        // render geometric objects using selected program
        glDrawArrays(GL_POINTS, 0, 1);
    }
    // driver method
    public static void main(String[] args){
        try{
            new Test_2_2().run();
        }
        catch(Exception ex){
            System.out.println(" Caught exception: ");
            ex.getStackTrace();
        }
    }
}
