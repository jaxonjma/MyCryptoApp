package service;

/**
 *  Writing an immutable class: Write a program that represents an immutable class.
 */
final class InmutableClassChallenge1Week3 {

    private String attribute;

    //Assign value in constructor
    InmutableClassChallenge1Week3(String attribute) {
        this.attribute=attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}

class Main {
    public static void main(String[] args) {
        InmutableClassChallenge1Week3 obj = new InmutableClassChallenge1Week3("myAttributeValue");
        System.out.println(String.format("The inmutable value is %s",obj.getAttribute()));
    }
}