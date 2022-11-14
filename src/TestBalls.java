import java.awt.Point;


public class TestBalls {

	public static void main(String[] args) {
		// Créer un tableau des points
		Point point = new Point();
		Point[] points = new Point[6];
		Point[] vitesses = new Point[6];
		points[0]= point;
		vitesses[0] = new Point(10, 10);
		for( int i = 1; i<6; i++) {
			points[i]= new Point(i,i+1);
			vitesses[i] = new Point(10, 10);
		}
		// Créer une instance de Balls
		Balls balles = new Balls(points, vitesses, 15);
		// Afficher cette instance
		System.out.println(balles);
		// Translater l'instance, et l'afficher
		balles.translate(1,0);
		System.out.println(balles);
		// Remettre toutes les balles à leurs positions initiales, et les afficher
		balles.reInit();
		System.out.println(balles);

		// Test de modification des positions initiales de toutes les balles
		Point[] p = {point, point,point,point, point};
		balles.setInitPositions(p);
		balles.reInit();
		System.out.println(balles);
		System.out.println("fin");

	}

}
