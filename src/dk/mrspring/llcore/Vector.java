package dk.mrspring.llcore;

/**
 * Created by Konrad on 30-01-2015.
 */
public class Vector
{
    Color color;
    float alpha;
    float x;
    float y;
    int u;
    int v;

    public Vector(float x, float y, int u, int v, Color color, float alpha)
    {
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.color = color;
        this.alpha = alpha;
    }

    public Vector(float x, float y, int u, int v)
    {
        this(x, y, u, v, Color.WHITE, 1F);
    }

    public Vector(float x, float y, Color color, float alpha)
    {
        this(x, y, 0, 0, color, alpha);
    }

    public Vector(float x, float y)
    {
        this(x, y, 0, 0);
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public int getU()
    {
        return u;
    }

    public int getV()
    {
        return v;
    }

    public Vector setX(float x)
    {
        this.x = x;
        return this;
    }

    public Vector setY(float y)
    {
        this.y = y;
        return this;
    }

    public Vector setU(int u)
    {
        this.u = u;
        return this;
    }

    public Vector setV(int v)
    {
        this.v = v;
        return this;
    }

    public Vector translate(float x, float y)
    {
        this.x += x;
        this.y += y;
        return this;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setAlpha(float alpha)
    {
        this.alpha = alpha;
    }
}
