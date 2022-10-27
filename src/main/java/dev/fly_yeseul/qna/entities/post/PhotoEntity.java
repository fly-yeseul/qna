package dev.fly_yeseul.qna.entities.post;

public class PhotoEntity {
    private String id;
    private int index;
    private byte[] data;

    public PhotoEntity() {
    }

    public PhotoEntity(String id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public PhotoEntity(String id, int index, byte[] data) {
        this.id = id;
        this.index = index;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
