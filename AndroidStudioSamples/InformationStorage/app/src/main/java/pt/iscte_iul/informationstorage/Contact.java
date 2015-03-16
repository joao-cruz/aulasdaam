package pt.iscte_iul.informationstorage;

/**
 * Created by cserrao on 15/03/15.
 */
public class Contact {
    int _id;
    String _name;
    String _email;
    String _telephone;

    public Contact() {

    }

    public Contact(int id, String name, String email, String telephone) {
        this._id = id;
        this._name = name;
        this._email = email;
        this._telephone = telephone;
    }

    public Contact(String name, String email, String telephone) {
        this._name = name;
        this._email = email;
        this._telephone = telephone;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getEmail() {
        return this._email;
    }

    public void setTelephone(String telephone) {
        this._telephone = telephone;
    }

    public String getTelephone() {
        return this._telephone;
    }

}
