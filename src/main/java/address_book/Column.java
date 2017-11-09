package address_book;

public enum Column {
    NAME(0),
    SEX(1),
    DATE(2);

    private int index;

    Column(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}