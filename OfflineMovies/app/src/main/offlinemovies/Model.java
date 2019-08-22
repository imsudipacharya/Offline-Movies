package com.appdeepo.offlinemovies;

public class Model {
    String name, email, profilepic,aurthor,dlink,plink,keydata,category;



    public Model() {
    }


    public Model(String name, String email, String profilepic, String aurthor, String dlink, String plink, String keydata) {
        this.name = name;
        this.email = email;
        this.profilepic = profilepic;
        this.aurthor = aurthor;
        this.dlink = dlink;
        this.plink = plink;
        this.keydata = keydata;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getAurthor() {
        return aurthor;
    }

    public void setAurthor(String aurthor) {
        this.aurthor = aurthor;
    }

    public String getDlink() {
        return dlink;
    }

    public void setDlink(String dlink) {
        this.dlink = dlink;
    }

    public String getPlink() {
        return plink;
    }

    public void setPlink(String plink) {
        this.plink = plink;
    }

    public String getKeydata() {
        return keydata;
    }

    public void setKeydata(String keydata) {
        this.keydata = keydata;
    }
}