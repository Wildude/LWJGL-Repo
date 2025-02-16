package src.Test;
import src.core.*;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
public class Test_2_5 extends Base{ 
    public int programRef, vaoRef;
    public void initialize()
    {
        // load code, send to GPU, and compile; 
        //    store program reference
        programRef = OpenGLUtils.initFromFiles(
        "src/shaders/Test_2_4.vert", 
        "src/shaders/Test_2_3.frag" );
        // render settings (optional)
        // set line width
        glPointSize(10);
        glLineWidth(4);
        // setup vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);
        float[] positionData = {
            0.8f,  0.0f, 0.0f,
            0.4f,  0.6f, 0.0f,
            -0.4f,  0.6f, 0.0f,
            -0.8f,  0.0f, 0.0f,
            -0.4f, -0.6f, 0.0f,
            0.4f, -0.6f, 0.0f };
            Attribute positionAttribute = new Attribute("vec3", positionData );
            positionAttribute.assoicateVariable(programRef, "position");
            float[] colorData = {
            1.0f, 0.0f, 0.0f,
            1.0f, 0.5f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            0.5f, 0.0f, 1.0f  };
            Attribute colorAttribute = new Attribute("vec3", colorData );
            colorAttribute.assoicateVariable(programRef, "vertexColor");
        }
        public void update()
        {
            glUseProgram( programRef );
            // draw the object
            glBindVertexArray( vaoRef );
            glDrawArrays( GL_TRIANGLE_FAN,  0,  6 );
        }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_2_5().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
