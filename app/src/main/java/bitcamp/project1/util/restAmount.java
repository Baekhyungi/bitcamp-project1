package bitcamp.project1.util;
import java.text.DecimalFormat;




public class restAmount {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static String formatNumber(int number) {
        return decimalFormat.format(number);
    }
}
