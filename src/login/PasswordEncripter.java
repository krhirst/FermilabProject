package login ;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public class PasswordEncripter {

	public static int SALT_SIZE = 20 ; // This would be in bytes

	static PasswordManagementUtils pwdMgmtUtils = new PasswordManagementUtils() ;

	public static String[] encriptPass(String pass) {
		String[] vals = new String[2];
		String enteredPassword = pass;

		byte[] salt = generateSalt() ;
		byte[] saltedStretchedPassword = null ;
		try {
			saltedStretchedPassword = pwdMgmtUtils.generateEncryptedPassword(enteredPassword , salt) ;
		} catch(GeneralSecurityException genSecExc) {System.out.println(genSecExc.getMessage() + " " + genSecExc.getCause() ) ; System.exit(1) ; }      

		//store Salt And Encrypted Password(saltedStretchedPassword, salt ) ; 
		vals[0] = pwdMgmtUtils.returnStringRep(saltedStretchedPassword);
		vals[1] = pwdMgmtUtils.returnStringRep(salt);
		
		return vals;
	}


	private static byte[] generateSalt() {
		SecureRandom secRan = new SecureRandom(); 
		byte[] ranBytes = new byte[SALT_SIZE];
		secRan.nextBytes(ranBytes);

		return ranBytes;
	}
}
