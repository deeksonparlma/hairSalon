import org.sql2o.Connection;

public class clients {
    private String mName;
    private String mContact;
    private String mEmail;
    private String mGender;

    public clients(String name,String contact,String Email,String Gender){
        mName= name;
        mContact=contact;
        mEmail=Email;
        mGender=Gender;

    }

    public String getContact() {
        return mContact;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getGender() {
        return mGender;
    }

    public String getName() {
        return mName;
    }
}
