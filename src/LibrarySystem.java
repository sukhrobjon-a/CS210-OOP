class LibrarySystem {
    public void checkout(Professor p, Book b) {
        System.out.println(p.getName() + " has checked out " + b.getTitle() +  " for research.");
    }
}