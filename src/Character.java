public class Character {
    private int id;
    private String name;
    private String antagonist;
    private String waytofight;
    private String place;
    private String date;
    private double einfluss;

    public Character(int id, String name, String antagonist, String waytofight, String place, String date, double einfluss) {
        this.id = id;
        this.name = name;
        this.antagonist = antagonist;
        this.waytofight = waytofight;
        this.place = place;
        this.date = date;
        this.einfluss = einfluss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAntagonist() {
        return antagonist;
    }

    public void setAntagonist(String antagonist) {
        this.antagonist = antagonist;
    }

    public String getWaytofight() {
        return waytofight;
    }

    public void setWaytofight(String waytofight) {
        this.waytofight = waytofight;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getEinfluss() {
        return einfluss;
    }

    public void setEinfluss(double einfluss) {
        this.einfluss = einfluss;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", antagonist='" + antagonist + '\'' +
                ", waytofight='" + waytofight + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", einfluss=" + einfluss +
                '}';
    }
}
