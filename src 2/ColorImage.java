public class ColorImage {
    private Color[][] image;
    private int width,height;
    private Color backgroundColor;
    public ColorImage(int width, int height){
        this.width = width;
        this.height = height;
        //setting background color to an arbitrary value (this matters for the percentTimer)
        backgroundColor = new Color(0.01, 0.01, 0.01);
        image = new Color[width][height];
        //setting every pixel to this color
        for(int i = 0; i < image.length; i++){
            for(int j = 0; j < image[i].length; j++){
                image[i][j] = backgroundColor;
            }
        }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Color getBackgroundColor(){
        return backgroundColor;
    }
    //getting color at specified col and row
    public Color getColor(int col, int row){
        return image[col][row];
    }
    public void setColor(int col, int row, Color c){
        image[col][row] = c;
    }
    //checking if color c is exactly the same as color d USING A TERNARY YAY
    public boolean sameColor(Color c, Color d){
        boolean ternary = (c.getR() == d.getR() && c.getB() == d.getB() && c.getG() == d.getG() ?  true: false);
        return ternary;    
    }
}
