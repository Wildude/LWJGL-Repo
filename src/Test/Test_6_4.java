package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.light.*;
import src.extras.*;
import src.effects.*;
import src.core.*;
import src.geometry.*;
import src.material.*;
import src.math.*;
public class Test_6_4 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh, sphere;
    public MovementRig rig;
    public PostProcessor glowPass, comboPass;
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
        Geometry sphereGeometry = new SphereGeometry();
        Material sphereMaterial = new TextureMaterial( 
        new Texture("images/CS.jpg") );
        sphere = new Mesh( sphereGeometry, sphereMaterial );
        sphere.setPosition( new Vector(0,1,0) );
        scene.add( sphere );
        // set up postprocessing
        // glow scene
        Scene glowScene = new Scene();
        Material redMaterial = new SurfaceMaterial();
        redMaterial.uniforms.get("baseColor").data = new Vector(1,0,0);
        Mesh glowSphere = new Mesh(sphereGeometry, redMaterial);
        glowSphere.transform = sphere.transform;
        glowScene.add( glowSphere );
        // glow postprocessing
        Vector size = new Vector(800, 600);
        RenderTarget glowTarget = new RenderTarget( size );
        glowPass = new PostProcessor(renderer, glowScene, 
        camera, glowTarget);
        glowPass.addEffect( new HorizontalBlurEffect(size, 50) );
        glowPass.addEffect( new VerticalBlurEffect(size, 50) );
        // combining results of glow effect with main scene
        comboPass = new PostProcessor(renderer, scene, camera, null);
        comboPass.addEffect( new AdditiveBlendEffect(
        glowTarget.texture, 1, 3) );
    }
    public void update()
    {
        //mesh.rotateY( 0.0123f, true );
        //mesh.rotateX( 0.0237f, true );
        glowPass.render();
        comboPass.render();
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_6_4().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
