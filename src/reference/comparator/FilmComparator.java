package reference.comparator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import reference.domain.Film;
import reference.domain.Rating;

public class FilmComparator implements Comparator<Film> {

    private Map<Film, List<Rating>> filmRatings;

    public FilmComparator(Map<Film, List<Rating>> ratings) {
        this.filmRatings = ratings;
    }

    @Override
    public int compare(Film o1, Film o2) {
        int totalFilm1 = 0;
        int totalFilm2 = 0;

        for (int i = 0; i < this.filmRatings.get(o1).size(); i++) {
            totalFilm1 += this.filmRatings.get(o1).get(i).getValue();
        }

        for (int i = 0; i < this.filmRatings.get(o2).size(); i++) {
            totalFilm2 += this.filmRatings.get(o2).get(i).getValue();
        }

        if (totalFilm1 >= totalFilm2) {
            return -1;
        }

        return 1;

    }
}
