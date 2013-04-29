package conceit.commons;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class DateUtils {
    private static ThreadLocal<SimpleDateFormat> rfc3339SimpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        }
    };
    public static String rfc3339Format(final java.util.Date date) {
        final SimpleDateFormat simpleDateFormat = rfc3339SimpleDateFormatThreadLocal.get();
        final long offset = TimeZone.getDefault().getOffset(date.getTime());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        final StringBuilder builder = new StringBuilder(simpleDateFormat.format(date));
        if (offset == 0) {
            builder.append("Z");
        } else {
            builder.append(offset < 0 ? "-" : "+");
            final long absOffset = Math.abs((int) offset / 60 / 60 / 10);
            if (absOffset < 10) {
                builder.append("000");
            } else if (absOffset < 100) {
                builder.append("00");
            } else if (absOffset < 1000) {
                builder.append("0");
            }
            builder.append(absOffset);
        }
        return builder.toString();
    }
}