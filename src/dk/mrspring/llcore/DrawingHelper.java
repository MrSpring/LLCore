package dk.mrspring.llcore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Konrad on 30-01-2015.
 */
public class DrawingHelper
{
    double zIndex = 0;

    public void setZIndex(double zIndex)
    {
        this.zIndex = zIndex;
    }

    public DrawingHelper drawShape(Shape shape)
    {
        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_CULL_FACE);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);

        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getWorldRenderer().startDrawingQuads();

        float texMapScale = 0.00390625F;

        for (Vector vector : shape.getVectors())
        {
            tessellator.getWorldRenderer().setColorRGBA_F(vector.color.r, vector.color.g, vector.color.b, vector.alpha);
            tessellator.getWorldRenderer().addVertexWithUV(vector.getX(), vector.getY(), zIndex, vector.getU() * texMapScale, vector.getV() * texMapScale);
        }

        tessellator.draw();

//        glColor4f(1, 1, 1, 1);
        glShadeModel(GL_FLAT);

        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);

        return this;
    }

    public DrawingHelper drawShapes(Shape[] shapes)
    {
        for (Shape shape : shapes)
            this.drawShape(shape);
        return this;
    }

    public DrawingHelper drawIcon(Icon icon, Quad placement)
    {
        if (icon != null)
            for (Shape shape : icon.getShapes(placement.getWidth(), placement.getHeight()))
                this.drawShape(shape.translate(placement.getVector(0).getX(), placement.getVector(0).getY()));
        return this;
    }

    public int drawText(String text, Vector placement, int color, boolean shadow, int wrap, VerticalTextAlignment verticalAlignment, HorizontalTextAlignment horizontalAlignment)
    {
        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
        int wrapLength = (wrap == -1) ? 100000 : wrap;
        float textX, textY;
        List<String> lines = renderer.listFormattedStringToWidth(text, wrapLength);
        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            int lineLength = renderer.getStringWidth(line);
            textX = placement.getX();
            if (verticalAlignment == VerticalTextAlignment.CENTER)
                textX -= (lineLength / 2);
            else if (verticalAlignment == VerticalTextAlignment.RIGHT)
                textX -= lineLength;

            textY = placement.getY() + (i * 9);
            if (horizontalAlignment == HorizontalTextAlignment.CENTER)
                textY -= (lines.size() * 9) / 2;
            else if (horizontalAlignment == HorizontalTextAlignment.BOTTOM)
                textY -= lines.size() * 9;
            renderer.drawString(line, textX, textY, color, shadow);
        }
        return lines.size();
    }

    public enum HorizontalTextAlignment
    {
        TOP,
        CENTER,
        BOTTOM
    }

    public enum VerticalTextAlignment
    {
        LEFT,
        CENTER,
        RIGHT
    }
}
