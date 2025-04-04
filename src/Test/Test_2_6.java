package src.Test;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL40.*;
import src.core.*;
import src.math.Vector;
public class Test_2_6 extends Base{
    public int programRef, vaoRef;
    public Uniform<Vector> translation1, translation2,
    baseColor1, baseColor2;
    public void initialize(){
        // load code, send to GPU, and compile; 
        //    store program reference
        programRef = OpenGLUtils.initFromFiles(
        "src/shaders/Test_2_5.vert", 
        "src/shaders/Test_2_4.frag" );
        // setup vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);
        float[] positionData = {
        0.0f,  0.2f, 0.0f,
        0.2f, -0.2f, 0.0f,
        -0.2f, -0.2f, 0.0f  };
        Attribute positionAttribute = new Attribute( 
        "vec3", positionData );
        positionAttribute.assoicateVariable(programRef, "position");
        // set up uniforms
        translation1 = new Uniform<Vector>(
        "vec3", new Vector(-0.5f, 0.0f, 0.0f) );
        translation1.locateVariable(programRef, "translation");
        translation2 = new Uniform<Vector>(
        "vec3", new Vector(0.5f, 0.0f, 0.0f) );
        translation2.locateVariable(programRef, "translation");
        baseColor1 = new Uniform<Vector>(
        "vec3", new Vector(1.0f, 0.0f, 0.0f) );
        baseColor1.locateVariable(programRef, "baseColor");
        baseColor2 = new Uniform<Vector>(
        "vec3", new Vector(0.0f, 0.0f, 1.0f) );
        baseColor2.locateVariable(programRef, "baseColor");
    }
    public void update()
    {
        glUseProgram( programRef );
        glBindVertexArray( vaoRef );
        // draw the first triangle
        translation1.uploadData();
        baseColor1.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );
        // draw the second triangle
        translation2.uploadData();
        baseColor2.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_2_6().run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
