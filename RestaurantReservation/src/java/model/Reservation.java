package model;

import java.io.Serializable;

/**
 *
 * @author John Phillips
 */
public class Reservation implements Serializable {

    private int id;
    private String lastName;
    private String firstName;
    private String date;
    private String time;
    private String notes;

    public Reservation() {
        id = 0;
        lastName = "Builder";
        firstName = "Bob";
        date = "2015-08-08";
        time = "6:00 PM";
        notes = "Window seat";
    }

    public Reservation(int id, String lastName, String firstName, String date, String time, String notes) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String inHTMLRowFormat() {
        return "<tr><td>" + lastName + "</td>"
                + "<td>" + firstName + "</td>"
                + "<td>" + date + "</td>"
                + "<td>" + time + "</td>"
                + "<td>" + notes + "</td></tr>\n";
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastName=" + lastName + ", firstName="
                + firstName + ", date=" + date + ", time=" + time
                + ", notes=" + notes + '}';
    }
}
