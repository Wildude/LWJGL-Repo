package src.light;
import src.math.Vector;
public class AmbientLight extends Light{
    public AmbientLight(Vector color){
        lightType = Light.AMBIENT;
        this.color = color;
    }
}