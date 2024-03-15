package eu.ase.lab4;

import java.util.Comparator;

public class RatingComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		if (movie1.getRating() == movie2.getRating()) {
			return 0;
		}
		if (movie1.getRating() > movie2.getRating()) {
			return 1;
		} else {
			return -1;
		}
	}

}