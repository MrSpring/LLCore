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
    boolean allowTextures;

    public DrawingHelper setAllowTextures(boolean allowTextures)
    {
        this.allowTextures = allowTextures;
        return this;
    }

    public void setZIndex(double zIndex)
    {
        this.zIndex = zIndex;
    }

    public DrawingHelper drawButtonThingy(Quad quad, float alphaProgress, boolean enabled, Color startColor, float startAlpha, Color endColor, float endAlpha)
    {
        float x = quad.getVector(0).getX(), y = quad.getVector(0).getY(), w = quad.getWidth(), h = quad.getHeight();

        this.drawShape(new Quad(x + 1, y, w - 2, h).setColor(Color.BLACK).setAlpha(0.25F));
        this.drawShape(new Quad(x, quad.getVector(0).getY() + 1, 1, quad.getHeight() - 2).setColor(Color.BLACK).setAlpha(0.25F));
        this.drawShape(new Quad(x + w - 1, quad.getVector(0).getY() + 1, 1, quad.getHeight() - 2).setColor(Color.BLACK).setAlpha(0.25F));
        this.drawShape(new Quad(x + 1, y + 1, w - 2, 1).setColor(Color.WHITE));
        this.drawShape(new Quad(x + 1, y + 2, 1, h - 4).setColor(Color.WHITE));
        this.drawShape(new Quad(x + w - 2, y + 2, 1, h - 3).setColor(Color.LT_GREY));
        this.drawShape(new Quad(x + 1, y + h - 2, w - 3, 1).setColor(Color.LT_GREY));

        if (enabled && alphaProgress > 0)
            this.drawShape(new Quad(
                    new Vector(x + 2, y + 2, startColor, alphaProgress * startAlpha),
                    new Vector(x + w - 2, y + 2, startColor, alphaProgress * startAlpha),
                    new Vector(x + w - 2, y + h - 2, endColor, alphaProgress * endAlpha),
                    new Vector(x + 2, y + h - 2, endColor, alphaProgress * endAlpha)));

        return this;
    }

    public DrawingHelper drawButtonThingy(Quad quad, float alphaProgress, boolean enabled)
    {
        return this.drawButtonThingy(quad, alphaProgress, enabled, Color.CYAN, 0.25F, Color.BLUE, 0.5F);
    }

    public DrawingHelper drawVerticalLine(Vector start, float length, float width, boolean shadow)
    {
        if (shadow)
            this.drawShape(new Quad(start.getX() + (width / 2) + 1, start.getY() + 1, width, length).setColor(Color.DK_GREY));
        return this.drawShape(new Quad(start.getX() + (width / 2), start.getY(), width, length));
    }

    public DrawingHelper drawHorizontalLine(Vector start, float length, float width, boolean shadow)
    {
        if (shadow)
            this.drawShape(new Quad(start.getX() + 1, start.getY() - (width / 2) + 1, length, width).setColor(Color.DK_GREY));
        return this.drawShape(new Quad(start.getX(), start.getY() - (width / 2), length, width));
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
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glDisable(GL_BLEND);

        return this;
    }

    public DrawingHelper drawTexturedShape(Shape shape)
    {
        glDisable(GL_LIGHTING);
        glEnable(GL_BLEND);
        glAlphaFunc(GL_GREATER, 0.01F);
        glEnable(GL_TEXTURE_2D);
        glColor4f(1.0F, 1.0F, 1.0F, 1F);

        float texMapScale = 0.001953125F;

        Tessellator tessellator = Tessellator.getInstance();

        tessellator.getWorldRenderer().startDrawingQuads();
        Vector[] vectors = new Vector[]{
                shape.getVector(3),
                shape.getVector(2),
                shape.getVector(1),
                shape.getVector(0)
        };
        for (Vector vector : vectors)
        {
            tessellator.getWorldRenderer().addVertexWithUV(vector.getX(), vector.getY(), zIndex, vector.getU() * texMapScale, vector.getV() * texMapScale);
        }

        tessellator.draw();

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
