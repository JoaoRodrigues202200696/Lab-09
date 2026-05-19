package ips.poo.model;

public class Request {

    private String userType;
    private String name;
    private Equipment equipment;
    private String description;

    public Request(String userType, String name,
                   Equipment equipment, String description) {

        this.userType = userType;
        this.name = name;
        this.equipment = equipment;
        this.description = description;
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", equipment=" + equipment +
                ", description='" + description + '\'' +
                '}';
    }
}
