package accounts.acbtz.jagroadmin;


public class DataModel1 {

    String timestamp;
    String name;
    String love;
    String gender;
    String picha;
    String umri;
    String area;
    String maelezo;
    String simcard;
    String reg_date;
    String meseji;
    String state;
    String joint;




    public DataModel1(String timestamp, String name, String love, String gender, String picha, String umri, String area, String maelezo, String simcard, String reg_date, String meseji, String state, String joint)
    {
        this.timestamp=timestamp;
        this.name=name;
        this.love=love;
        this.gender=gender;
        this.picha=picha;
        this.umri=umri;
        this.area=area;
        this.maelezo=maelezo;
        this.simcard=simcard;
        this.reg_date=reg_date;
        this.meseji=meseji;
        this.state=state;
        this.joint=joint;

    }

    public String gettimestamp() {
        return timestamp;
    }

    public String getname() {
        return name;
    }

    public String getlove() {
        return love;
    }

    public String getgender() {
        return gender;
    }

    public String getpicha() {
        return picha;
    }

    public String getumri() {
        return umri;
    }

    public String getarea() {
        return area;
    }

    public String getmaelezo() {
        return maelezo;
    }

    public String getsimcard() {
        return simcard;
    }

    public String getreg_date() {
        return reg_date;
    }

    public String getmeseji() {
        return meseji;
    }

    public String getstate() {
        return state;
    }

    public String getjoint() {
        return joint;
    }

}
