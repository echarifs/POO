import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

/* cette classe contient l'ensemble des calculs communs entre les classes des jeux
 * (Conway , Schelling, Immigration) */
public abstract class Games {
	protected HashMap< Point , Integer> rectangles;
	protected HashMap< Point , Integer> rectanglesInit;
	protected  int k_max;
	protected int m_max;
	protected int n;
	
	/* modifie l'attribut rectangle à partir d'un autre réctangle donné en paramètre */
	public void setRectangles(HashMap<Point, Integer>  Map) {
		this.rectangles = new HashMap<>();
		for (Point p: Map.keySet()) {
			rectangles.put(p,Map.get(p));
			}
	}
	
	/* retourne l'attribut rectangle résumant les états des points (carrés dans la grille) */
	public HashMap<Point , Integer> getRectangles() {
		return this.rectangles;
	}
	
	/* retourne l'attribut rectangleInit résumant les états initiaux des points (carrés dans la grille) */
	public HashMap<Point , Integer> getRectanglesInit() {
		return rectanglesInit;
	}
	
	/*retourne l'attribut n (nombre d'états) */
	public int getN() {
		return this.n;
	}
	
	/* Constructeur 
	 * @param jeu le nom du jeu (Conway, Immigration, Schelling)
	 * @param size la taille du chaque carré de la grille
	 * @param n nombre d'états des points qui correspond à leur nombre de couleurs
	 * @param points les carrés de la grille qui sont colorés
	 * @param width largeur de la grille 
	 * @param height hauteur de la grille
	 */
	public Games(ArrayList<Point> points, String jeu,int n, int size, int width, int height) {
		this.rectangles = new HashMap<>();
		this.rectanglesInit = new HashMap<>();
		this.k_max = (int) width/size;
		this.m_max = (int) height/size;
		this.n = n;
		ArrayList<Integer> etatPoints = new ArrayList<>();
		for (int i =0; i<points.size(); i++) {
			if(jeu =="Conway") {
				etatPoints.add(1);
			}else if(jeu == "Schelling") {
				etatPoints.add(Math.floorMod(i,n));
			}else if(jeu == "Immigration") {
				etatPoints.add(Math.floorMod(i,n));
			}
		}
		 ArrayList<Point> newPoints = this.pointsMod(points);
		for (int i =0; i<points.size(); i++) {
			rectangles.put(newPoints.get(i), etatPoints.get(i));
			rectanglesInit.put(newPoints.get(i), etatPoints.get(i));
		}
		if(jeu == "Conway" || jeu == "Immigration") {
			for (int k = 0; k<k_max; k++) {
				for (int m = 0; m<m_max; m++) {
					Point point = new Point(Math.floorMod(k,k_max),Math.floorMod(m,m_max));
					if (! newPoints.contains(point)) {
						this.rectangles.put(point,0);
						this.rectanglesInit.put(point,0);
						
					}
				}
			}
		}
		
	}
	
	/*retourne une nouvelle liste de points dont leurs abscisses et leurs cordonnées appartiennent 
	 respectivement à (0,k_max) et (0,m_max) */
	public ArrayList<Point> pointsMod(ArrayList<Point> points){
		ArrayList<Point> newPoints = new ArrayList<>();
		for (int i =0; i<points.size(); i++) {
			int x = (int) points.get(i).getX();
			int y = (int) points.get(i).getY();
			Point pNew =  new Point(Math.floorMod(x,k_max),Math.floorMod(y,m_max));
			newPoints.add(pNew);
		}
		return newPoints;
	}
		
	/* retourne un tableau des points représentant les voisins d'un certain point donné en paramète*/
	public Point[] voisins(	Point p) {
		Point[] neighbors = new Point[8];
		int x = (int) p.getX();
		int y = (int) p.getY();
		neighbors[0]= new Point(Math.floorMod(x-1,k_max),Math.floorMod(y,m_max));
		neighbors[1]= new Point(Math.floorMod(x-1,k_max),Math.floorMod(y-1,m_max));
		neighbors[2]= new Point(Math.floorMod(x-1,k_max),Math.floorMod(y+1,m_max));
		neighbors[3]= new Point(Math.floorMod(x,k_max),Math.floorMod(y-1,m_max));
		neighbors[4]= new Point(Math.floorMod(x,k_max),Math.floorMod(y+1,m_max));
		neighbors[5]= new Point(Math.floorMod(x+1,k_max),Math.floorMod(y-1,m_max));
		neighbors[6]= new Point(Math.floorMod(x+1,k_max),Math.floorMod(y,m_max));
		neighbors[7]= new Point(Math.floorMod(x+1,k_max),Math.floorMod(y+1,m_max));
		return neighbors;
	}
	
	/*Etant donné un point p, cette méthode retourne l'état du point
	p dans la prochaine itération  suivant les régles du jeu*/
	public abstract int nextEtat(Point p);
	
	/* change notre ensemble de points de sorte qu'ils suivent tous les règles du jeu*/
	public abstract void rules();
}
