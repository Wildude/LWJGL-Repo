package src.core;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL40.*;
import java.util.Arrays;

import src.math.Matrix;
import src.math.Vector;
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
    public Uniform(String dataType, T data)
    {
        this.dataType = dataType;
        this.data = data;
    }
    // get and store reference for program variable with name
    public void locateVariable(int programRef, String variableName)
    {
        variableRef = glGetUniformLocation(programRef, variableName);
        if (variableRef == -1){
            System.out.println(" program can't reference variable: " + variableName);
            return; 
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
    }
}
