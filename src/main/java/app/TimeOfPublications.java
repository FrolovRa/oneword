package app;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeOfPublications {

    private static LocalDateTime now = LocalDateTime.now();

    public static String getTimeDifference(LocalDateTime ptime) {

        long diff = (Duration.between(ptime, now).toMinutes());
        String result;

        if (diff >= 60 * 24 * 7) {
            result = Math.round(diff / (60 * 24 * 7)) + " weeks ago";

        } else if (diff >= 60 * 24) {
            result = Math.round(diff / (60 * 24)) + " days ago";

        } else if (diff >= 60) {
            result = Math.round(diff / 60) + " hour ago";

        } else if (diff >= 5) {
            result = diff + " minutes ago";

        } else {
            result = "just now";

        }

        return result;
    }

}
