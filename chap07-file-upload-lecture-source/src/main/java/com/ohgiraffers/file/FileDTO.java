package com.ohgiraffers.file;

/* 업로드 된 파일과 관련한 정보를 모아서 관리하는 DTO */
public class FileDTO {

    private String originFileName;
    private String savedName;
    private String filPath;
    private String fileDescription;

    public FileDTO() {}

    public FileDTO(String originFileName, String savedName, String filPath, String fileDescription) {
        this.originFileName = originFileName;
        this.savedName = savedName;
        this.filPath = filPath;
        this.fileDescription = fileDescription;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getFilPath() {
        return filPath;
    }

    public void setFilPath(String filPath) {
        this.filPath = filPath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", filPath='" + filPath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
