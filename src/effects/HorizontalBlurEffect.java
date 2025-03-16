package src.effects;
import src.material.Material;
import src.math.Vector;
public class HorizontalBlurEffect extends Material{
    public HorizontalBlurEffect(Vector textureSize, int blurRadius)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/HorizontalBlurEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("vec2", "textureSize", textureSize);
        addUniform("int", "blurRadius", blurRadius);
        locateUniforms();
    }
}
