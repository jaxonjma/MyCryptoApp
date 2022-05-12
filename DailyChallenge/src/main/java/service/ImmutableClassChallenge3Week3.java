
package service;

import lombok.Getter;

import java.util.HashMap;

/**
 * Writing an immutable class via the Builder pattern:
 * Write a program that represents an implementation of the Builder pattern in an immutable class.
 */

@Getter
public class ImmutableClassChallenge3Week3 {

    private int id;
    private String name;
    private String nit;
    private HashMap<String, String> object;

    public HashMap<String, String> getObject() {
        return (HashMap<String, String>) object.clone();
    }

    private ImmutableClassChallenge3Week3(ImmutableBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.object = builder.object;
        this.nit = builder.nit;
    }

    public static class ImmutableBuilder{

        private int id;
        private String name;
        private String nit;
        private HashMap<String, String> object;

        public ImmutableBuilder(int id, String name){
            this.id=id;
            this.name=name;
        }

        public ImmutableBuilder setObject(HashMap<String,String> object){
            this.object = (HashMap<String, String>) object.clone();
            return this;
        }

        public ImmutableBuilder setNit(String nit){
            this.nit = nit;
            return this;
        }

        public ImmutableClassChallenge3Week3 build(){
            return new ImmutableClassChallenge3Week3(this);
        }
    }
}
