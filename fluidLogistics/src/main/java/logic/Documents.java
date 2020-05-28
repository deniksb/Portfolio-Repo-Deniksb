package logic;

import java.time.LocalDate;
import java.util.Date;

public class Documents {
    private int documentNumber;
    private LocalDate creationDate;
    private String description;
    private DocumentStatus status;

    public Documents(int documentNumber, LocalDate creationDate, String description, DocumentStatus status) {
        this.documentNumber = documentNumber;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
    }

    public Documents(int documentNumber, String description, DocumentStatus status) {
        this.documentNumber = documentNumber;
        this.creationDate = LocalDate.now();
        this.description = description;
        this.status = status;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

}
