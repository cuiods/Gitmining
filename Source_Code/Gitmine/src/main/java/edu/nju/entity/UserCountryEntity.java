package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/6/6.
 */
@Entity
@Table(name = "user_country", schema = "gitmining", catalog = "")
public class UserCountryEntity {
    private String country;
    private int number;

    @Id
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCountryEntity that = (UserCountryEntity) o;

        if (number != that.number) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + number;
        return result;
    }
}
