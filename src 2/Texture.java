public class Texture extends Material {

    private ColorImage texture;
    //loading a new file into as texture
    public Texture(String filename) {
        this.texture = ImageLoader.loadImage(filename);
    }

    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        //getting the directional ray from the light to the intersection
        Vector killme = li.computeLightDirection(i.getPosition());
        double dot = i.getNormal().dot(killme);
        if (dot < 0){
        //part that doesnt get light
            return new Color(0, 0, 0);
        }
        //making the width and the height equal to the texture's width and height
            int imageWidth = texture.getWidth();
            int imageHeight = texture.getHeight();
            double imageX = i.getImageX();
            double imageY = i.getImageY();
            //multiplying the width and height by the shapes width and height
            //adding abs value j for fun fix that 
            int texelX = Math.abs((int) (imageX * (imageWidth)));
            int texelY = Math.abs((int) (imageY * (imageHeight)));
            //getting the color at the certain place
            Color textureColor = texture.getColor(texelX,texelY);
            Color c = textureColor.scale(dot);
            return c.shade(li.computeLightColor(i.getPosition()));
    }

}
