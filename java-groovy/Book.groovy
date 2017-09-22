class Book {
    def name
    def balance

    Book(name, balance) {
        this.name = name
        this.balance = balance
    }

    @Override
    public String toString() {
        return "Book{" +
                "name=" + name +
                ", balance=" + balance +
                '}';
    }
}