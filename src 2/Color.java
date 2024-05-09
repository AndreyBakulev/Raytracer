public class Color {
    private double r,g,b;

    //just making colors to make life easier
    public static Color Red(){
        return new Color(.9, .1, .15);
    }
    public static Color Green(){
        return new Color(.05, .7, .06);
    }
    public static Color Blue(){
        return new Color(.05, .5, .6);
    }
    public static Color Yellow(){
        return new Color(.9, .8, .1);
    }
    public static Color Magenta(){
        return new Color(.9, .2, .9);
    }
    public static Color Cyan(){
        return new Color(.2, .8, .85);
    }
    public static Color White(){
        return new Color(1, 1, 1);
    }
    public static Color Black(){
        return new Color(0, 0, 0);
    }
    public static Color Dark(){
        return new Color(0.1,0.1,0.1);
    }
    public static Color MarineBlue(){
        return new Color(0, 0.2666,0.50588235);
    }
    public static Color Jade(){
        return new Color(0, 0.76078,0.48235);
    }
    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    //returning the r,g,b of the color
    public double getR(){
        return r;
    }
    public double getG(){
        return g;
    }
    public double getB(){
        return b;
    }
    //adding two color's r g b values
    public Color add(Color C){
        return new Color(this.r + C.getR(), this.g + C.getG(), this.b+C.getB());
    }
    //multiply the r g b values of this color by a scalar
    public Color scale(double scalar){
        return new Color(this.r*scalar,this.g*scalar,this.b*scalar);
    }
    //whatever this does
    public int toARGB(){
        int ir = (int)(Math.min(Math.max(r,0),1) * 255 + 0.1);
        int ig = (int)(Math.min(Math.max(g,0),1) * 255 + 0.1);
        int ib = (int)(Math.min(Math.max(b,0),1) * 255 + 0.1);
        return (ir << 16) | (ig << 8) | (ib << 0);
        //bit shifting 
    }
    //multiplies the color by the shading color c
    public Color shade(Color c){
        return new Color(this.getR()*c.getR(), this.getG()*c.getG(), this.getB()*c.getB());
    }
    //tints the color by the tinting color c
    public Color tint(Color c){
        double newR = r + (1 - r)*c.getR();
        double newG = g + (1 - g)*c.getG();
        double newB = b + (1 - b)*c.getB();
        return new Color(newR, newG, newB);

    }

}
