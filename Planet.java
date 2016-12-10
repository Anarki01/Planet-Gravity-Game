import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Planet extends JPanel {

    private BufferedImage imgPlanet = null;
    private MyVector pLocation;
    private float radius;
    private float perimeter;

    private int spriteGlowX;
    private int spriteGlowY;

    public static final float MAX_VELOCITY = 15;
    public static final float MAX_ACCELERATION = 0.018f;
    private float thisPlanetsMax;

    private float planetaryAcceleration;

    // Constructor
    public Planet(MyVector location, float radius, int spriteGlowX, int spriteGlowY, BufferedImage imgPlanet) {
        this.pLocation = location;
        this.radius = radius;
        this.imgPlanet = imgPlanet;
        perimeter = 2 * (float)Math.PI * this.radius;
        this.spriteGlowX = spriteGlowX;
        this.spriteGlowY = spriteGlowY;
//        planetaryAcceleration = Convert.map(radius, 110, 40, 0, MAX_ACCELERATION);
        planetaryAcceleration = MAX_ACCELERATION * (1 - (this.radius / (175 + 10)));
        if(radius == 100)thisPlanetsMax = 3;
        if(radius == 75)thisPlanetsMax = 4;
        if(radius == 50)thisPlanetsMax = 7;
        if(radius == 41)thisPlanetsMax = 15;
    }
    public Planet(float x, float y, float radius, int spriteGlowX, int spriteGlowY, BufferedImage imgPlanet) {
        this(new MyVector(x, y), radius, spriteGlowX, spriteGlowY, imgPlanet);
    }


    // Getters and Setters
    public MyVector getpLocation() {
        return pLocation;
    }

    public void setpLocation(MyVector pLocation) {
        this.pLocation = pLocation;
    }

    public float getRadius() {
        return radius;
    }
    public float getThisPlanetsMax(){
        return thisPlanetsMax;
    }
    public float getPlanetaryAcceleration() {
        return planetaryAcceleration;
    }

    public float getPerimeter() {
        return perimeter;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public BufferedImage getImgPlanet() {
        return imgPlanet;
    }

    public void setImgPlanet(BufferedImage imgPlanet) {
        this.imgPlanet = imgPlanet;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgPlanet, (int) pLocation.x - (int)radius -spriteGlowX, (int) pLocation.y - (int)radius -spriteGlowY, null);
    }

}