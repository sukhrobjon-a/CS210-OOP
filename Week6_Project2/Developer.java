class Developer extends Employee {

    Developer(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void writeCode() {
        System.out.println(name + " is writing code.");
    }

    void fixBug() {
        System.out.println(name + " fixed a bug.");
    }
}

