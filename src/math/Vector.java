package src.math;

public class Vector
{
    public double[] values;
    // initialize zero vector with given size
    public Vector(int size)
    {
        values = new double[size];
    }
    // initialize with given values
    public Vector(double... v)
    {
        values = new double[v.length];
        for (int i = 0; i < v.length; i++)
            values[i] = v[i];
    }
    public static double dot(Vector v, Vector w)
    {
        double c = 0;
        for (int i = 0; i < v.values.length; i++)
        c += v.values[i] * w.values[i];
        return c;
    }
    public String toString()
    {
        String s = "[";
        for (int i = 0; i < values.length; i++)
            s += String.format("%6.2f", values[i]);
        s += "]";
        return s;
    }
}