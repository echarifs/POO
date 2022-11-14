
import java.awt.Point;
import java.util.Arrays;
import java.lang.*;


public class Balls{

	private Point[] positions;
	private Point[] vitesses;
	private Point[] initPositions;
  private Point[] initVitesses;
  private int r;

  public int getRayon(){
		return r;
	}

	public Point[] getPositions() {
		return positions;
	}

	public Point[] getVitesses() {
		return vitesses;
	}

	public void setPositions(Point[] positions) {
		this.positions = positions;
	}

	public void setVitesses(Point[] vitesses) {
		this.vitesses = vitesses;
	}

	public Point[] getInitPositions() {
		return initPositions;
	}

	public Point[] getInitVitesses() {
		return initVitesses;
	}

	public void setInitPositions(Point[] initPositions) {
			this.initPositions = initPositions;
	}

	public void setInitVitesses(Point[] initVitesses) {
			this.initVitesses = initVitesses;
	}

	public void translate(int dx, int dy){
		for (Point p : this.positions){
			p.translate(dx, dy);
		}
	}

	public Balls(Point[] positions, Point[] vitesses, int r) {
		this.positions = positions;
		this.vitesses = vitesses;
		this.r = r;
		int n = this.positions.length;
		this.initPositions = new Point[n];
		this.initVitesses = new Point[n];
		for (int i = 0; i<n; i++) {
			this.initPositions[i]= this.positions[i].getLocation();
			this.initVitesses[i]= this.vitesses[i].getLocation();
			}
	}

	public void update_vitesses(int width, int heigth){
		// si on atteint les bords on doit rebondir
		int n = this.positions.length;
		for (int i = 0; i<n; i++) {
			int x = this.positions[i].x;
			int y = this.positions[i].y;
			if (x >= width || x<0) {
				this.vitesses[i].x *= -1 ;
			}
			if (y>= heigth || y<0 ) {
				this.vitesses[i].y *= -1;
			}
		}
	}

	public void move() {
		// a chaque appel a next on translate les balls
		int n = this.positions.length;
		for (int i = 0; i<n; i++) {
			this.positions[i].translate(this.vitesses[i].x, this.vitesses[i].y);
		}
	}

	public void reInit() {
		// a chaque appel a restart on reprend les positions initiales
		int n = this.positions.length;
		for (int i = 0; i<n; i++) {
			this.positions[i].setLocation(this.initPositions[i]);
			this.vitesses[i].setLocation(this.initVitesses[i]);
		}
	}


	@Override
	public String toString() {
		return "Balls : " + Arrays.toString(this.positions) ;
	}

}
