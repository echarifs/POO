import java.awt.Point;
import java.lang.Math;

public class Boids extends Balls{
  private Point[] heads; // les tetes des boids
  private Point[] accelerations;
  private long pas; // le pas de temps specifique au boids
  private float maxspeed; // vitesses maximale des boids
  private float maxforce;  // force maximale des rules


  public Point[] getHeads() {
    return heads;
  }

  public float getMaxForce(){
    return maxforce;
  }

  public float getMaxSpeed(){
    return maxspeed;
  }

  public Point[] getAccelerations(){
    return accelerations;
  }

  public long getPas(){
    return pas;
  }

  public Boids(Point[] positions, Point[] vitesses, long pas, float maxspeed, float maxforce, int r) {
    super(positions, vitesses, r);
    this.pas = pas;
    this.maxforce = maxforce;
    this.maxspeed = maxspeed;
    int n = this.getPositions().length;
    this.accelerations = new Point[n];
    this.heads = new Point[n];
    // on initialise les accelerations a zero et on calcul la positions des tetes
    for (int i = 0; i<n; i++){
      this.accelerations[i] = new Point(0, 0);
      int vx = this.getVitesses()[i].x;
      int vy = this.getVitesses()[i].y;
      int l = (int) Math.sqrt(vx*vx + vy*vy);
      int xh = this.getPositions()[i].x + (int)((this.getVitesses()[i].getX()) *(this.getRayon()+3)/ l);
      int yh = this.getPositions()[i].y + (int)((this.getVitesses()[i].getY())*(this.getRayon()+3) / l);
      this.heads[i] = new Point(xh, yh);
    }
  }

