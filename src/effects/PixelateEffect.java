package src.effects;
import src.material.Material;
import src.math.Vector;
public class PixelateEffect extends Material{
    public PixelateEffect(float pixelSize, Vector resolution)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/PixelateEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("float", "pixelSize", pixelSize);
        addUniform("vec2", "resolution", resolution);
        locateUniforms();
    }
}
