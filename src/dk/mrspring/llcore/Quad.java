package dk.mrspring.llcore;

import java.util.Arrays;

/**
 * Created by Konrad on 30-01-2015.
 */
public class Quad extends Shape
{
    public Quad(Vector[] vectors)
    {
        this.setVectors(vectors);
    }

    public Quad(Vector v1, Vector v2, Vector v3, Vector v4)
    {
        super(v1, v2, v3, v4);
    }

    public Quad(float x, float y, float width, float height)
    {
        this(new Vector(x, y), new Vector(x + width, y), new Vector(x + width, y + height), new Vector(x, y + height));
    }

    public Quad(Vector v1, Vector v2)
    {
        this(v1.getX(), v2.getX(), v1.getY(), v2.getY());
    }

    @Override
    public Shape setVectors(Vector[] vectors)
    {
        this.vectors = Arrays.copyOf(vectors, 4);
        System.out.println(this.vectors.length);
        return this;
    }

    public float getWidth()
    {
        return getVector(1).getX() - getVector(0).getX();
    }

    public float getHeight()
    {
        return getVector(3).getY() - getVector(0).getY();
    }
}
