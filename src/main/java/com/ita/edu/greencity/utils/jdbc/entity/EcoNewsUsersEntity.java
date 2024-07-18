package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsUsersEntityFields {
    ID(0),
    DATE_OF_REGISTRATION(1),
    EMAIL(2),
    EMAIL_NOTIFICATION(3),
    NAME(4),
    ROLE(5),
    USER_STATUS(6),
    REFRESH_TOKEN_KEY(7),
    PROFILE_PICTURE(8),
    RATING(9),
    LAST_ACTIVITY_TIME(10),
    FIRST_NAME(11),
    CITY(12),
    USER_CREDO(13),
    SHOW_LOCATION(14),
    SHOW_ECO_PLACE(15),
    SHOW_SHOPPING_LIST(16),
    LANGUAGE_ID(17),
    UUID(18),
    PHONE_NUMBER(19);

    private final int number;

    EcoNewsUsersEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EcoNewsUsersEntity {
    public static final String SELECT_ALL = "SELECT * FROM users;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM users WHERE %s = '%s';";
    public static final String DELETE_BY_ID = "DELETE FROM users WHERE id = %s;";

    private long id;
    private String dateOfRegistration;
    private String email;
    private int emailNotification;
    private String name;
    private int role;
    private int userStatus;
    private String refreshTokenKey;
    private String profilePicture;
    private double rating;
    private String lastActivityTime;
    private String firstName;
    private String city;
    private String userCredo;
    private boolean showLocation;
    private boolean showEcoPlace;
    private boolean showShoppingList;
    private long languageId;
    private String uuid;
    private String phoneNumber;

    public EcoNewsUsersEntity(long id, String dateOfRegistration, String email, int emailNotification, String name, int role, int userStatus, String refreshTokenKey, String profilePicture, double rating, String lastActivityTime, String firstName, String city, String userCredo, boolean showLocation, boolean showEcoPlace, boolean showShoppingList, long languageId, String uuid, String phoneNumber) {
        this.id = id;
        this.dateOfRegistration = dateOfRegistration;
        this.email = email;
        this.emailNotification = emailNotification;
        this.name = name;
        this.role = role;
        this.userStatus = userStatus;
        this.refreshTokenKey = refreshTokenKey;
        this.profilePicture = profilePicture;
        this.rating = rating;
        this.lastActivityTime = lastActivityTime;
        this.firstName = firstName;
        this.city = city;
        this.userCredo = userCredo;
        this.showLocation = showLocation;
        this.showEcoPlace = showEcoPlace;
        this.showShoppingList = showShoppingList;
        this.languageId = languageId;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
    }

    public EcoNewsUsersEntity() {
        this.id = 0;
        this.dateOfRegistration = null;
        this.email = null;
        this.emailNotification = 0;
        this.name = null;
        this.role = 0;
        this.userStatus = 0;
        this.refreshTokenKey = null;
        this.profilePicture = null;
        this.rating = 0;
        this.lastActivityTime = null;
        this.firstName = null;
        this.city = null;
        this.userCredo = null;
        this.showLocation = false;
        this.showEcoPlace = false;
        this.showShoppingList = false;
        this.languageId = 0;
        this.uuid = null;
        this.phoneNumber = null;
    }

    public static EcoNewsUsersEntity getEcoNewsUsersEntity(List<String> row) {
        return new EcoNewsUsersEntity()
                .setId(Long.parseLong(row.get(EcoNewsUsersEntityFields.ID.getNumber())))
                .setDateOfRegistration(row.get(EcoNewsUsersEntityFields.DATE_OF_REGISTRATION.getNumber()))
                .setEmail(row.get(EcoNewsUsersEntityFields.EMAIL.getNumber()))
                .setEmailNotification(Integer.parseInt(row.get(EcoNewsUsersEntityFields.EMAIL_NOTIFICATION.getNumber())))
                .setName(row.get(EcoNewsUsersEntityFields.NAME.getNumber()))
                .setRole(Integer.parseInt(row.get(EcoNewsUsersEntityFields.ROLE.getNumber())))
                .setUserStatus(Integer.parseInt(row.get(EcoNewsUsersEntityFields.USER_STATUS.getNumber())))
                .setRefreshTokenKey(row.get(EcoNewsUsersEntityFields.REFRESH_TOKEN_KEY.getNumber()))
                .setProfilePicture(row.get(EcoNewsUsersEntityFields.PROFILE_PICTURE.getNumber()))
                .setRating(Double.parseDouble(row.get(EcoNewsUsersEntityFields.RATING.getNumber())))
                .setLastActivityTime(row.get(EcoNewsUsersEntityFields.LAST_ACTIVITY_TIME.getNumber()))
                .setFirstName(row.get(EcoNewsUsersEntityFields.FIRST_NAME.getNumber()))
                .setCity(row.get(EcoNewsUsersEntityFields.CITY.getNumber()))
                .setUserCredo(row.get(EcoNewsUsersEntityFields.USER_CREDO.getNumber()))
                .setShowLocation(Boolean.parseBoolean(row.get(EcoNewsUsersEntityFields.SHOW_LOCATION.getNumber())))
                .setShowEcoPlace(Boolean.parseBoolean(row.get(EcoNewsUsersEntityFields.SHOW_ECO_PLACE.getNumber())))
                .setShowShoppingList(Boolean.parseBoolean(row.get(EcoNewsUsersEntityFields.SHOW_SHOPPING_LIST.getNumber())))
                .setLanguageId(Long.parseLong(row.get(EcoNewsUsersEntityFields.LANGUAGE_ID.getNumber())))
                .setUuid(row.get(EcoNewsUsersEntityFields.UUID.getNumber()))
                .setPhoneNumber(row.get(EcoNewsUsersEntityFields.PHONE_NUMBER.getNumber()));
    }

    public static List<EcoNewsUsersEntity> getListEcoNewsUsersEntity(List<List<String>> rows) {
        List<EcoNewsUsersEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsUsersEntity(currentRow));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public EcoNewsUsersEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public EcoNewsUsersEntity setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EcoNewsUsersEntity setEmail(String email) {
        this.email = email;
        return this;

    }

    public int getEmailNotification() {
        return emailNotification;
    }

    public EcoNewsUsersEntity setEmailNotification(int emailNotification) {
        this.emailNotification = emailNotification;
        return this;
    }

    public String getName() {
        return name;
    }

    public EcoNewsUsersEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getRole() {
        return role;
    }

    public EcoNewsUsersEntity setRole(int role) {
        this.role = role;
        return this;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public EcoNewsUsersEntity setUserStatus(int userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public String getRefreshTokenKey() {
        return refreshTokenKey;
    }

    public EcoNewsUsersEntity setRefreshTokenKey(String refreshTokenKey) {
        this.refreshTokenKey = refreshTokenKey;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public EcoNewsUsersEntity setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public EcoNewsUsersEntity setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public String getLastActivityTime() {
        return lastActivityTime;
    }

    public EcoNewsUsersEntity setLastActivityTime(String lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EcoNewsUsersEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public EcoNewsUsersEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getUserCredo() {
        return userCredo;
    }

    public EcoNewsUsersEntity setUserCredo(String userCredo) {
        this.userCredo = userCredo;
        return this;
    }

    public boolean isShowLocation() {
        return showLocation;
    }

    public EcoNewsUsersEntity setShowLocation(boolean showLocation) {
        this.showLocation = showLocation;
        return this;
    }

    public boolean isShowEcoPlace() {
        return showEcoPlace;
    }

    public EcoNewsUsersEntity setShowEcoPlace(boolean showEcoPlace) {
        this.showEcoPlace = showEcoPlace;
        return this;
    }

    public boolean isShowShoppingList() {
        return showShoppingList;
    }

    public EcoNewsUsersEntity setShowShoppingList(boolean showShoppingList) {
        this.showShoppingList = showShoppingList;
        return this;
    }

    public long getLanguageId() {
        return languageId;
    }

    public EcoNewsUsersEntity setLanguageId(long languageId) {
        this.languageId = languageId;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public EcoNewsUsersEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EcoNewsUsersEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsUsersEntity{" +
                "id=" + id +
                ", dateOfRegistration=" + dateOfRegistration +
                ", email='" + email + '\'' +
                ", emailNotification=" + emailNotification +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                ", refreshTokenKey='" + refreshTokenKey + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", rating=" + rating +
                ", lastActivityTime=" + lastActivityTime +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", userCredo='" + userCredo + '\'' +
                ", showLocation=" + showLocation +
                ", showEcoPlace=" + showEcoPlace +
                ", showShoppingList=" + showShoppingList +
                ", languageId=" + languageId +
                ", uuid='" + uuid + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
