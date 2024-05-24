package model;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 5/6/2024, Monday
 **/
public class DataError {

    private String fileName;
    private String recordNumber;
    private String errorCode;
    private String errorClassificationName;
    private String errorDescription;
    private String errorDate;


    public DataError(String fileName, String recordNumber, String errorCode, String errorClassificationName, String errorDescription, String errorDate) {
        this.fileName = fileName;
        this.recordNumber = recordNumber;
        this.errorCode = errorCode;
        this.errorClassificationName = errorClassificationName;
        this.errorDescription = errorDescription;
        this.errorDate = errorDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorClassificationName() {
        return errorClassificationName;
    }

    public void setErrorClassificationName(String errorClassificationName) {
        this.errorClassificationName = errorClassificationName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }

    @Override
    public String toString() {
        return "DataError{" +
                "FILE_NAME='" + fileName + '\'' +
                ", RECORD_NUMBER='" + recordNumber + '\'' +
                ", ERROR_CODE='" + errorCode + '\'' +
                ", ERROR_CLASSIFICATION_NAME='" + errorClassificationName + '\'' +
                ", ERROR_DESCRIPTION='" + errorDescription + '\'' +
                ", ERROR_DATE='" + errorDate + '\'' +
                '}';
    }
}
