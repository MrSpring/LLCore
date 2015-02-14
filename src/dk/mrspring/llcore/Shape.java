package dk.mrspring.llcore;

import java.util.Collection;

/**
 * Created by Konrad on 30-01-2015.
 */
public class Shape
{
    Vector[] vectors;

    public Shape(Vector... vectors)
    {
        this.vectors = vectors;
    }

    public Vector getVector(int index)
    {
        if (index < vectors.length && index >= 0)
            return vectors[index];
        else return null;
    }

    public Vector[] getVectors()
    {
        return vectors;
    }

    public Shape setVectors(Vector[] vectors)
    {
        this.vectors = vectors;
        return this;
    }

    public Shape translate(float x, float y)
    {
        for (Vector vector : vectors)
            vector.translate(x, y);
        return this;
    }

    public static void translateArray(Shape[] shapes, float x, float y)
    {
        for (Shape shape : shapes)
            shape.translate(x, y);
    }

    public static void translateArray(Collection<Shape> shapes, float x, float y)
    {
        for (Shape shape : shapes)
            shape.translate(x, y);
    }

    public Shape setColor(Color color)
    {
        for (Vector vector : vectors)
            vector.color = color;
        return this;
    }

    public Shape setAlpha(float alpha)
    {
        for (Vector vector : vectors)
            vector.alpha = alpha;
        return this;
    }

    public Shape scale(float xSize, float ySize)
    {
        Vector[] newVectors = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++)
        {
            Vector vector = new Vector(vectors[i].getX(), vectors[i].getY());
            vector.setX(vector.getX() * xSize);
            vector.setY(vector.getY() * ySize);
            newVectors[i] = vector;
        }
        return new Shape(newVectors);
    }

    public Shape scale(float size)
    {
        return this.scale(size, size);
    }
}
