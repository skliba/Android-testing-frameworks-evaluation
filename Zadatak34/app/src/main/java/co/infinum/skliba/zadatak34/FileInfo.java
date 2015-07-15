package co.infinum.skliba.zadatak34;

import java.util.Date;

/**
 * Created by noxqs on 13.07.15..
 */
public class FileInfo {
    public String fileName;
    public Date fileDateChanged;

    public FileInfo(String fileName, Date fileDateChanged){
        this.fileName = fileName;
        this.fileDateChanged = fileDateChanged;
    }

    public void setFileName(String name){
        this.fileName = name;
    }

    public void setFileDateChanged(Date dateChanged ){
        this.fileDateChanged = dateChanged;
    }

    public String getFileName(){
        return this.fileName;
    }
}
