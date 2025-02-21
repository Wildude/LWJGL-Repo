package src.extras;
import src.core.Mesh;
import src.material.LineMaterial;
public class AxesHelper extends Mesh{
    public AxesHelper(float axisLength, int lineWidth)
    {
        super( new AxesGeometry(axisLength),
        new LineMaterial() );
        this.material.uniforms.get("useVertexColors").data = 1;
        this.material.renderSettings.get("lineWidth").data = lineWidth;
    }
}
