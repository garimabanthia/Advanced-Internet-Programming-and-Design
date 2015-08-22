package application;

import java.sql.SQLException;

import db.BankDb;
import db.BankingAppDataSource;
import exceptions.AccountDbFailure;

/* Test that multiple DB connections have been created.   Deposit money to the same account --
 * both reads are done before the first commit.   So second deposit (second connection) will overwrite the first.
 */
public class UpdateBankAcc {
	private static int accountId = 2;
	private static int fromAccountId = 1;

	public static void main(String args[]) {

		/*
		 * Comment out one of the two methods below and run. The call to
		 * connectionExperiment() is an experiment showing problems that can
		 * happen with multiple connections. The call to simpleDeposit() shows
		 * how normally a client would attempt to make a deposit.
		 */
		try {
			transferMoney();
		} finally {
			try {
				BankingAppDataSource.shutdownDataSource();
			} catch (SQLException ex) {
				System.out.println("Error shutting down DataSource");
			}
		}
	}

	public static void transferMoney() {
		double initialBalFrom, finalBalFrom, initialBalTo, finalBalTo;
		try {
			initialBalTo = BankDb.getAcctBalance(accountId);
			initialBalFrom = BankDb.getAcctBalance(fromAccountId);

			BankDb.doTransferRetry(fromAccountId, 10.0, accountId);

			finalBalTo = BankDb.getAcctBalance(accountId);
			finalBalFrom = BankDb.getAcctBalance(fromAccountId);


			System.out.println("******Account1******" + "(Withdraw)");	
			System.out.println("Initial Balance: " + initialBalFrom );
			System.out.println("Final Balance:   " + finalBalFrom);

			//System.out.println("                                                                          ");
			System.out.println("----------------------------------------------------------------------");
			
			System.out.println("******Account2******" + "(Deposit)");
			System.out.println("Initial Balance: " + initialBalTo);
			System.out.println("Final Balance:   "  + finalBalTo);
			
			

			
		} catch (AccountDbFailure ex) {
			System.out.println("Failure with Database operation: "
					+ ex.getReasonStr());
		} catch (SQLException ex) {
			System.out.println("Database operation failure: " + ex);
		}
	}

}
