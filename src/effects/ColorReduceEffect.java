package src.effects;
import src.material.Material;
import src.math.Vector;
public class ColorReduceEffect extends Material{
    public ColorReduceEffect(float levels)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/ColorReduceEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("float", "levels", levels);
        locateUniforms();
    }
}
