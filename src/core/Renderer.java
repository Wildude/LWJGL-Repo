package src.core;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL40.*;
import java.util.List;
import java.util.ArrayList;
import src.math.Vector;
import src.light.*;
public class Renderer
{    
    public boolean clearColorBuffer, clearDepthBuffer;
    public RenderTarget renderTarget;
    public Renderer()
    {
        clearColorBuffer = true;
        clearDepthBuffer = true;
        glClearColor(0, 0, 0, 1);
        glEnable( GL_DEPTH_TEST );
        // required for antialiasing
        glEnable( GL_MULTISAMPLE );
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    public void setClearColor( Vector color )
    {
        glClearColor( (float)color.values[0], 
        (float)color.values[1],(float)color.values[2], 1);
    }
    public void render(Scene scene, Camera camera){
        // activate render target
        if (renderTarget == null)
        {
            // set render target to window
            glBindFramebuffer(GL_FRAMEBUFFER, 0);
            glViewport(0,0, Base.getWidth(), Base.getHeight());
        }
        else
        {
            // set render target properties
            glBindFramebuffer(GL_FRAMEBUFFER, 
            renderTarget.framebufferRef);
            glViewport(0, 0, renderTarget.width, renderTarget.height);
        }
        // clear color and/or depth buffers
        if (clearColorBuffer)
        glClear(GL_COLOR_BUFFER_BIT);
        if (clearDepthBuffer)
        glClear(GL_DEPTH_BUFFER_BIT);
        // update camera view (calculate inverse)
        camera.updateViewMatrix();
        // extract list of all Mesh objects in scene
        List<Object3D> descendentList = scene.getDescendentList();
        ArrayList<Mesh> meshList = new ArrayList<Mesh>();
        // extract list of all Light objects in scene
        ArrayList<Light> lightList = new ArrayList<Light>();
        for (Object3D obj : descendentList)
        if (obj instanceof Light)
        lightList.add( (Light)obj );
        // scenes support 4 lights; precisely 4 must be present
        while ( lightList.size() < 4 )
        lightList.add( new Light() );
        for (Object3D obj : descendentList)
        if (obj instanceof Mesh)
        meshList.add( (Mesh)obj );
        for (Mesh mesh : meshList)
        {
            // if this object is not visible,
            // continue to next object in list
            if (!mesh.visible)
            continue;
            glUseProgram( mesh.material.programRef );
            // bind VAO
            glBindVertexArray( mesh.vaoRef );
            // update uniform values stored outside of material
            mesh.material.uniforms.get("modelMatrix").data = 
            mesh.getWorldMatrix();
            mesh.material.uniforms.get("viewMatrix").data = 
            camera.viewMatrix;
            mesh.material.uniforms.get("projectionMatrix").data 
            = camera.projectionMatrix;
            // update uniforms stored in material
            // if material uses light data, add lights from list
            if ( mesh.material.uniforms.containsKey("light0") )
            {
                for (int lightNumber = 0; lightNumber < 4; lightNumber++)
                {
                    String lightName = "light" + lightNumber;
                    Light lightObject = lightList.get(lightNumber);
                    mesh.material.uniforms.get(lightName).data 
                    = lightObject;
                }
            }
            // add camera position if needed (specular lighting)
            if ( mesh.material.uniforms.containsKey("viewPosition") )
            mesh.material.uniforms.get("viewPosition").data = 
            camera.getWorldPosition();
            for ( Uniform uniform : 
            mesh.material.uniforms.values() )
            uniform.uploadData();
            // update render settings
            for ( RenderSetting setting : 
            mesh.material.renderSettings.values() )
            setting.apply();
            glDrawArrays( mesh.material.drawStyle, 
            0, mesh.geometry.vertexCount );
        }
    }
}