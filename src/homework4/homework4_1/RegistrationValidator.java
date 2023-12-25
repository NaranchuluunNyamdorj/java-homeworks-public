package homework4.homework4_1;
public class RegistrationValidator {
    public static boolean validateRegistration(String login, String password, String confirmPassword) {
        try {
            if (login.length() > 20) {
                throw new RegistrationException("Nevtreh ner 20 hurtleh urttai.!");
            }
            if (!login.matches("^[A-Za-z0-9_]+$")) {
                throw new RegistrationException("Nevtreh ner zuvhun latin useg, too, doogur zuraasaas burdene.!");
            }
            if (!password.equals(confirmPassword)) {
                throw new RegistrationException("Batlah nuuts ug ijil bish baina.!");
            }
            if (!password.matches("^[A-Za-z0-9_]+$")) {
                throw new RegistrationException("Nuuts ug zuvhun latin useg, too, doogur zuraasaas burdene.!");
            }
            return true;
        } catch (RegistrationException e) {
            System.out.println("Registration Exception: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String login = "user1234";
        String password = "password1234";
        String confirmPassword = "password123";

        boolean isValid = validateRegistration(login, password, confirmPassword);
        System.out.println("Registration is valid: " + isValid);
    }
}
