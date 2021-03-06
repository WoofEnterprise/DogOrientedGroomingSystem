package org.woofenterprise.dogs.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO for Customer entity.
 *
 * @author michal.babel@embedit.cz
 */
public class CustomerDTO {

    private Long id;

    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String name;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String surname;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    @Pattern(regexp = "^\\S+@\\S+$", message = "Must be an email address.")
    private String email;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressFirstLine;
    private String addressSecondLine;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressCity;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressCountry;
    
    @NotNull(message = "Must be present.")
    @Size(min=2, max=255)
    private String addressPostalCode;
    
    @Pattern(regexp = "[0-9-()-.+ ]*", message = "Must contain only 0-9, (), +- . or spaces")
    private String phoneNumber;

    private boolean admin;

    @JsonManagedReference
    private Set<DogDTO> dogs = new HashSet<>();

    /**
     * Returns id of referenced entity.
     *
     * @return id of referenced entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of referenced entity.
     *
     * @param id id of referenced entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns customers first name.
     *
     * @return customers first name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customers first name.
     *
     * @param name customers first name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns customers last name.
     *
     * @return customers last name
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets customers last name.
     *
     * @param surname customers last name
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns email of a customer.
     *
     * @return email of customer
     */

    public String getEmail() {
        return email;
    }

    /**
     * Sets customers email.
     *
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return first line of customers address.
     *
     * @return first line of customers address
     */
    public String getAddressFirstLine() {
        return addressFirstLine;
    }

    /**
     * Sets first line of customers address.
     *
     * @param addressFirstLine first line of customers address
     */
    public void setAddressFirstLine(String addressFirstLine) {
        this.addressFirstLine = addressFirstLine;
    }

    /**
     * Returns second line of customers address.
     *
     * @return second line of customers address
     */
    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    /**
     * Sets second line of customers address.
     *
     * @param addressSecondLine second line of customers address
     */
    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    /**
     * Returns customers city.
     *
     * @return customers city
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets customers city.
     *
     * @param addressCity customers city
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * Returns customers country.
     *
     * @return customers country
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * Sets customers country.
     *
     * @param addressCountry customers country
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    /**
     * Returns customers postal code.
     *
     * @return customers postal code
     */
    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    /**
     * Sets customers postal code.
     *
     * @param addressPostalCode customers postal code
     */
    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    /**
     * Returns customers phone number.
     *
     * @return customers phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets customers phone number
     *
     * @param phoneNumber customers phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns customers dogs.
     *
     * @return customers dogs.
     */
    public Set<DogDTO> getDogs() {
        return dogs;
    }

    /**
     * Sets customers dogs.
     *
     * @param dogs customers dogs
     */

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setDogs(Set<DogDTO> dogs) {
        this.dogs = dogs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (this.id != null) {
            hash = 37 * hash + Objects.hashCode(this.id);
        } else {
            hash = 37 * hash + System.identityHashCode(this);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDTO other = (CustomerDTO) obj;
        if (this.id != null) {
            return Objects.equals(this.id, other.id);
        } else {
            return this == other;
        }
    }

}
