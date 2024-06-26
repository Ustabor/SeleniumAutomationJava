package entities;

public class User {

    private String email;
    private String password;
    private String phoneCode;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String city;
    private String profileId = null;
    private String lastSmsCode = "";

    public User() { }

    public User(String email, String password, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return getPhoneCode() + getPhoneNumber();
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getLastSmsCode() { return lastSmsCode; }

    public void setLastSmsCode(String lastSmsCode) { this.lastSmsCode = lastSmsCode; }

    @Override
    public String toString() {
        return "User{ " +
                String.format("phoneNumber: %s, ", phoneNumber) +
                String.format("profileId: %s }", profileId);
    }
}
