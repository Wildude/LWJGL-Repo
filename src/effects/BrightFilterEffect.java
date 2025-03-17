package src.effects;
import src.material.Material;
import src.math.Vector;
public class BrightFilterEffect extends Material{
    public BrightFilterEffect(float threshold)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/BrightFilterEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("float", "threshold", threshold);
        locateUniforms();
    }
}
