package src.effects;
import src.material.Material;
import src.math.Vector;
public class VignetteEffect extends Material{
    public VignetteEffect(float dimStart, float dimEnd, Vector dimColor)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/VignetteEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("float", "dimStart", dimStart);
        addUniform("float", "dimEnd", dimEnd);
        addUniform("vec3", "dimColor", dimColor);
        locateUniforms();
    }
}
