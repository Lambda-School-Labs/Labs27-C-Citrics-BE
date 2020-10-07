package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The entity allowing interaction with the users table
 */
@Entity
@Table(name = "users")
public class User
    extends Auditable
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    /**
     * The username (String). Cannot be null and must be unique
     */
    @NotNull
    @Column(unique = false)
    private String username;

    /**
    *The list that holds the users favorite cities
    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<UserCities> favCities = new ArrayList<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public User()
    {
    }

    /**
     * Given the params, create a new user object
     * <p>
     * userid is autogenerated
     *
     * @param username The username (String) of the user
     */
    public User(String username)
    {
        setUsername(username);
    }

    /**
     * Getter for userid
     *
     * @return the userid (long) of the user
     */
    public long getUserid()
    {
        return userid;
    }

    /**
     * Setter for userid. Used primary for seeding data
     *
     * @param userid the new userid (long) of the user
     */
    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    /**
     * Getter for username
     *
     * @return the username (String) lowercase
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter for username
     *
     * @param username the new username (String) converted to lowercase
     */
    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    /**
     *
     * getter and setters for user's fav cities
     */

    public List<UserCities> getFavCities()
    {
        return favCities;
    }

    public void setFavCities(List<UserCities> favCities)
    {
        this.favCities = favCities;
    }

    /**
     * ToString override method
     */
    @Override
    public String toString()
    {
        return "User{" +
            "userid=" + userid +
            ", username='" + username + '\'' +
            ", favCities=" + favCities +
            '}';
    }

    /**
     * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
     * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
     *
     * @return The list of authorities, roles, this user object has
     */
//    @JsonIgnore
//    public List<SimpleGrantedAuthority> getAuthority()
//    {
//        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
//
//        for (UserRoles r : this.roles)
//        {
//            String myRole = "ROLE_" + r.getRole()
//                .getName()
//                .toUpperCase();
//            rtnList.add(new SimpleGrantedAuthority(myRole));
//        }
//
//        return rtnList;
//    }
}
