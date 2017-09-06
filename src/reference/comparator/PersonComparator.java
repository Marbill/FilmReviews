package reference.comparator;

import java.util.Comparator;
import java.util.Map;
import reference.domain.Person;

public class PersonComparator implements Comparator<Person> {

    private Map<Person, Integer> peopleIdentities;

    public PersonComparator(Map<Person, Integer> peopleIdentities) {
        this.peopleIdentities = peopleIdentities;
    }

    @Override
    public int compare(Person o1, Person o2) {
        if (this.peopleIdentities.get(o1) >= this.peopleIdentities.get(o2)) {
            return -1;
        }

        return 1;
    }
}
