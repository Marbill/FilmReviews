package reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;

public class RatingRegister {

    Map<Film, List<Rating>> movieRatings;
    Map<Person, Map<Film, Rating>> personRatings;

    public RatingRegister() {
        this.movieRatings = new HashMap<Film, List<Rating>>();
        this.personRatings = new HashMap<Person, Map<Film, Rating>>();
    }

    public void addRating(Film film, Rating rating) {
        if (!this.movieRatings.containsKey(film)) {
            this.movieRatings.put(film, new ArrayList<Rating>());
        }

        this.movieRatings.get(film).add(rating);
    }

    public List<Rating> getRatings(Film film) {
        List<Rating> temp = new ArrayList<Rating>();

        if (this.movieRatings.containsKey(film)) {
            for (Rating rating : this.movieRatings.get(film)) {
                temp.add(rating);
            }

            return temp;
        }
        return null;
    }

    public Map<Film, List<Rating>> filmRatings() {
        return this.movieRatings;
    }

    public void addRating(Person person, Film film, Rating rating) {
        if (!this.personRatings.containsKey(person)) {
            this.personRatings.put(person, new HashMap<Film, Rating>());
        }

        this.personRatings.get(person).put(film, rating);
        this.addRating(film, rating);
    }

    public Rating getRating(Person person, Film film) {
        if (this.personRatings.containsKey(person)) {
            if (this.personRatings.get(person).get(film) != null) {
                return this.personRatings.get(person).get(film);
            } else {
                return Rating.NOT_WATCHED;
            }
        }

        return null;
    }

    public Map<Film, Rating> getPersonalRatings(Person person) {
        Map<Film, Rating> temp = new HashMap<Film, Rating>();

        if (this.personRatings.containsKey(person)) {
            temp = this.personRatings.get(person);
            return temp;
        } else {
            return temp;
        }

        //return null;
    }

    public List<Person> reviewers() {
        List<Person> people = new ArrayList<Person>();

        if (!this.personRatings.isEmpty()) {
            for (Person person : this.personRatings.keySet()) {
                people.add(person);
            }

            return people;
        }

        return null;
    }
}
