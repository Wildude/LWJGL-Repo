package src.math;

public class Surface {
    public interface Function{
        Vector apply(double u, double v);
    }
    public Function function;
    public Surface(Function function){
        this.function = function;
    }
    // get a 2D array of points on the surface
    //   represented by this function
    public Vector[][] getPoints(double uStart, double uEnd, int uResolution,
        double vStart, double vEnd, int vResolution){
            Vector[][] points = new Vector[uResolution+1][vResolution+1];
            double deltaU = (uEnd - uStart) / uResolution;
            double deltaV = (vEnd - vStart) / vResolution;
            for (int uIndex=0; uIndex < uResolution+1; uIndex++)
            {
                for (int vIndex=0; vIndex < vResolution+1; vIndex++)
                {
                    double u = uStart + uIndex * deltaU;
                    double v = vStart + vIndex * deltaV;
                    points[uIndex][vIndex] = 
                    this.function.apply(u,v);
                }
            }
        return points;
    } 
    public Vector[][] getUVs(
    double uStart, double uEnd, int uResolution, 
    double vStart, double vEnd, int vResolution)
    {
        Vector[][] uvs = new Vector[uResolution+1][vResolution+1];
        for (int uIndex = 0; uIndex < uResolution+1; uIndex++)
        {
            for (int vIndex = 0; vIndex < vResolution+1; vIndex++)
            {
                double u = (double)uIndex/uResolution;
                double v = (double)vIndex/vResolution;
                uvs[uIndex][vIndex] = new Vector(u,v);
            }
        }
        return uvs;
    }
    // calculate normal vectors to surface (vertex normals)
 public Vector[][] getNormals(
    double uStart, double uEnd, int uResolution,
    double vStart, double vEnd, int vResolution)
    {
        Vector[][] normals = 
        new Vector[uResolution+1][vResolution+1];
        double deltaU = (uEnd - uStart) / uResolution;
        double deltaV = (vEnd - vStart) / vResolution;
        for (int uIndex = 0; uIndex < uResolution+1; uIndex++)
        {
            for (int vIndex = 0; vIndex < vResolution+1; vIndex++)
            {
            double u = uStart + uIndex * deltaU;
            double v = vStart + vIndex * deltaV;
            double h = 0.0001;
            Vector P0 = function.apply(u, v);
            Vector P1 = function.apply(u+h, v);
            Vector P2 = function.apply(u, v+h);
            normals[uIndex][vIndex] = 
            Vector.calcNormal(P0, P1, P2);
            }
        }
        return normals;
    }
}
