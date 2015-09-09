package co.infinum.skliba.BoatIt.models.comments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentsResponseBody implements Serializable {

    @SerializedName("content")
    public String content;

    @SerializedName("author")
    public CommentAuthor author;

    @SerializedName("created_at")
    public String time;

    private int daysPassed;

    private int hoursPassed;

    private int minutesPassed;

    private int secondsPassed;

    public void setSecondsPassed(int secondsPassed) {
        this.secondsPassed = secondsPassed;
    }

    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    public void setHoursPassed(int hoursPassed) {
        this.hoursPassed = hoursPassed;
    }

    public void setMinutesPassed(int minutesPassed) {
        this.minutesPassed = minutesPassed;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public int getHoursPassed() {
        return hoursPassed;
    }

    public int getMinutesPassed() {
        return minutesPassed;
    }

    public int getSecondsPassed() {
        return secondsPassed;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public CommentAuthor getAuthor() {
        return author;
    }
}
