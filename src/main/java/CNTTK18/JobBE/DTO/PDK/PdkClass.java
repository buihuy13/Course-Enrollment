package CNTTK18.JobBE.DTO.PDK;

public class PdkClass {
    private String idPdk;
    private String idClass;

    public PdkClass() {
    }

    public PdkClass(String idClass, String idPdk) {
        this.idClass = idClass;
        this.idPdk = idPdk;
    }

    public String getIdPdk() {
        return idPdk;
    }

    public void setIdPdk(String idPdk) {
        this.idPdk = idPdk;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

}
