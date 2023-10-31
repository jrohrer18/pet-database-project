
package petdatabase;


public class Pet {
    
    private String name;
    private int age;

    public Pet(String petName, int petAge) {
            name=petName;
            age=petAge;
    }

    public String getName() {
            return name;
    }

    public void setName(String petName) {
            name=petName;
    }

    public int getAge() {
            return age;
    }

    public void setAge(int petAge) {
            age=petAge;
    }
    
}
