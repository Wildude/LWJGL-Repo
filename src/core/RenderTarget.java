package src.core;
import static org.lwjgl.opengl.GL40.*;
import src.math.Vector;
public class RenderTarget {
    // store associated texture dimensions
    public int width;
    public int height;
    public Texture texture;
    public int framebufferRef;
    public RenderTarget(Vector resolution)
    {
        width = (int)resolution.values[0];
        height = (int)resolution.values[1];
        // generate an empty texture
        texture = new Texture(width, height);
        // create a framebuffer
        framebufferRef = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, framebufferRef);
        // configure color buffer to use this texture
        glFramebufferTexture(GL_FRAMEBUFFER, 
        GL_COLOR_ATTACHMENT0, texture.textureRef, 0);
        // generate a buffer to store depth information
        int depthBufferRef = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, depthBufferRef);
        glRenderbufferStorage(GL_RENDERBUFFER, 
        GL_DEPTH_COMPONENT, width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, 
        GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depthBufferRef);
        // check framebuffer status
        int status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if (status != GL_FRAMEBUFFER_COMPLETE)
        System.out.println("Framebuffer status error: " 
        + status);
    }
}
