package rs.ac.uns.ftn.isa.fisherman.mail;

public interface MailFormatter<T> {
        String getText(T params);
        String getSubject();
}
