package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class Masks {
    public static DefaultFormatterFactory getValorMask() {
        
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        //dfs.setGroupingSeparator(',');
        dfs.setDecimalSeparator('.');
        
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.HALF_UP);
        //df.setDecimalFormatSymbols(dfs);
        
        NumberFormatter nf = new NumberFormatter(df);
        nf.setAllowsInvalid(false);
        nf.setMinimum(0.00);
        nf.setMaximum(999999.99);
        
        return(new DefaultFormatterFactory(nf));
    }
    
    public static DefaultFormatterFactory getCpfMask() {
        
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            return(new DefaultFormatterFactory(mask, mask));
        } catch (ParseException pex) {
            return null;
        }
    }
    
    public static DefaultFormatterFactory getTelefoneMask() {
        
        try {
            MaskFormatter mask = new MaskFormatter("(##) ####-####");
            mask.setPlaceholderCharacter('_');
            return(new DefaultFormatterFactory(mask, mask));
        } catch (ParseException pex) {
            return null;
        }
    }
    
    public static DefaultFormatterFactory getCelularMask() {
        
        try {
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            mask.setPlaceholderCharacter('_');
            return(new DefaultFormatterFactory(mask, mask));
        } catch (ParseException pex) {
            return null;
        }
    }
}
