import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import gui.*;

/*cette classe fournit l'implémentation d'un simulateur des jeux (Conway, Schelling, Immigration) */
public class GamesSimulator implements Simulable{
	private Games jeu;
	private  GUISimulator window;
	private int size;
	private Color[] couleurs;
	private String name;
	
	public GamesSimulator(ArrayList<Point> points, String jeu, int seuil, GUISimulator window, int size, Color[] couleurs) {
		if(jeu == "Conway") {
			this.jeu = new Conway(points, size, window.getPanelWidth(), window.getPanelHeight());
		}else if(jeu == "Immigration") {
			this.jeu = new Immigration(points, seuil, size, window.getPanelWidth(), window.getPanelHeight());
		}else if(jeu == "Schelling") {
			this.jeu = new Schelling(points,seuil, couleurs.length, size, window.getPanelWidth(), window.getPanelHeight());
		}
		this.name = jeu;
		this.window = window;
		this.size = size;
		this.couleurs = couleurs;
		this.draw();
		}
	
	
	/* permet de dessiner les grilles selon chaque jeu*/
	public void draw(){
		int d = (int)size/2;
		for (Point p : this.jeu.getRectangles().keySet()) {
				int x = (int) p.getX();
				int y = (int) p.getY();
				Color couleur = Color.WHITE;
				if (name == "Schelling" ) {
					couleur = couleurs[jeu.getRectangles().get(p)];
				}else if (name =="Immigration") {
					couleur = this.getColor()[jeu.getRectangles().get(p)];
					
				}else if(name =="Conway") {
					if (this.jeu.getRectangles().get(p)==0 ) {
						couleur = couleurs[0];
					}else {
						couleur = couleurs[1];
					}
				}
				this.window.addGraphicalElement(new Rectangle((int)x*size+d, (int)y*size+d, Color.GRAY, couleur, size));
		}
		if(name == "Schelling")	{
			for (Point p : ((Schelling) jeu).getVacantes()) {
				int x = (int) p.getX();
				int y = (int) p.getY();
				this.window.addGraphicalElement(new Rectangle((int)x*size+d, (int)y*size+d, Color.GRAY, Color.WHITE, size));
			}
		}
	}
	/* cette méthode est utile pour le jeu d'immigration pour colorer les carrés de la grille */
	public Color[] getColor() {
		int n = ((Immigration) jeu).getN();
		int pas = (int) 255/n;
		Color[] couleurs = new Color[n];
		for (int i = 0; i <n; i++) {
			couleurs[i] = new Color(0,0,0, i*pas);
		}
		return couleurs;
	}

	@Override
	public void next() {
		this.jeu.rules();
		window.reset();
		this.draw();
	}
	@Override
	public void restart() {
		window.reset();
		this.jeu.setRectangles(this.jeu.getRectanglesInit());
		if (name == "Schelling") {
			((Schelling) jeu).setVacantes(((Schelling) jeu).getVacantesInit());
		}
		this.draw();
	}
	
	
}