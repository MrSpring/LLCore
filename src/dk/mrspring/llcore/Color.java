package dk.mrspring.llcore;

/**
 * Created by Konrad on 30-01-2015.
 */
public class Color
{
    public static final Color WHITE = new Color(1F, 1F, 1F);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color LT_GREY = new Color(0.75F, 0.75F, 0.75F);
    public static final Color DK_GREY = new Color(0.25F, 0.25F, 0.25F);
    public static final Color GREY = new Color(0.5F, 0.5F, 0.5F);
    public static final Color RED = new Color(1F, 0F, 0F);
    public static final Color GREEN = new Color(0F, 1F, 0F);
    public static final Color BLUE = new Color(0F, 0F, 1F);
    public static final Color CYAN = new Color(0F, 1F, 1F);

    // RGB Values from 0 to 1
    public float r, g, b;

    public Color(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(int r, int g, int b)
    {
        this((float) r / 255, (float) g / 255, (float) b / 255);
    }
}
