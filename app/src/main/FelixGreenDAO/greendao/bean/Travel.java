package greendao.bean;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table travel.
 */
public class Travel {

    private Long id;
    private String area_name;
    private String area_station;
    private String area_pic;
    private String area_desc;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Travel() {
    }

    public Travel(Long id) {
        this.id = id;
    }

    public Travel(Long id, String area_name, String area_station, String area_pic, String area_desc) {
        this.id = id;
        this.area_name = area_name;
        this.area_station = area_station;
        this.area_pic = area_pic;
        this.area_desc = area_desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_station() {
        return area_station;
    }

    public void setArea_station(String area_station) {
        this.area_station = area_station;
    }

    public String getArea_pic() {
        return area_pic;
    }

    public void setArea_pic(String area_pic) {
        this.area_pic = area_pic;
    }

    public String getArea_desc() {
        return area_desc;
    }

    public void setArea_desc(String area_desc) {
        this.area_desc = area_desc;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}