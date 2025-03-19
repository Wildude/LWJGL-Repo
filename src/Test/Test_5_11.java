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
public class Test_5_11 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera, skyCamera;
    public Mesh mesh, sphere;
    public MovementRig rig;
    public RenderTarget renderTarget;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPerspective(60, 800/600f, 0.1, 1000);
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
        Geometry sphereGeometry = new SphereGeometry();
        Material sphereMaterial = new TextureMaterial( 
        new Texture("images/CS.jpg") );       
        sphere = new Mesh( sphereGeometry, sphereMaterial );
        sphere.setPosition( new Vector(-1.2, 1, 0) );
        scene.add( sphere );
        Geometry boxGeometry = new BoxGeometry(2, 2, 0.2);
        Material boxMaterial = new SurfaceMaterial();
        boxMaterial.uniforms.get("baseColor").data = 
        new Vector(0.2, 0.2, 0.2);
        Mesh box = new Mesh( boxGeometry, boxMaterial );
        box.setPosition( new Vector(1.2, 1, 0) );
        scene.add( box );   
        renderTarget = new RenderTarget( new Vector(512,512) );
        Geometry screenGeometry = new RectangleGeometry(1.8, 1.8);
        Material screenMaterial = new TextureMaterial( 
        renderTarget.texture );
        Mesh screen = new Mesh( screenGeometry, screenMaterial );
        screen.setPosition( new Vector(1.2, 1, 0.11) );
        scene.add( screen );
        // camera aspectRatio=1 by default, as needed
        skyCamera = new Camera(); 
        skyCamera.setPosition( new Vector(0, 10, 0.1) );
        scene.add( skyCamera );
        skyCamera.lookAt( new Vector(0, 0, 0) );
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        rig.update(input, deltaTime);
        sphere.rotateY( 0.01337, true );
        renderer.renderTarget = renderTarget;
        renderer.render(scene, skyCamera);
        renderer.renderTarget = null;
        renderer.render(scene, camera);       
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_5_11().run(800, 600);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
