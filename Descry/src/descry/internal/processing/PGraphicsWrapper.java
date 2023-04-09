package descry.internal.processing;

import descry.internal.Graphics;
import processing.core.PGraphics;

public class PGraphicsWrapper implements Graphics {

    private final PGraphics _g;

    public PGraphicsWrapper(PGraphics g) {
        _g = g;
    }

    @Override
    public int getSizeX() {
        return _g.width;
    }

    @Override
    public int getSizeY() {
        return _g.height;
    }

    @Override
    public void clear() {
        _g.clear();
    }

    @Override
    public void pushMatrix() {
        _g.pushMatrix();
    }

    @Override
    public void popMatrix() {
        _g.popMatrix();
    }

    @Override
    public void translate(float x, float y) {
        _g.translate(x, y);
    }

    @Override
    public void scale(float scale) {
        _g.scale(scale);
    }

    @Override
    public void rotate(float angle) {
        _g.rotate(angle);
    }

    @Override
    public void beginShape() {
        _g.beginShape();
    }

    @Override
    public void vertex(float x, float y) {
        _g.vertex(x, y);
    }

    @Override
    public void vertex(float x, float y, float u, float v) {
        _g.vertex(x, y, u, v);
    }

    @Override
    public void endShape() {
        _g.endShape();
    }

    @Override
    public int color(int grey) {
        return _g.color(grey);
    }

    @Override
    public int color(int grey, int alpha) {
        return _g.color(grey, alpha);
    }

    @Override
    public int color(int r, int g, int b) {
        return _g.color(r, g, b);
    }

    @Override
    public int color(int r, int g, int b, int alpha) {
        return _g.color(r, g, b, alpha);
    }

    @Override
    public void background(int grey) {
        _g.background(grey);
    }

    @Override
    public void background(int grey, int alpha) {
        _g.background(grey, alpha);
    }

    @Override
    public void background(int r, int g, int b) {
        _g.background(r, g, b);
    }

    @Override
    public void background(int r, int g, int b, int alpha) {
        _g.background(r, g, b, alpha);
    }

    @Override
    public void strokeWeight(float weight) {
        _g.strokeWeight(weight);
    }

    @Override
    public void noStroke() {
        _g.noStroke();
    }

    @Override
    public void strokeColor(int grey) {
        _g.stroke(grey);
    }

    @Override
    public void strokeColor(int grey, int alpha) {
        _g.stroke(grey, alpha);
    }

    @Override
    public void strokeColor(int r, int g, int b) {
        _g.stroke(r, g, b);
    }

    @Override
    public void strokeColor(int r, int g, int b, int alpha) {
        _g.stroke(r, g, b, alpha);
    }

    @Override
    public void noFill() {
        _g.noFill();
    }

    @Override
    public void fillColor(int grey) {
        _g.fill(grey);
    }

    @Override
    public void fillColor(int grey, int alpha) {
        _g.fill(grey, alpha);
    }

    @Override
    public void fillColor(int r, int g, int b) {
        _g.fill(r, g, b);
    }

    @Override
    public void fillColor(int r, int g, int b, int alpha) {
        _g.fill(r, g, b, alpha);
    }

    @Override
    public void line(float x0, float y0, float x1, float y1) {
        _g.line(x0, y0, x1, y1);
    }

    @Override
    public void textAlign(int modeX, int modeY) {
        _g.textAlign(modeX, modeY);
    }

    @Override
    public void text(String text, float x, float y) {
        _g.text(text, x, y);
    }

    @Override
    public void textSize(float size) {
        _g.textSize(size);
    }

    @Override
    public void point(float x, float y) {
        _g.point(x, y);
    }

    @Override
    public void rectangleMode(int mode) {
        _g.rectMode(mode);
    }

    @Override
    public void rectangle(float x0, float y0, float x1, float y1) {
        _g.rect(x0, y0, x1, y1);
    }

    @Override
    public void circle(float x, float y, float radius) {
        _g.circle(x, y, radius);
    }

    @Override
    public void ellipseMode(int mode) {
        _g.ellipseMode(mode);
    }

    @Override
    public void ellipse(float x0, float y0, float x1, float y1) {
        _g.ellipse(x0, y0, x1, y1);
    }

    @Override
    /**
     * Make sure arrows point down lol.
     */
    public void downArrow(float x0, float y0, float x1, float y1) {
        _g.line(x0, y0, x1, y1);
        float triLen = (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2)) * 0.2f;
        float triX1 = x0 + triLen/2;
        float triY1 = y0 - triLen;
        float triX2 = x0 - triLen/2;
        float triY2 = y0 - triLen;
        _g.triangle(x0, y0, triX1, triY1, triX2, triY2);

    }
}
