package model;

public class Invitation
{
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public enum STATUS
    {
        PENDING,
        ACCEPTED,
        REJECTEd
    }

    private String source;
    private String dest;
    private STATUS status;

    public Invitation(String source, String des) {
        this.source=source;
        this.dest=des;
        this.status=STATUS.PENDING;
    }

    public Invitation() {
    }
}