package cmse473.bloodbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Onur on 05.12.2017.
 */

public class Seek {

    @SerializedName("seekID")
    @Expose
    private String id;

    @SerializedName("seekerName")
    @Expose
    private String name;

    @SerializedName("seekerEmail")
    @Expose
    private String mail;

    @SerializedName("bloodType")
    @Expose
    private String bloodType;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("seekDate")
    @Expose
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
