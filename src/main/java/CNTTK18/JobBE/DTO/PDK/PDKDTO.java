package CNTTK18.JobBE.DTO.PDK;

public class PDKDTO {
    private int hocki;
    private String namhoc;

    public PDKDTO(int hocki, String namhoc) {
        this.hocki = hocki;
        this.namhoc = namhoc;
    }

    public int getHocki() {
        return hocki;
    }

    public void setHocki(int hocki) {
        this.hocki = hocki;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

}
