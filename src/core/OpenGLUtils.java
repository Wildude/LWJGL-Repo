package src.core;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgramiv;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderiv;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL40.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class OpenGLUtils{
    public static int initFromFiles (String vertexShaderFileName, String fragmentShaderFileName){
        return initProgram (readFile(vertexShaderFileName), readFile(fragmentShaderFileName));
    }
    public static String readFile (String fileName){
        String text = "";
        try{
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return text;
    }
    static int[] status = new int[1];
    public static int initShader(String shaderCode, int shaderType){
        // specify specific OpenGL version
        shaderCode = "#version 330\n" + shaderCode;
        // create empty shader object and return reference value
        int shaderRef = glCreateShader(shaderType);
        // stores the source code in the shader
        glShaderSource(shaderRef, shaderCode);
        // compiles the shader
        glCompileShader(shaderRef);
        // check for compilation errors
        glGetShaderiv(shaderRef, GL_COMPILE_STATUS, status);
        if (status[0] == GL_FALSE){
            // retrieve error message
            String errorMessage = glGetShaderInfoLog(shaderRef);
            // free memory used to store shader program
            glDeleteShader(shaderRef);
            // halt program and print error message
            throw new RuntimeException(errorMessage);
        }
        // compilation was successful
        // return shader reference value
        return shaderRef;
    }
    public static int initProgram (String vertexShaderCode, String fragmentShaderCode){
        int vertexShaderRef = initShader(vertexShaderCode, GL_VERTEX_SHADER);
        int fragmentShaderRef = initShader(fragmentShaderCode, GL_FRAGMENT_SHADER);
        // create empty program object and store it's reference
        int programRef = glCreateProgram();
        // attach shaders to the program
        glAttachShader(programRef, vertexShaderRef);
        glAttachShader(programRef, fragmentShaderRef);
        // link vertex shader to fragment shader
        glLinkProgram(programRef);
        // check for linking errors
        glGetProgramiv(programRef, GL_LINK_STATUS, status);
        if (status[0] == GL_FALSE){
            // retrieve error message
            String errorMessage = glGetProgramInfoLog(programRef);
            // free memory used to store program
            glDeleteProgram(programRef);
            // halt program and print error message
            throw new RuntimeException(errorMessage);
        }
        // linking was successful
        // return program reference value
        return programRef;
    }
    public static void checkVersion()
    {
        System.out.println("Vendor: " + glGetString(GL_VENDOR));
        System.out.println("Renderer: " + glGetString(GL_RENDERER));
        System.out.println("OpenGL version supported: " + glGetString(GL_VERSION));
    }
}