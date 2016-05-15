package app.wishzee.com.wishzee.apiparsing.publicfeed;

import app.wishzee.com.wishzee.apiparsing.recentapiparsing.NotificationDetails;

/**
 * Created by Ajit Gupta on 5/14/2016.
 */
public class PublicNotificationDetails {
    private String status;
    private NotificationDetails notificationDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }
}
