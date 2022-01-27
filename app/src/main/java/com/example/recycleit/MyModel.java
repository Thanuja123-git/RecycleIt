package com.example.recycleit;

public class MyModel{
    String img,name,plastic,textview,number,locale,pcode;

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPlastic() {
        return plastic;
    }

    public String getTextview() {
        return textview;
    }

    public String getNumber() {
        return number;
    }

    public String getLocale() {
        return locale;
    }

    public String getcode(){return pcode;};

    public MyModel(String img, String name, String plastic, String number, String locale,String pcode, String textview) {
        this.img = img;
        this.name = name;
        this.plastic = plastic;
        this.textview = textview;
        this.number = number;
        this.locale = locale;
        this.pcode=pcode;
    }

    public MyModel() {
    }

}
