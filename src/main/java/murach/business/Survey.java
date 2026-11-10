package murach.business;

import java.io.Serializable;

public class Survey implements Serializable {
    private User user;
    private String hearAbout;
    private String announcements;
    private String contactBy;

    public Survey() {
        this.hearAbout = "";
        this.announcements = "";
        this.contactBy = "";
    }

    public Survey(User user, String hearAbout, String announcements, String contactBy) {
        this.user = user;
        this.hearAbout = hearAbout;
        this.announcements = announcements;
        this.contactBy = contactBy;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getHearAbout() { return hearAbout; }
    public void setHearAbout(String hearAbout) { this.hearAbout = hearAbout; }

    public String getAnnouncements() { return announcements; }
    public void setAnnouncements(String announcements) { this.announcements = announcements; }

    public String getContactBy() { return contactBy; }
    public void setContactBy(String contactBy) { this.contactBy = contactBy; }
}