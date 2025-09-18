package warehouse;

class item {
    private String code;
    private String name;
    private double quantity;
    public item(String code, String name, double quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return String.format("%-7s | %-12s | %.0fqty", code, name, quantity);
    }
}