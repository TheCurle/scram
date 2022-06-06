package uk.gemwire.scram.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String access;

    @Column(nullable = false, length = 150)
    private String home;

    @Column(nullable = false, length = 60)
    private String password_hash;

    @Column(length = 50)
    private String avatar;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHash() {
        return password_hash;
    }

    public void setHash(String hash) {
        this.password_hash = hash;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", access='" + access + '\'' +
                ", home='" + home + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
