package emailapp;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.UUID;


public class Email {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private int mailboxCapacity = 100;
    private String password;
    private int defaultPasswordLength = 5;
    private String alternateEmail = "";


    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        this.password = generatePassword(defaultPasswordLength);
        this.email = firstName.toLowerCase() + '.' + lastName.toLowerCase() + '@' + department + ".com";
    }

    private String setDepartment() {
        System.out.print("DEPARTMENT CODES\n1 - Sales\n2 - Development\n3 - Accounting 0 - None\nEnter department code:");
        Scanner input = new Scanner(System.in);
        int choice_int = input.nextInt();
        return switch (choice_int) {
            case 1 -> "sales";
            case 2 -> "development";
            case 3 -> "account";
            default -> "gmail";
        };
    }

    public String generatePassword(int length) {
        String randomPassword =  UUID.randomUUID().toString();
        return randomPassword.substring(0, length);
    }

    public void setMailboxCapacity(int newCapacity) {
        this.mailboxCapacity = newCapacity;
    }

    public void setAlternateEmail(String newAlternateEmail) {
        this.alternateEmail = newAlternateEmail;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public int getMailboxCapacity() {
        return this.mailboxCapacity;
    }

    public String getAlternateEmail() {
        return this.alternateEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void showEmailInfo() {
        System.out.println("Full name: " + this.firstName + ", " + this.lastName + "\nDepartment: " + this.department + "\nPassword: " + this.password);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
