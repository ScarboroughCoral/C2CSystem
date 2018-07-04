package top.scarboroughcoral.c2c.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;

    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String phone;

    private String mail;

    private String address;

    @Column(name = "regist_time")
    private Date registTime;

    @Column(name = "id_card")
    private String IDCard;

    @Column(name = "is_delete")
    private Boolean deleted;

    private String token;

    public User() {
    }

    public User(String username, String password, String name, String phone, String mail, String address, Date registTime, String IDCard, Boolean deleted, String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.registTime = registTime;
        this.IDCard = IDCard;
        this.deleted = deleted;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
