package src.Test;
import src.core.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import src.math.Vector;
public class Test_2_8 extends Base
{
    public int programRef, vaoRef;
    // must declare uniform class type here
    //   for update function to work
    public Uniform<Vector> translation, baseColor;
    public void initialize(){
        // load code, send to GPU, and compile; 
        //    store program reference
        programRef = OpenGLUtils.initFromFiles(
        "src/shaders/Test_2_5.vert", 
        "src/shaders/Test_2_4.frag" );
        // specify color used when clearing the screen
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        // setup vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);
        float[] positionData = {
        0.0f,  0.2f, 0.0f,
        0.2f, -0.2f, 0.0f,
        -0.2f, -0.2f, 0.0f };
        Attribute positionAttribute = new Attribute("vec3", positionData );
        positionAttribute.assoicateVariable(programRef, "position" );
        // set up uniforms
        translation = new Uniform<Vector>("vec3", new Vector(-0.5f, 0.0f, 0.0f) );
        translation.locateVariable( programRef, "translation" );
        baseColor = new Uniform<Vector>("vec3", new Vector(1.0f, 0.0f, 0.0f) );
        baseColor.locateVariable( programRef, "baseColor" );
    }
    public void update()
    {
        // update data
        translation.data.values[0] = (float)(0.75 * Math.cos(time));
        translation.data.values[1] = (float)(0.75 * Math.sin(time));
        // render scene
        // reset color buffer with specified color
        glClear(GL_COLOR_BUFFER_BIT);
        glUseProgram( programRef );
        glBindVertexArray( vaoRef );
        translation.uploadData();
        baseColor.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_2_8().run();
        }
        catch( Exception ex){
            ex.printStackTrace();
        }
    }
}
