import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {
    private BufferedImage imgBackground;
    ArrayList<Planet> planets = new ArrayList<Planet>();
    ArrayList<Player> players = new ArrayList<Player>();
    private int[] planetID = new int[4]; //Indicates on which planet the respective player is on
    private int[][] keys = new int[4][2]; //Keyboard inputs for player 1
    private int playerbounds = 20; //Used for player collision
    private boolean finished;
    public Game(JFrame frame) {
        imgBackground = ResourceLoader.loadImage("background.png");
        finished = false;
        generatePlanets();
        addPlayers(frame);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgBackground, 0, 0, null);
        for(Planet i : planets){
            i.paint(g);
        }
        for(Player i : players){
            if(i.getAlive())i.paint(g);
        }
    }

    public void move() {
        for(int i = 0; i < players.size(); i++) {
            if (players.get(i).getAlive()) {
                players.get(i).move(planetID[i]);
                if (players.get(i).getJumping()) {
                    for (int j = 0; j < planets.size(); j++) {
                        if (j != planetID[i])
                            if (players.get(i).checkCollision(planets.get(j).getpLocation().x, planets.get(j).getpLocation().y, planets.get(j).getRadius(), planets.get(j).getBbounds())) {
                                planetID[i] = j;
                                break;
                            }
                    }
                }
            }
        }
        collision(); //Check player Collisions
    }
    public boolean getFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    public void collision() {
        for(int i = 0; i < players.size(); i++) {
            for(int j = i+1; j < players.size(); j++) {
                if (players.get(i).getAlive() && players.get(j).getAlive() && planetID[i] == planetID[j]) {
                    if (players.get(i).getAngle() > players.get(j).getAngle() - playerbounds && players.get(i).getAngle() < players.get(j).getAngle() + playerbounds) {
                        findSlowerPlayer(i, j);
                        int x = checkIfWinner();
                        if(x != -1)Winner(x);
                    }
                }
            }
        }
    }
    public void findSlowerPlayer(int i, int j){
        if (players.get(i).getAcceleration() > players.get(j).getAcceleration())killPlayer(j);
        else killPlayer(i);
    }
    public void killPlayer(int i){
        players.get(i).setAlive(false);
        System.out.println("player " + (i+1) + " was killed");
    }
    public int checkIfWinner(){
        int x = 0;
        int j = 0;
        for(int i = 0; i < players.size(); i++){
            if(!players.get(i).getAlive())x++;
            else j = i;
        }
        if(x == 3)return j;
        else return -1;
    }
    public void addPlayers(JFrame frame){
        for(int i = 0; i < 4; i++) {
            randomSpawn(i);
            players.add(new Player(planets.get(planetID[i]).getpLocation().x, planets.get(planetID[i]).getpLocation().y, (int) planets.get(planetID[i]).getRadius(), planets.get(planetID[i]).getBbounds(), ResourceLoader.loadImage("animate2.png"), keys[i]));
        }
        setPlayerKeys();
        for(int i = 0; i < players.size(); i++){
            players.get(i).addKeyListener(frame); //Enables use of Keyboard inputs for players
        }
    }
    public void randomSpawn(int i){ //Random a planet for player to spawn on
        planetID[i] = (int)(Math.random()*planets.size());
        //System.out.println((int)(Math.random()*planets.size()));     //printing out values in menu screen, Investigate this
    }
    public void setPlayerKeys(){
        //Player1
        keys[0][0] = KeyEvent.VK_A; //Move
        keys[0][1] = KeyEvent.VK_S; //Jump
        //Player 2
        keys[1][0] = KeyEvent.VK_O; //Move
        keys[1][1] = KeyEvent.VK_P; //Jump
        //Player 3
        keys[2][0] = KeyEvent.VK_C; //Move
        keys[2][1] = KeyEvent.VK_V; //Jump
        //Player 4
        keys[3][0] = KeyEvent.VK_N; //Move
        keys[3][1] = KeyEvent.VK_M; //Jump
    }
    public void generatePlanets(){
        planets.add(new Planet(56, 74, 100, 6, ResourceLoader.loadImage("planets/orangeplanet.png")));
        planets.add(new Planet(525, 356, 50, 20, ResourceLoader.loadImage("planets/testpurpleplanet.png")));
        planets.add(new Planet(550, 88, 75, 10, ResourceLoader.loadImage("planets/greenplanet.png")));
        planets.add(new Planet(250, 350, 50,14, ResourceLoader.loadImage("planets/redplanet.png")));
    }
    public void Winner(int playerID){
        System.out.println("Winner is " + (playerID+1)); //Do winning stuff here
        try{Thread.sleep(1000);}
        catch(Exception e){}
        finished = true;
    }
}
