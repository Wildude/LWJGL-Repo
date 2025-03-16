package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.core.*;
import src.extras.AxesHelper;
import src.extras.DirectionalLightHelper;
import src.extras.GridHelper;
import src.extras.MovementRig;
import src.extras.PointLightHelper;
import src.geometry.*;
import src.material.*;
import src.math.*;
import src.light.*;
public class Test_6_1 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public MovementRig rig;
    public DirectionalLight directionalLight;
    public PointLight pointLight;   
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPerspective(60, 800/600f, 0.1, 1000 );
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0.5, 1, 4) );
        scene.add( rig );
        Mesh grid = new GridHelper(10,10, new Vector(1,1,0), new 
        Vector(1,1,1), 2);
        grid.rotateX(-Math.PI/2, true);
        scene.add( grid );
        Mesh axes = new AxesHelper(2, 8);
        axes.translate(0, 0.01, 0, true);
        scene.add( axes );
        Light ambient = new AmbientLight( new Vector(0.1, 0.1, 0.1) );
        scene.add( ambient );
        directionalLight = new DirectionalLight( new Vector(0.8, 0.8, 0.8), new Vector(-1, -1, -2) );
        scene.add( directionalLight );
        pointLight = new PointLight( new Vector(0.9, 0, 0), new Vector(1, 1, 0.8) );
        scene.add( pointLight );
        Mesh directionalHelper = new DirectionalLightHelper(directionalLight);
        directionalLight.setPosition( new Vector(3,2,0) );
        directionalLight.add( directionalHelper );
        Mesh pointHelper = new PointLightHelper( pointLight );
        pointLight.add( pointHelper );
        directionalLight.setPosition( new Vector(3,2,0) );
        Geometry sphereGeometry = new SphereGeometry();
        Material flatMaterial = new FlatMaterial(null);
        flatMaterial.uniforms.get("baseColor").data = new Vector(0.6, 0.2, 0.2);
        Mesh sphere1 = new Mesh(sphereGeometry, flatMaterial);
        sphere1.setPosition( new Vector(-2.2, 0, 0) );
        scene.add( sphere1 );
        Material lambertMaterial = new LambertMaterial( 
        new Texture("images/CS.jpg") );
        Mesh sphere2 = new Mesh(sphereGeometry, lambertMaterial);
        sphere2.setPosition( new Vector(0, 0, 0) );
        scene.add( sphere2 );
        Material phongMaterial = new PhongMaterial(null);
        phongMaterial.uniforms.get("baseColor").data = 
        new Vector(0.5, 0.5, 1);
        Mesh sphere3 = new Mesh(sphereGeometry, phongMaterial);
        sphere3.setPosition( new Vector(2.2, 0, 0) );
        scene.add( sphere3 );
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        directionalLight.setDirection( 
        new Vector(-1, Math.sin(0.7*time), -2) );
        pointLight.setPosition( 
        new Vector(1, Math.sin(time), 0.8) );
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_6_1().run(1600, 1200);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}