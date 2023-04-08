package descry.internal;

interface Graphics {

    int getSizeX();

    int getSizeY();

    void clear();

    //region Transform Manipulation
    void pushMatrix();

    void popMatrix();

    void translate(float x, float y);

    void scale(float scale);

    void rotate(float angle);
    //endregion

    //region Polygons
    void beginShape();

    void vertex(float x, float y);

    void vertex(float x, float y, float u, float v);

    void endShape();
    //endregion

    //region Colors
    int color(int grey);

    int color(int grey, int alpha);

    int color(int r, int g, int b);

    int color(int r, int g, int b, int alpha);

    void background(int grey);

    void background(int grey, int alpha);

    void background(int r, int g, int b);

    void background(int r, int g, int b, int alpha);

    void strokeWeight(float weight);

    void textAlign(int x, int y);

    void noStroke();

    void strokeColor(int grey);

    void strokeColor(int grey, int alpha);

    void strokeColor(int r, int g, int b);

    void strokeColor(int r, int g, int b, int alpha);

    void noFill();

    void fillColor(int grey);

    void fillColor(int grey, int alpha);

    void fillColor(int r, int g, int b);

    void fillColor(int r, int g, int b, int alpha);
    //endregion

    //region Draw Commands
    void line(float aX, float aY, float bX, float bY);

    void text(String text, float x, float y);

    void textSize(float size);

    void point(float x, float y);

    void rectangle(float x0, float y0, float x1, float y1);

    void circle(float x, float y, float radius);

    void ellipse(float x0, float y0, float x1, float y1);
    //endregion

}
