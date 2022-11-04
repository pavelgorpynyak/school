package ruhogwartsschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    private Student student;

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath( String filePath ) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize( long fileSize ) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType( String mediaType ) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData( byte[] data ) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent( Student student ) {
        this.student = student;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Avatar)) return false;
        Avatar avatar = (Avatar) o;
        return getId() == avatar.getId() && getFileSize() == avatar.getFileSize() && Objects.equals(getFilePath(), avatar.getFilePath()) && Objects.equals(getMediaType(), avatar.getMediaType()) && Arrays.equals(getData(), avatar.getData()) && Objects.equals(getStudent(), avatar.getStudent());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getFilePath(), getFileSize(), getMediaType(), getStudent());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", student=" + student +
                '}';
    }
}
