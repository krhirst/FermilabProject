package login ;

import java.security.GeneralSecurityException;
import java.util.Arrays;

public class Authentication {

	static PasswordManagementUtils pwdMgmtUtils = new PasswordManagementUtils() ;

	public static boolean validatePass(String attempt, String stored, String salt) {
		String attemptedPassword = attempt;

		byte[] computePBKDF2AttemptedPassword = null ;
		try {
			computePBKDF2AttemptedPassword = pwdMgmtUtils.generateEncryptedPassword(attemptedPassword, 
					pwdMgmtUtils.returnByteArray(salt));
		} catch(GeneralSecurityException genSecExc) {System.out.println(genSecExc.getMessage() + " " 
			+ genSecExc.getCause()); System.exit(1); }              

		boolean successful_authentication = Arrays.equals(computePBKDF2AttemptedPassword, 
				pwdMgmtUtils.returnByteArray(stored)) ;

		return successful_authentication;
	}
}