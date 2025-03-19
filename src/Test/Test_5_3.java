package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.core.*;
import src.geometry.*;
import src.material.*;
import src.math.*;
public class Test_5_3 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPosition( new Vector(0,0,1.5) );
        Geometry geometry = new RectangleGeometry();
        Texture gridTex = new Texture("images/CS.jpg");
        Material waveMaterial = new Material(
        "src/shaders/Test_5_3.vert", "src/shaders/Test_5_3.frag");
        waveMaterial.addUniform("sampler2D", "tex", 
        new Vector(gridTex.textureRef, 1));
        waveMaterial.addUniform("float", "time", 0.0f);
        waveMaterial.locateUniforms();
        mesh = new Mesh( geometry, waveMaterial );
        scene.add(mesh);
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
        mesh.material.uniforms.get("time").data = time;
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_5_3().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
