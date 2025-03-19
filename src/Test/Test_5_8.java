package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.core.*;
import src.extras.GridHelper;
import src.extras.MovementRig;
import src.geometry.*;
import src.material.*;
import src.math.*;
public class Test_5_8 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh, label;
    public MovementRig rig;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0, 1.5, 5) );
        scene.add( rig );
        Geometry boxGeometry = new BoxGeometry();
        Material boxMaterial = new TextureMaterial( 
        new Texture("images/AAU.png") );
        Mesh box = new Mesh( boxGeometry, boxMaterial );
        box.setPosition( new Vector(0,0.5,0) );
        scene.add( box );
        Mesh grid = new GridHelper(10, 10, 
        new Vector(1,1,1), new Vector(1,0.5,0.5), 2);
        grid.rotateX(-Math.PI/2, true);
        scene.add( grid );
        Texture labelTexture = new Texture("images/CS.jpg");
        Material labelMaterial = new TextureMaterial(labelTexture);
        Geometry labelGeometry = new RectangleGeometry(1, 0.5);
        //labelGeometry.applyMatrix( Matrix.makeRotationY(3.14) );
        label = new Mesh(labelGeometry, labelMaterial);
        label.setPosition( new Vector(0, 1.5, 0) );
        scene.add(label);   
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true )
        label.lookAt(camera.getWorldPosition());
        rig.update(input, deltaTime);
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_5_8().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
