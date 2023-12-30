import java.io.*;
import java.util.*;
class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> numbers;
    private String serialNumber;

    public Ticket(List<Integer> numbers, String serialNumber) {
        this.numbers = numbers;
        this.serialNumber = serialNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
