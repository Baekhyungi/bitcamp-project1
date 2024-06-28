package bitcamp.project1.vo;

import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

public class Item {
    private LocalDate date;
    private String category;
    private int amount;
    private String description;

    public Item(LocalDate date, String category, int amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", date, category, formatNumber(amount), description);
    }

    private String formatNumber(int number) {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number);
    }
}