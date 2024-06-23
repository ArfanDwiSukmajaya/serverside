package co.id.mii.serverside.model.dto.request;

public class EmailRequest {
    private String toEMail;
    private String subject;
    private String body;
    private String attachment;

    public EmailRequest() {
    }

    public EmailRequest(String toEMail, String subject, String body) {
        this.toEMail = toEMail;
        this.subject = subject;
        this.body = body;
    }

    public EmailRequest(String toEMail, String subject, String body, String attachment) {
        this.toEMail = toEMail;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }

    public String getToEMail() {
        return toEMail;
    }

    public void setToEMail(String toEMail) {
        this.toEMail = toEMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
