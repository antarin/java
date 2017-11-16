package enums;

public enum Priority {

    URGENT(1),
    MEDIUM(2),
    LOW(3);

    private int value;

    Priority(int type) {
        this.value = type;
    }

    public int getValue() {
        return value;
    }
}
