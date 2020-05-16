package accounts.acbtz.jagroadmin;


public class DataModel {

    String id;
    String fruitName;
    String fruitDescription;
    String pricePerOne;
    String image;
    String type;



    public DataModel(String id, String fruitName,String fruitDescription,String pricePerOne, String image, String type)
    {
        this.id=id;
        this.fruitName=fruitName;
        this.fruitDescription=fruitDescription;
        this.pricePerOne=pricePerOne;
        this.image=image;
        this.type=type;


    }

    public String getid() {
        return id;
    }

    public String getfruitName() {
        return fruitName;
    }

    public String getfruitDescription() {
        return fruitDescription;
    }

    public String getpricePerOne() {
        return pricePerOne;
    }

    public String getimage() {
        return image;
    }

    public String gettype() {
        return type;
    }


}
