package remoteokdesktop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static String getDescription(String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(
                text.length() + insert.length() * (text.length()/period)+1);

        int index = 0;
        String prefix = "";
        while (index < text.length())
        {
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index,
                    Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD hh:mm:ss");
        return format.format(date);
    }
}
