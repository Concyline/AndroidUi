package siac.com.texto.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static SimpleDateFormat dataHoraSegundo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String dateHoraSegundo(Date date) {
        return dataHoraSegundo.format(date);
    }
}
