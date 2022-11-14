import java.awt.Color;
import gui.*;
import java.util.Random;

public class PlusieursBoidsSimulator implements Simulable{
  private Boids[] listBoids;
  private Color[] color1;
  private Color[] color2;
  private  GUISimulator window;
  private EventManager manager;


  public PlusieursBoidsSimulator(Boids[] listBoids, GUISimulator window, EventManager manager){
    this.listBoids = listBoids;
    this.window = window;
    this.manager = manager;
    int p = listBoids.length;
    this.color1 = new Color[p];
    this.color2 = new Color[p];
    for(int k = 0; k<p; k++){
      color1[k] = this.randomColor();
      color2[k] = this.randomColor();
    }
    this.draw();

  }
  public Color randomColor(){
    Random randomGenerator = new Random();
    int red = randomGenerator.nextInt(256);
    int green = randomGenerator.nextInt(256);
    int blue = randomGenerator.nextInt(256);
    Color randomColor = new Color(red,green,blue);
    return randomColor;
  }
  public void draw(){
    int p = listBoids.length;
    for(int k = 0; k<p; k++){
      int n = listBoids[k].getPositions().length;
      for ( int i = 0; i<n; i++) {
        int x = listBoids[k].getPositions()[i].x;
        int y = listBoids[k].getPositions()[i].y;
        int xh = listBoids[k].getHeads()[i].x;
        int yh = listBoids[k].getHeads()[i].y;
        window.addGraphicalElement(new Oval(xh, yh, color1[k], color1[k], 6));
        window.addGraphicalElement(new Oval(x, y, color2[k], color2[k], listBoids[k].getRayon()*2));
      }
    }
  }

  @Override
  public void next() {
    int p = listBoids.length;
    manager.next();
    for(int k = 0; k<p; k++){
      manager.addEvent(new BoidsEvent(manager.getCurrentDate()+ listBoids[k].getPas(), listBoids[k], window));
    }
    window.reset();
    this.draw();
  }

  @Override
  public void restart() {
    int p = this.listBoids.length;
    for(int k = 0; k<p; k++){
       this.listBoids[k].reInit();
    }
    this.manager.restart();
    window.reset();
    this.draw();
  }
}
