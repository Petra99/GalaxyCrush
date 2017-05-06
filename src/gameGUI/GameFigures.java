package gameGUI;

import java.util.ArrayList;
import java.util.Random;

public class GameFigures {

	

	public Figure getRandomFigure() {

		ArrayList<Figure> figureList = new ArrayList<Figure>();
		
		figureList.add(new FigureEarth());
		figureList.add(new FigureSaturn());
		figureList.add(new FigureNeptune());
		figureList.add(new FigureMars());

		Random randomImage = new Random();
		int index = randomImage.nextInt(figureList.size());
		System.out.println(index);
		return figureList.get(index);
	}
}
