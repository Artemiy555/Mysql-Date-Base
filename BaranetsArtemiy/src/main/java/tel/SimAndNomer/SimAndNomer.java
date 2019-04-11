package tel.SimAndNomer;

public class SimAndNomer {
    private Integer id;
    private String nomer;
    private String sim;

    public SimAndNomer() {

    }

    public SimAndNomer(String nomer, String sim) {
        this.nomer = nomer;
        this.sim = sim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }
}
