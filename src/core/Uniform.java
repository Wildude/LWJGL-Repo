package src.core;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL40.*;
import java.util.HashMap;
import src.math.Matrix;
import src.math.Vector;
import src.light.*;
// variable types: Integer | Float | Vector
public class Uniform<T> {
    // store name of GLSL type:
    // int | bool | float | vec2 | vec3 | vec4
    private String dataType;
    // data to be sent to uniform variable
    public T data;
    // store results of generating buffers
    private int[] resultArray = new int[1];
    // reference for variable location in program
    private int variableRef;
    // reference for multiple variable locations in program
    private HashMap<String, Integer> variableRefMap;
    public Uniform(String dataType, T data)
    {
        this.dataType = dataType;
        this.data = data;
    }
    // get and store reference for program variable with name
    public void locateVariable(int programRef, String variableName)
    {
        if (dataType.equals("Light"))
        {
            variableRefMap = new HashMap<String, Integer>();
            variableRefMap.put("lightType",
            glGetUniformLocation(programRef, 
            variableName + ".lightType") );
            variableRefMap.put("color",
            glGetUniformLocation(programRef, 
            variableName + ".color") );
            variableRefMap.put("direction",
            glGetUniformLocation(programRef, 
            variableName + ".direction") );
            variableRefMap.put("position",
            glGetUniformLocation(programRef, 
            variableName + ".position") );
            variableRefMap.put("attenuation",
            glGetUniformLocation(programRef, 
            variableName + ".attenuation") );
        }
        else{
            variableRef = glGetUniformLocation(programRef, variableName);
            if (variableRef == -1){
                System.out.println(" program can't reference Uniform variable: " + variableName);
                return; 
            }
        }
    }
    // store data in uniform variable previously located
    public void uploadData()
    {
        // if program does not reference variable, exit
        if (variableRef == -1){
            System.out.println("Variable not found in program");
            return; 
        }
        if (dataType.equals("int"))
        glUniform1i(variableRef, (Integer)data);
        else if (dataType.equals("bool"))
        glUniform1i(variableRef, (Integer)data);
        else if (dataType.equals("float"))
        glUniform1f(variableRef, (Float)data);
        else if (dataType.equals("vec2"))
        {
            Vector v = (Vector)data;
            glUniform2f(variableRef, 
            (float)v.values[0], 
            (float)v.values[1]);
        }
        else if (dataType.equals("vec3"))
        {
            Vector v = (Vector)data;
            glUniform3f(variableRef, 
            (float)v.values[0], 
            (float)v.values[1], 
            (float)v.values[2]);
        }
        else if (dataType.equals("vec4"))
        {
            Vector v = (Vector)data;
            glUniform4f(variableRef,
            (float)v.values[0], 
            (float)v.values[1], 
            (float)v.values[2], 
            (float)v.values[3]);
        }
        else if (dataType.equals("mat4"))
        {
            Matrix m = (Matrix)data;
            glUniformMatrix4fv(variableRef, true, m.flatten());
        }
        else if (dataType.equals("sampler2D"))
        {
            Vector v = (Vector)data;
            int textureObjectRef = (int)v.values[0];
            int textureUnitRef   = (int)v.values[1];
            // activate texture unit
            glActiveTexture( GL_TEXTURE0 + textureUnitRef );
            // associate texture object reference 
            //   to currently active texture unit
            glBindTexture( GL_TEXTURE_2D, textureObjectRef );
            // upload texture unit number (0...15) 
            //   to uniform variable in shader
            glUniform1i( variableRef, textureUnitRef );
        }
        else if (dataType.equals("Light"))
        {
            Light L = (Light)data;
            glUniform1i( variableRefMap.get("lightType"), 
            (int)L.lightType );
            Vector col = L.color;
            glUniform3f( variableRefMap.get("color"),
            (float)col.values[0], (float)col.values[1], 
            (float)col.values[2] );
            Vector dir = L.getDirection();
            glUniform3f( variableRefMap.get("direction"),
            (float)dir.values[0], (float)dir.values[1], 
            (float)dir.values[2] );
            Vector pos = L.getPosition();
            glUniform3f( variableRefMap.get("position"),
            (float)pos.values[0], (float)pos.values[1], 
            (float)pos.values[2] );
            Vector att = L.attenuation;
            glUniform3f( variableRefMap.get("attenuation"), 
            (float)att.values[0], (float)att.values[1], 
            (float)att.values[2] );
        }
    }
}
