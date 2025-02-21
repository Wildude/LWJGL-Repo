package src.Test;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import src.core.*;
import src.geometry.*;
import src.material.*;
import src.math.*;
public class Test_4_1 extends Base{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPosition( new Vector(0,0,3) );
        Geometry geometry = new BoxGeometry();
        Material material = new SurfaceMaterial();
        // to change value from default, for example:
        // material.renderSettings.get("pointSize").data = 32;
        material.uniforms.get("useVertexColors").data = 1;
        mesh = new Mesh( geometry, material );
        scene.add( mesh );
    }
    public void update()
    {
        mesh.rotateY( 0.0123f, true );
        mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
    }
    // driver method
    public static void main(String[] args)
    {
        try{
            new Test_4_1().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
