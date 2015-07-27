package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 26.07.15..
 */
public class Author implements Serializable {

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;
}
