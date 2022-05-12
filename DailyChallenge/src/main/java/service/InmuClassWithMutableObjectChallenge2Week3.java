package service;

import lombok.Getter;
import lombok.Setter;

/**
 * Passing/returning mutable objects to/from an immutable class: Write a program that passes and returns a mutable object to/from an immutable class.
 */
public class InmuClassWithMutableObjectChallenge2Week3 {
    public static void main(String[] args) throws Exception {
        Student student = new Student("Jaxon", 28, new School("Cofrem", "Acacías"));
        School school = student.getSchool();
        System.out.println(school);
        school.setName("José María Córdoba");
        school.setCity("Guamal");
        System.out.println(student.getSchool());
    }
}

@Getter
final class Student {
    private final String name;
    private final int age;
    private final School school;
    public Student(String name, int age, School school) {
        super();
        this.name = name;
        this.age = age;
        this.school = school;
    }
    public School getSchool() throws CloneNotSupportedException {
        return (School) school.clone();
    }
}

@Getter @Setter
class School implements Cloneable {
    public String name;
    public String city;

    public School(String name, String city) {
        super();
        this.name = name;
        this.city = city;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Address Type - "+name+", city - "+city;
    }
}

