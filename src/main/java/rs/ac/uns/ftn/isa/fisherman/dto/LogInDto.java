package rs.ac.uns.ftn.isa.fisherman.dto;

import java.util.Objects;

public class LogInDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LogInDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LogInDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInDto logInDto = (LogInDto) o;
        return Objects.equals(username, logInDto.username) && Objects.equals(password, logInDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "LogInDto{" +
                "email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
