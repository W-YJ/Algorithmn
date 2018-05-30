package week4.elementarysymboltables;

// Implementing equals for user-defined types(9)
public final class Date {
    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public boolean equals(Object y) {

        // optimize for true object equality
        if (y == this)
            return true;
        // check for null
        if (y == null)
            return false;

        // object must be in the same class(religion: getClass() vs. instanceof())
        if (y.getClass() != this.getClass())
            return false;

        // cast is guaranteed to succeed
        Date that = (Date) y;

        // check that all significant fields are the same
        if(this.day != that.day)
            return false;
        if(this.month != that.month)
            return false;
        if(this.year != that.year)
            return false;
        return true;
    }
}
