package dk.mrspring.llcore;

/**
 * Created by Konrad on 30-01-2015.
 */
public class Icon
{
    Shape[] shapes;
    public float scaleX, scaleY;

    public Icon(float xScale, float yScale, Shape... shapes)
    {
        this.shapes = shapes;
        this.scaleX = xScale;
        this.scaleY = yScale;
    }

    public Shape[] getShapes()
    {
        return this.shapes;
    }

    public Shape[] getShapes(float newSize)
    {
        return this.getShapes(newSize, newSize);
    }

    public Shape[] getShapes(float newScaleX, float newScaleY)
    {
        Shape[] newShapes = new Shape[shapes.length];
        for (int i = 0; i < shapes.length; i++)
        {
            Shape shape = shapes[i];
            float toScaleX = newScaleX / scaleX, toScaleY = newScaleY / scaleY;
            newShapes[i] = shape.scale(toScaleX, toScaleY);
        }
        return newShapes;
    }
}
