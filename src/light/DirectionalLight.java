package src.light;
import src.math.Vector;
public class DirectionalLight extends Light{
    public DirectionalLight(Vector color, Vector direction){
    lightType = Light.DIRECTIONAL;
    this.color = color;
    setDirection(direction);
    }
}