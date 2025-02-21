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
public class Test_4_2 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPosition( new Vector(0,0,10) );
        Geometry geometry = new SphereGeometry(3);
        Material material = new Material(
        "src/shaders/Test_4_2.vert", "src/shaders/Test_4_2.frag");
        material.locateUniforms();
        mesh = new Mesh( geometry, material );
        scene.add( mesh );
    }
    public void update()
    {
        mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_4_2().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
