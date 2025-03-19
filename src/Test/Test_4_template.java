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
import src.light.*;
public class Test_4_template extends Base{
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
        Geometry geometry = new Geometry();
        ArrayList<Vector> positionList = new ArrayList<Vector>();
        for (double x=-3.2; x <= 3.2; x += 0.3)
        positionList.add( new Vector(x, Math.sin(x), 0) );
        float[] positionData = Vector.flattenList(positionList);
        geometry.addAttribute("vec3", "vertexPosition", positionData);
        geometry.vertexCount = positionList.size();
        Material lineMaterial = new LineMaterial("connected");
        lineMaterial.uniforms.get("baseColor").data = new Vector(1,0,1);
        lineMaterial.renderSettings.get("lineWidth").data = 4;
        Mesh lineMesh = new Mesh( geometry, lineMaterial );
        scene.add( lineMesh );
        Material pointMaterial = new PointMaterial();
        pointMaterial.uniforms.get("baseColor").data = 
        new Vector(1,1,0);
        pointMaterial.renderSettings.get("pointSize").data = 8;
        Mesh pointMesh = new Mesh( geometry, pointMaterial );
        scene.add(pointMesh);
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
            new Test_4_template().run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
