package dev.fly_yeseul.qna.entities.member;

public class UserEntity {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String intro;
    private byte[] profile;


    public UserEntity() {
    }

    public UserEntity(byte[] profile){
        this.profile = profile;
    }

    public UserEntity(String email, String password, String name, String nickname, String intro, byte[] profile) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.intro = intro;
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserEntity setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
}
