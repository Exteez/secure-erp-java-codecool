package com.codecool.secureerp.model;

public class CRMModel {
    private String id;
    private String name;
    private String email;
    private boolean subscribed;

    public String[] toTableRow() {
        int sub = subscribed ? 1 : 0;
        return new String[]{
                id, name, email, String.valueOf(sub)
        };
    }

    public CRMModel(String id, String name, String email, boolean subscribed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscribed = subscribed;
    }

    public String generateId() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
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

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
