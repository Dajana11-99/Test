package com.example.demo.mail;

public class CabinReservationSuccessfulInfo implements MailFormatter<String>{
    @Override
    public String getText(String message) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1> Successful reservation for cabin!</h1>\n" +
                "    <h2>Your request for reservation has been successful. </h2>\n" +
                "    <br>\n" +
                "<h2>" + message + "<h2>\n" +
                "    <h2>Sincerely,</h2>\n" +
                "    <h2>Fisherman team.</h2>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getSubject() {
        return "Fisherman cabin reservation";
    }
}
