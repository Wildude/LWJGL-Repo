package src.Test;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import src.core.*;
import src.core.Base;
import src.core.OpenGLUtils;
public class Test_2_3 extends Base {
    public int programRef;
    public void initialize(){
        // load code, send to GPU, and compile;
        //   store program reference
        programRef = OpenGLUtils.initFromFiles(
        "src/shaders/Test_2_3.vert", 
        "src/shaders/Test_2_2.frag" );
        // render settings (optional)
        // set line width
        glLineWidth(4);
        // setup vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);
        float[] positionData = {
        0.8f,  0.0f, 0.0f,
        0.4f,  0.6f, 0.0f,-0.4f,  0.6f, 0.0f,-0.8f,  0.0f, 0.0f,-0.4f, -0.6f, 0.0f,
        0.4f, -0.6f, 0.0f  };
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.assoicateVariable(programRef, "position");
    }
    public void update(){
        // select program to use when rendering
        glUseProgram( programRef );
        // render geometric objects using selected program
        glDrawArrays(GL_LINE_LOOP, 0, 6);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_2_3().run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
