package src.effects;
import src.material.Material;
import src.math.Vector;
public class TintEffect extends Material{
    public TintEffect(Vector tintColor)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/TintEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("vec3", "tintColor", tintColor);
        locateUniforms();
    }
}
