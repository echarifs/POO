

import java.awt.Point;
import java.lang.Math;
public class Proies extends Boids{

     public Proies(Point[] positions, Point[] vitesses, long pas, float maxspeed, float maxforce, int r){
       super(positions, vitesses, pas, maxspeed, maxforce, r);
     }


     public void escape(Predateurs predateurs){
       float distance = 200;
       int ligne = this.getPositions().length;
       int colonne = predateurs.getPositions().length;
       float maxforce = this.getMaxForce();
       float maxspeed = this.getMaxSpeed();
       for (int j =0; j<ligne; j++){
         float sumx = 0;
         float sumy = 0;
         int count = 0;
         for (int i= 0; i<colonne; i++) {
           /* pour tout les voisins on ajoute la direction que doit suivre la ball
           pour s'echaper, on normalise pour avoir une direction finale de norme un
           puis on divise par d, car plus deux balls sont proches plus ils ont tendance à s'echaper  */
           float dx = this.getPositions()[j].x - predateurs.getPositions()[i].x;
           float dy = this.getPositions()[j].y - predateurs.getPositions()[i].y;
           float d = (float) Math.sqrt(dx*dx + dy*dy);
           if (d > 0 && d < distance ) {
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
           // on limite la vitesse de la ball
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
           this.getAccelerations()[j].x += acceleration.x;
           this.getAccelerations()[j].y += acceleration.y;
         }
       }
     }

     public void move(int width, int heigth, Predateurs predateurs){
       this.align();
       this.escape(predateurs);
       this.separate();
       this.cohesion();
       int n = this.getPositions().length;
       for (int j = 0; j<n; j++){
         this.getVitesses()[j].x += this.getAccelerations()[j].x;
         this.getVitesses()[j].y += this.getAccelerations()[j].y;
         this.getPositions()[j].x += this.getVitesses()[j].x;
         this.getPositions()[j].y += this.getVitesses()[j].y;
         this.getPositions()[j].x = Math.floorMod((int) this.getPositions()[j].x, (int)width);
         this.getPositions()[j].y = Math.floorMod((int)this.getPositions()[j].y, (int)heigth);
         float l = (float)(Math.sqrt(this.getVitesses()[j].x*this.getVitesses()[j].x+this.getVitesses()[j].y*this.getVitesses()[j].y));
         this.getHeads()[j].setLocation(this.getPositions()[j].x + (int)(this.getVitesses()[j].x*(this.getRayon()+3) / l), this.getPositions()[j].y + (int)(this.getVitesses()[j].y*(this.getRayon()+3) / l));
         this.getAccelerations()[j].x = 0;
         this.getAccelerations()[j].y = 0;
       }
     }
}
