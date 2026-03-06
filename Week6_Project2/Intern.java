class Intern extends Employee {

    Intern(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void attendTraining() {
        System.out.println(name + " is attending training.");
    }

    void submitReport() {
        System.out.println(name + " submitted a report.");
    }
}