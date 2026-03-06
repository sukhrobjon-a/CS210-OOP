class Person {
    String name;
    int id;
    int age;

    Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Age: " + age);
    }
}