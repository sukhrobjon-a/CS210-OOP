public class Runner {
    public static void main(String[] args) {
//        //Problem 1
//        Flight flight1 = new Flight("PR301", "Namangan", "12A", "G5");
//        System.out.println(flight1);



//        //Problem 2
//        SmartDevice bulb = new SmartDevice("Samsung", false);
//        Hub<SmartDevice> homeHub = new Hub<>();
//        homeHub.storeDevice(bulb);
//
//        bulb.turnOn();
//        homeHub.statusReport();
//        homeHub = null;
//
//        System.out.println("Device still exists outside Hub:");
//        System.out.println(bulb);


        //Problem 3
        Professor prof = new Professor("Dr. Suxrobjobn");
        Book book = new Book("Artificial Intelligence", "Abdulla Qodiri");
        LibrarySystem library = new LibrarySystem();
        library.checkout(prof, book);
    }
}
