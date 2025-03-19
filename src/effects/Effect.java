package src.effects;
import src.material.Material;
import src.math.Vector;
public class Effect extends Material{
    public Effect()
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/Effect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        locateUniforms();
    }
}
