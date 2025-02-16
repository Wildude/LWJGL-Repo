package src.Test;
import static org.lwjgl.opengl.GL40.*;
import src.core.*;
public class Test_2_4 extends Base{
    public int programRef, vaoTri, vaoSquare;
    public void initialize(){
        // load code, send to GPU, and compile;
        // store program reference
        programRef = OpenGLUtils.initFromFiles("src/shaders/Test_2_3.vert", "src/shaders/Test_2_2.frag");
        // render settings (optional)
        // set line width
        glLineWidth(4);
        // setup vertex array object: triangle
        vaoTri = glGenVertexArrays();
        glBindVertexArray(vaoTri);
        float[] positionDataTri = {
            -0.5f, 0.8f, 0.0f,
            -0.2f, 0.2f, 0.0f,
            -0.8f, 0.2f, 0.0f
        };
        Attribute positionAttributeTri = new Attribute ( "vec3", positionDataTri);
        positionAttributeTri.assoicateVariable(programRef, "position");
        // setup vertex array object: square
        vaoSquare = glGenVertexArrays();
        glBindVertexArray(vaoSquare);
        float[] positionDataSquare = {
            0.8f, 0.8f, 0.0f,
            0.8f, 0.2f, 0.0f,
            0.2f, 0.2f, 0.0f,
            0.2f, 0.8f, 0.0f
        };
        Attribute positionAttributeSquare = new Attribute( "vec3", positionDataSquare);
        positionAttributeSquare.assoicateVariable(programRef, "position");
    }
    public void update(){
        // using same program to render both shapes
        glUseProgram(programRef);
        // draw the triangle
        glBindVertexArray(vaoTri);
        glDrawArrays(GL_LINE_LOOP, 0, 3);
        // draw the square
        glBindVertexArray(vaoSquare);
        glDrawArrays(GL_LINE_LOOP, 0, 4);
    }
    // driver method
    public static void main(String[] args){
        try{
            new Test_2_4().run();
        }
        catch(Exception ex){
            System.out.println(" Caught exception: ");
            ex.getStackTrace();
        }
    }
}