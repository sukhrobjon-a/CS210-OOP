class Manager extends Employee {

    Manager(String name, int employeeID, double salary) {
        super(name, employeeID, salary);
    }

    void assignTask(String task) {
        System.out.println(name + " assigned task: " + task);
    }

    void approveLeave(String employeeName) {
        System.out.println("Leave approved for " + employeeName);
    }
}