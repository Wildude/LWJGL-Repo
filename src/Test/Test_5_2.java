package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.core.*;
import src.extras.MovementRig;
import src.geometry.*;
import src.material.*;
import src.math.*;
public class Test_5_2 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public MovementRig rig;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0, 1, 4) );
        scene.add( rig );
        Geometry skyGeometry = new SphereGeometry(50);
        Material skyMaterial = new TextureMaterial( 
        new Texture("images/sky.jpg") );
        Mesh sky = new Mesh( skyGeometry, skyMaterial );
        scene.add( sky );
        Geometry grassGeometry = new RectangleGeometry(100, 100);
        Material grassMaterial = new TextureMaterial( 
        new Texture("images/grass.jpg") );
        grassMaterial.uniforms.get("repeatUV").data = new Vector(50,50);
        Mesh grass = new Mesh( grassGeometry, grassMaterial );
        grass.rotateX(-3.14/2, true);
        scene.add( grass );
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
        rig.update(input, deltaTime);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_5_2().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
