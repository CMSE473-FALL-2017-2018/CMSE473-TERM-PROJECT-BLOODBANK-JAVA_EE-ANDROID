package cmse473.bloodbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    //The @SerializedName annotation is needed for Gson to map the JSON keys to Java object fields.
    //The @Expose annotation indicates that the class member should be exposed for
    //JSON serialization or deserialization.

    @SerializedName("userEmail")
    @Expose
    private String email;

    @SerializedName("userPassword")
    @Expose
    private String password;

    @SerializedName("userName")
    @Expose
    private String name;

    @SerializedName("birthYear")
    @Expose
    private int birthYear;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("bloodType")
    @Expose
    private String bloodType;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("donor")
    @Expose
    private String donor;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }
}
