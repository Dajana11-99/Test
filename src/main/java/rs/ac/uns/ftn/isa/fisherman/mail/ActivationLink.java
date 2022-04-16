package rs.ac.uns.ftn.isa.fisherman.mail;

public class ActivationLink implements MailFormatter<String>{
    @Override
    public String getText(String link) {
    return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>Welcome to Fisherman!</h1>\n" +
            "    <h2>Follow this link to activate your account: " + link + " . </h2>\n" +
            "    <br>\n" +
            "    <h2>Sincerely,</h2>\n" +
            "    <h2>Fisherman team.</h2>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}

    @Override
    public String getSubject() {
        return "Fisherman account activation link";
    }
}
