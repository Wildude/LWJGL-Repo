package src.light;
import src.math.Vector;
public class PointLight extends Light{
    public PointLight(Vector color, Vector position, 
    Vector attenuation)
    {
        lightType = Light.POINT;
        this.color = color;
        setPosition(position);
        this.attenuation = attenuation;
    }
    // sets default attenuation coefficients
    public PointLight(Vector color, Vector position)
    {
    this(color, position, new Vector(1, 0, 0.1));
    }
}
