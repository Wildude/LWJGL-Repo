package src.extras;
import src.core.Mesh;
import src.light.PointLight;
import src.geometry.SphereGeometry;
import src.material.SurfaceMaterial;
import src.math.Vector;
public class PointLightHelper extends Mesh{
    public PointLightHelper(PointLight light, double size, 
    int lineWidth)
    {
        super( new SphereGeometry(size, 4, 2), new SurfaceMaterial() );
        this.material.uniforms.get("baseColor").data = light.color;
        this.material.renderSettings.get("wireframe").data = true;
        this.material.renderSettings.get("doubleSide").data = true;
        this.material.renderSettings.get("lineWidth").data = lineWidth;
    }

    public PointLightHelper(PointLight light)
    {
        this(light, 0.1, 1);
    }
}
