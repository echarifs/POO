import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.util.HashSet;
import java.lang.Math;


public class Schelling extends Games{
	private ArrayList<Point> vacantes;
	private ArrayList<Point> vacantesInit;
	private int seuil;
	
	
	public ArrayList<Point> getVacantes() {
		return vacantes;
	}

	public void setVacantes(ArrayList<Point> list) {
		vacantes = new ArrayList<>();
		for(Point p : list) {
			vacantes.add(p);
		}
	}

	public ArrayList<Point> getVacantesInit() {
		return vacantesInit;
	}


	public Schelling(ArrayList<Point> habite,int seuil, int nbCouleurs, int size, int width, int height) {
		super(habite, "Schelling",nbCouleurs, size, width, height);
		
		this.vacantes = new ArrayList<>();
		this.vacantesInit = new ArrayList<>();
		this.seuil = seuil;		

		// Remplie les ensembles des familles vacantes
		for (int k = 0; k<k_max; k++) {
			for (int m = 0; m<m_max; m++) {
				Point point = new Point(Math.floorMod(k,k_max),Math.floorMod(m,m_max));
				if (! this.pointsMod(habite).contains(point)) {
					vacantes.add(point);
					vacantesInit.add(point);
					
				}
			}
		}
	}
	
	@Override
	public int nextEtat(Point p) {
		int cp = 0;
		for (Point voisin : this.voisins(p)) {
			if(! vacantes.contains(voisin) && rectangles.get(voisin)!= rectangles.get(p)) {
				cp+=1;
				if(cp>=seuil) {
					return -1;
				}
			}
		}
		return rectangles.get(p);
	}
	
	@Override
	public void rules() {
		HashMap<Point, Integer> newRectangles = new HashMap<>();
		ArrayList <Point> newVacantes = new ArrayList<>();
		newVacantes.addAll(vacantes);
		HashMap <Integer, HashSet<Point>> rectanglesCouleurs = new HashMap<>();
		for (int i =0; i<n; i++) {
			rectanglesCouleurs.put(i, new HashSet<Point>());
		}
		for (Point famille :rectangles.keySet()) {
			int couleur = rectangles.get(famille);
			if(this.nextEtat(famille)== -1) {
				
				rectanglesCouleurs.get(couleur).add(famille);
				int i = 0;
				Point newfamille = newVacantes.get(i);
				while(rectanglesCouleurs.get(couleur).contains(newfamille)) {
					i+=1;
					newfamille = newVacantes.get(i);
				}
				newVacantes.remove(i);
				newRectangles.put(newfamille, couleur);
				newVacantes.add(famille);
			}
			else{newRectangles.put(famille, couleur);}
		}
		rectangles = newRectangles;
		vacantes = newVacantes;
	}
		
}
