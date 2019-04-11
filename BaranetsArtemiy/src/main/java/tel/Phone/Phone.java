package tel.Phone;

public class Phone {

    private Integer id;
    private String markPhone;
    private String modelPhone;
    private Integer agePhone;

    public Phone() {
    }

    public Phone(String markPhone, String modelPhone, Integer agePhone) {
        this.markPhone = markPhone;
        this.modelPhone = modelPhone;
        this.agePhone = agePhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarkPhone() {
        return markPhone;
    }

    public void setMarkPhone(String markPhone) {
        this.markPhone = markPhone;
    }

    public String getModelPhone() {
        return modelPhone;
    }

    public void setModelPhone(String modelPhone) {
        this.modelPhone = modelPhone;
    }

    public Integer getAgePhone() {
        return agePhone;
    }

    public void setAgePhone(Integer agePhone) {
        this.agePhone = agePhone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", markPhone='" + markPhone + '\'' +
                ", modelPhone='" + modelPhone + '\'' +
                ", agePhone=" + agePhone +
                '}';
    }
}
