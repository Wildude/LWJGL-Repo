package src.core;
import static org.lwjgl.glfw.Callbacks.*;
//
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import javax.management.RuntimeErrorException;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
public abstract class Base {
    // window dimensions
    private int windowWidth;
    private int windowHeight;
    // the window handle
    private long window;
    // is the main loop currently active?
    private boolean running;
    public Base(){}
    public void startup() throws RuntimeException{
        boolean initSuccess = glfwInit();
        if(!initSuccess) throw new RuntimeException("Unable to initialize GLFW");
        // create window and associated OpenGL context,
        // which stores framebuffer and other state information
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(windowWidth, windowHeight, "Graphics Window", 0, 0);
        if(window == 0) throw new RuntimeException("Failed to create the GLFW window");
        running = true;
        // make all OpenGL function calls
        // apply to this context instance
        glfwMakeContextCurrent(window);
        // specify number of screen updates
        // to wait before swapping buffers.
        // setting to 1 synchronizes application frame rate
        // with display refresh rate;
        // prevents visual "screen tearing" artifacts
        glfwSwapInterval(1);
        // detect current context and makes
        // OpenGL bindings available for use
        GL.createCapabilities();
        OpenGLUtils.checkVersion();
        System.out.println(" LWJGL version: " + Version.getVersion());
    }
    public abstract void initialize();
    public abstract void update();
    public void run (int windowWidth, int windowHeight) throws Exception{
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        try{
            startup();
        }
        catch(RuntimeException rex){
            System.out.println(" Exception caught: " + rex.getMessage());
            return;
        }
        // application-specific startup code
        initialize();
        // main loop
        while(running){
            // check for user interaction events
            glfwPollEvents();
            // check if window close icon is clicked
            if (glfwWindowShouldClose(window)) running = false;
            // application-specific update code
            update();
            // swap the color buffers
            // to display rendered graphics on screen
            glfwSwapBuffers(window);
        }
        try{
            shutdown();    
        }
        catch(Exception ex){
            System.out.println("Exception caught in shutdown: " + ex.getMessage());
        }

    }
    public void run() throws Exception{
        run(640, 480);
    }
    public void shutdown(){
        // stop window monitoring for user input
        glfwFreeCallbacks(window);
        // close the window
        glfwDestroyWindow(window);
        // stop GLFW
        glfwTerminate();
        // stop error callback
        // glfwSetErrorCallback(null).free();
        // the above single line of code was from the book.
        // apparently if the return value of the function is null, an Exeception with message:
        /*
         * Cannot invoke "org.lwjgl.glfw.GLFWErrorCallback.free()" 
         * because the return value of 
         * "org.lwjgl.glfw.GLFW.glfwSetErrorCallback(org.lwjgl.glfw.GLFWErrorCallbackI)" is null
         */
        // is encountered which raises the NullPointerException
        // a fix was made after some backs and forths with GPT
        GLFWErrorCallback callback = GLFW.glfwSetErrorCallback(null);
        if (callback != null) callback.free();
        else System.out.println("No previous error callback to free.");    
    }
}
