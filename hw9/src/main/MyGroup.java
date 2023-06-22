package main;

import com.oocourse.spec1.main.Group;
import com.oocourse.spec1.main.Person;

import java.util.HashMap;

public class MyGroup implements Group {
    private final int id;
    private final HashMap<Integer, Person> people;

    public MyGroup(int id) {
        this.id = id;
        people = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Group)) {
            return false;
        } else {
            return ((Group) obj).getId() == id;
        }
    }

    public void addPerson(Person person) {
        if (!hasPerson(person)) {
            people.put(person.getId(), person);
        }
    }

    public boolean hasPerson(Person person) {
        return people.containsKey(person.getId());
    }

    public int getValueSum() {
        int sum = 0;
        for (Person person1 : people.values()) {
            for (Person person2 : people.values()) {
                if (person1.isLinked(person2)) {
                    sum += person1.queryValue(person2);
                }
            }
        }
        return sum;
    }

    public int getAgeMean() {
        if (people.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (Person person : people.values()) {
            sum += person.getAge();
        }
        return sum / people.size();
    }

    public int getAgeVar() {
        if (people.size() == 0) {
            return 0;
        }
        int sum = 0;
        int mean = getAgeMean();
        for (Person person : people.values()) {
            sum += (person.getAge() - mean) * (person.getAge() - mean);
        }
        return sum / people.size();
    }

    public void delPerson(Person person) {
        if (hasPerson(person)) {
            people.remove(person.getId());
        }
    }

    public int getSize() {
        return people.size();
    }
}
