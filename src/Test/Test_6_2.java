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
import src.light.*;
public class Test_6_2 extends Base{
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
        camera.setPerspective(60, 800/600f, 0.1, 1000 );
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0,0,2.5) );
        scene.add( rig );
        Light ambientLight = new AmbientLight( 
        new Vector(0.1, 0.1, 0.1) );
        scene.add( ambientLight );
        Light pointLight = new PointLight(
        new Vector(1,1,1), new Vector(1.2, 1.2, 0.3) );
        scene.add( pointLight );
        Geometry geometry = new RectangleGeometry(2,2);
        LambertMaterial bumpMaterial = new LambertMaterial( 
        new Texture("images/brick-color.png") );
        bumpMaterial.addBumpData( 
        new Texture("images/brick-bump.png"), 1.0f );
        mesh = new Mesh(geometry, bumpMaterial);
        scene.add(mesh);
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_6_2().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
