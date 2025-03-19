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
public class Test_5_9 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh, sprite;
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
        Geometry geometry = new RectangleGeometry();
        Material material = new SpriteMaterial( 
        new Texture("images/rolling-ball.png") );
        material.uniforms.get("billboard").data = 1;
        material.uniforms.get("tileCount").data = new Vector(4,4);
        material.uniforms.get("tileNumber").data = 0;
        sprite = new Mesh( geometry, material );
        sprite.setPosition( new Vector(0, 0.5, 0) );
        scene.add( sprite );
        Mesh grid = new GridHelper(10,10, new Vector(1,1,1), 
        new Vector(1, 0.5, 0.5), 2);
        grid.rotateX(-Math.PI/2, true);
        scene.add( grid );
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        int tilesPerSecond = 8;
        float tileNumber = (float)Math.floor(time * tilesPerSecond);
        sprite.material.uniforms.get("tileNumber").data = tileNumber;
        rig.update(input, deltaTime);
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_5_9().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
