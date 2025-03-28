package apiPractice;

public class userPOJO {
    //attributes of request payload
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;

    //default constructor
    public userPOJO() {
    }

    //constructor for the POJO class to invoke and pass data
    public userPOJO(String name, String email, String gender, String status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "userPOJO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
