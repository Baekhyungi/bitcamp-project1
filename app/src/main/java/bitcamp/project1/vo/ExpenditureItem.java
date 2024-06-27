package bitcamp.project1.vo;

import java.time.LocalDate;
import bitcamp.project1.util.restAmount;

public class ExpenditureItem {
    LocalDate date;
    String category;
    public int amount;
    String description;

    public ExpenditureItem(LocalDate date, String category, int amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", date, category, restAmount.formatNumber(amount), description);
    }
}
