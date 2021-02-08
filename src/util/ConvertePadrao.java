package util;

public class ConvertePadrao {
    public static double getFormattedValue(String txt) {
        return Double.parseDouble(txt.replaceAll("\\.", "").replaceAll(",", "."));
    }
}
