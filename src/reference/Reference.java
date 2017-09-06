package reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reference.comparator.FilmComparator;
import reference.comparator.PersonComparator;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;

public class Reference {

    private RatingRegister ratingRegister;

    public Reference(RatingRegister ratingRegister) {
        this.ratingRegister = ratingRegister;
    }

    public Film recommendFilm(Person person) {
        if (ratingRegister.reviewers() != null) {
            return filmByReviewers(person);
        }
        return filmByFilms(person);

        //return null;
    }

    private Film filmByFilms(Person person) {
        Map<Film, List<Rating>> films = ratingRegister.filmRatings();
        List<Film> onlyFilms = new ArrayList<Film>();

        for (Film film : films.keySet()) {
            onlyFilms.add(film);
        }

        Collections.sort(onlyFilms, new FilmComparator(films));

        return onlyFilms.get(0);

        //return null;
    }

    private Film filmByReviewers(Person person) {
        List<Person> reviewers = ratingRegister.reviewers();
        Map<Film, Rating> personFilms = this.ratingRegister.getPersonalRatings(person);
        Map<Person, Integer> peopleScores = new HashMap<Person, Integer>();
        Film retunFilm = null;

        for (Person reviewer : reviewers) {
            if (!reviewer.equals(person)) {
                for (Film film : this.ratingRegister.getPersonalRatings(reviewer).keySet()) {
                    if (personFilms != null) {
                        for (Film f : personFilms.keySet()) {
                            if (f.equals(film)) {
                                if (peopleScores.containsKey(reviewer)) {
                                    int temp = peopleScores.get(reviewer);
                                    peopleScores.put(reviewer, temp + (personFilms.get(f).getValue() * this.ratingRegister.getPersonalRatings(reviewer).get(film).getValue()));
                                } else {
                                    peopleScores.put(reviewer, personFilms.get(f).getValue() * this.ratingRegister.getPersonalRatings(reviewer).get(film).getValue());
                                }
                            }
                        }
                    }
                }
            }
        }

        //System.out.println(peopleScores.toString());
        List<Person> tempScores = new ArrayList<Person>();

        for (Person p : peopleScores.keySet()) {
            tempScores.add(p);
        }

        Collections.sort(tempScores, new PersonComparator(peopleScores));
        //System.out.println(tempScores);

        //System.out.println(tempScores.get(0));
        if (!tempScores.isEmpty()) {
            //System.out.println(tempScores.get(0));

            Map<Film, Rating> recommendPerson = this.ratingRegister.getPersonalRatings(tempScores.get(0));

            for (Film film : recommendPerson.keySet()) {
                if (!personFilms.containsKey(film) && recommendPerson.get(film) == Rating.GOOD) {
                    retunFilm = film;
                    break;
                } else if (!personFilms.containsKey(film) && recommendPerson.get(film) == Rating.FINE) {
                    retunFilm = film;
                    break;
                }
            }
        } else {
            Map<Film, List<Rating>> allFilmRatings = this.ratingRegister.filmRatings();
            List<Film> allFilms = new ArrayList<Film>();
            for (Film f : allFilmRatings.keySet()) {
                allFilms.add(f);
            }

            Collections.sort(allFilms, new FilmComparator(allFilmRatings));

            return allFilms.get(0);

        }

        return retunFilm;
    }
}
