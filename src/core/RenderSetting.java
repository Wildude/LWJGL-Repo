package src.core;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_POINT_SMOOTH;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL40.*;
public class RenderSetting {
    // setting name
    public String settingName;
    // default data value
    public Object data;
    public RenderSetting(String settingName, Object data)
    {
        this.settingName = settingName;
        this.data = data;
    }
    public void apply(){
        if (settingName.equals("pointSize"))glPointSize((int)data );
        else if (settingName.equals("roundedPoints"))
        {
            if ( (boolean)data )
            glEnable(GL_POINT_SMOOTH);
            else
            glDisable(GL_POINT_SMOOTH);
        }
        else if (settingName.equals("lineWidth"))glLineWidth( (int)data );
        else if (settingName.equals("doubleSide"))
        {
            if ( (boolean)data )
            glDisable(GL_CULL_FACE);
            else
            glEnable(GL_CULL_FACE);
        }
        else if (settingName.equals("wireframe"))
        {
            if ( (boolean)data )
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            else
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
        else System.out.println("Unknown render setting: " + settingName);
    }
}