  public boolean is_in_field_of_view(int i, int j){
    // foction qui retourne si le boid i est dans le champ de vision du boid i
    Point v1 = new Point(this.heads[j].x - this.getPositions()[j].x, this.heads[j].y - this.getPositions()[j].y);
    if(v1.x == 0 && v1.y == 0){
      return true;
    }
    Point v2 = new Point(this.getPositions()[i].x - this.getPositions()[j].x, this.getPositions()[i].y - this.getPositions()[j].y);
    double dot = v1.x * v2.x + v1.y * v2.y ;
    double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y);
    double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y);
    float angle = (float) Math.toDegrees(Math.acos(dot / (v1mag * v2mag)));
    if(angle <= 150){
      return true;
    }
    return false;
  }

  public void separate () {
      float distance = 100; // distance de separation
      int n = this.getPositions().length;
      for (int j = 0; j<n; j++){
        // sum vecteur somme qui determinera la vitesse d'une ball pour se separer de ces voisins
        float sumx = 0;
        float sumy = 0;
        int count = 0;
        for (int i= 0; i<n; i++) {
          /* pour tout les voisins on ajoute la direction que doit suivre la ball
          pour s'echaper, on normalise pour avoir une direction finale de norme un
          puis on divise par d, car plus deux balls sont proches plus ils ont tendance à s'echaper  */
          float dx = this.getPositions()[j].x - this.getPositions()[i].x;
          float dy = this.getPositions()[j].y - this.getPositions()[i].y;
          float d = (float) Math.sqrt(dx*dx + dy*dy);
          if (d > 0 && d < distance && is_in_field_of_view(i, j)) {
            float m = (float) Math.sqrt(dx*dx + dy*dy);
            dx = dx/(m*d);
            dy = dy/(m*d);
            sumx += dx;
            sumy += dy;
            count++;
          }
        }
        if (count > 0) {
          sumx /= count;
          sumy /= count;
          // on maximise vitesse de la ball
          float l = (float) Math.sqrt(sumx*sumx + sumy*sumy);
          int vx = (int) (sumx*maxspeed/l);
          int vy = (int) (sumy*maxspeed/l);
          // on calcule l'acceleration de la ball suite à l'action de separation
          Point acceleration = new Point(vx - this.getVitesses()[j].x, vy - this.getVitesses()[j].y);
          // on limite l'acceleration
          float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
          if(m > maxforce){
            acceleration.x = (int) (acceleration.x*maxforce/m);
            acceleration.y = (int) (acceleration.y*maxforce/m);
          }
          this.accelerations[j].x += acceleration.x;
          this.accelerations[j].y += acceleration.y;
        }
    }
    }

  public void align () {
    float distance = 150; // distance d'alignement
    int n = this.getPositions().length;
    for (int j = 0; j<n; j++){
      float sumx =0;
      float sumy = 0;
      int count = 0;
      // on calcule la direction moyenne des voisins de j
      for (int i= 0; i<n; i++) {
        int dx = this.getPositions()[j].x - this.getPositions()[i].x;
        int dy = this.getPositions()[j].y - this.getPositions()[i].y;
        float d = (float) Math.sqrt(dx*dx + dy*dy);
        if (d>0 && d < distance && is_in_field_of_view(i, j)) {
          sumx +=this.getVitesses()[i].x;
          sumy +=this.getVitesses()[i].y;
          count++;
        }
      }
      if (count > 0) {
        sumx /= count;
        sumy /= count;
        float l = (float) Math.sqrt(sumx*sumx + sumy*sumy);
        int vx = (int) (sumx*maxspeed/l);
        int vy = (int) (sumy*maxspeed/l);
        Point acceleration = new Point(vx - this.getVitesses()[j].x, vy - this.getVitesses()[j].y);
        float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
        if(m > maxforce){
          acceleration.x = (int) (acceleration.x*maxforce/m);
          acceleration.y = (int) (acceleration.y*maxforce/m);
        }
        this.accelerations[j].x += acceleration.x;
        this.accelerations[j].y += acceleration.y;
      }
    }
  }

   public void cohesion () {
      float distance = 150; // distance de cohesion
      int n = this.getPositions().length;
      for (int j = 0; j<n; j++){
        Point sum = new Point(0, 0);
        int count = 0;
        for (int i= 0; i<n; i++) {
          int dx = this.getPositions()[j].x - this.getPositions()[i].x;
          int dy = this.getPositions()[j].y - this.getPositions()[i].y;
          float d = (float) Math.sqrt(dx*dx + dy*dy);
          if (d>0 && d < distance && is_in_field_of_view(i, j)) {
            sum.x +=this.getPositions()[i].x;
            sum.y +=this.getPositions()[i].y;
            count++;
          }
        }
        if (count > 0) {
          sum.x /= count;
          sum.y /= count;
          Point desired = new Point(sum.x - this.getPositions()[j].x, sum.y - this.getPositions()[j].y);
          float l = (float )Math.sqrt(desired.x*desired.x + desired.y*desired.y);
          desired.x = (int) (desired.x*maxspeed/l);
          desired.y = (int) (desired.y*maxspeed/l);
          Point acceleration = new Point(desired.x - this.getVitesses()[j].x, desired.y - this.getVitesses()[j].y);
          float m = (float) Math.sqrt(acceleration.x*acceleration.x + acceleration.y*acceleration.y);
          if(m > maxforce){
            acceleration.x = (int) (acceleration.x*maxforce/(m));
            acceleration.y = (int) (acceleration.y*maxforce/(m));
          }
          this.accelerations[j].x += acceleration.x;
          this.accelerations[j].y += acceleration.y;
        }
      }
   }


    public void move(int width, int heigth){
      // on applique tout les regles
      this.align();
      this.separate();
      this.cohesion();
      int n = this.getPositions().length;
      for (int j = 0; j<n; j++){
        // on change les vitesses et puis les positions et enfin les positions des tetes
        this.getVitesses()[j].x += this.accelerations[j].x;
        this.getVitesses()[j].y += this.accelerations[j].y;
        this.getPositions()[j].x += this.getVitesses()[j].x;
        this.getPositions()[j].y += this.getVitesses()[j].y;
        this.getPositions()[j].x = Math.floorMod((int) this.getPositions()[j].x, (int)width);
        this.getPositions()[j].y = Math.floorMod((int)this.getPositions()[j].y, (int)heigth);
        float l = (float)(Math.sqrt(this.getVitesses()[j].x*this.getVitesses()[j].x+this.getVitesses()[j].y*this.getVitesses()[j].y));
        this.getHeads()[j].setLocation(this.getPositions()[j].x + (int)(this.getVitesses()[j].x*(this.getRayon()+3) / l), this.getPositions()[j].y + (int)(this.getVitesses()[j].y*(this.getRayon()+3) / l));
        this.accelerations[j].x = 0;
        this.accelerations[j].y = 0;
      }
    }

    @Override
    public void reInit() {
      super.reInit();
  		int n = this.getPositions().length;
      for (int i = 0; i<n; i++){
        int l = (int) Math.sqrt((this.getVitesses()[i].getX())*(this.getVitesses()[i].getX())+(this.getVitesses()[i].getY())*(this.getVitesses()[i].getY()));
        this.heads[i] = new Point(this.getPositions()[i].x + (int)((this.getVitesses()[i].getX()) *12/ l),this.getPositions()[i].y + (int)((this.getVitesses()[i].getY())*12 / l));
      }
  	}


}
