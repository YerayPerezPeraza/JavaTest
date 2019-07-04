/**
This class defines objects, which contain the information of different users
@author: Yeray Pérez Peraza
@version: 1.0.2
 */



public class Users {
    /**
     * class fields
    */
    private String name;
    private String surname;
    private Boolean active;
    private String email;
    private String city;
    private String creationDate;

    /**
    constructor of the class without specifying parameters
     */
    public Users() {
    }
    /**
    constructor of the class Users
    @param name Username
    @param surname user surname
    @param active user active
    @param email user email
    @param city user city
    @param creationDate user creation date
     */
    public Users(String name, String surname, Boolean active, String email, String city, String creationDate) {
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.email = email;
        this.city = city;
        this.creationDate = creationDate;
    }
    /**
    @return	name Username
     */
    public String getName() {
        return name;
    }
    /**
    @param name change username
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
    @return	user surname
     */
    public String getSurname() {
        return surname;
    }
    /**
    @param surname change surname user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
    @return user activite
    */
    public Boolean getActive() {
        return active;
    }
    /**
    @param active change active user
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
    /**
    @return user email
     */
    public String getEmail() {
        return email;
    }
    /**
    @param email change email user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
    @return user city
     */
    public String getCity() {
        return city;
    }
    /**
    @param city change city user
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
    @return user creation date
     */
    public String getCreationDate() {
        return creationDate;
    }
    /**
    @param creationDate change user creation date
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


}
