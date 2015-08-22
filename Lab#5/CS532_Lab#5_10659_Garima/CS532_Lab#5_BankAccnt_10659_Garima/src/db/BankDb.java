package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.AccountDbFailure;

/* Methods to perform operations on the bank database */
public class BankDb {
	private static int maxTimesToRetry = 3;

	public static void doTransferRetry(int fromAcctId, double amt, int toAcctId)
			throws AccountDbFailure, SQLException {
		double curBal;
		double newBalance = 0.0;
		int rowsAffected;
		Connection dbConn;
		
		String updateCurBalSql = "UPDATE account SET balance = ?  where id = ?  ;";

		dbConn = BankingAppDataSource.getConnection();
		dbConn.setAutoCommit(false);

		curBal = getAcctBalanceWithConn(fromAcctId, dbConn);
		newBalance = curBal - amt;

		try (PreparedStatement updateCurBalStmt = dbConn
				.prepareStatement(updateCurBalSql)) {
			
			updateCurBalStmt.setDouble(1, newBalance);
			updateCurBalStmt.setInt(2, fromAcctId);
			rowsAffected = updateCurBalStmt.executeUpdate();
			if (rowsAffected != 1) {
				dbConn.rollback();
				throw new AccountDbFailure(AccountDbFailure.STMT_FAILED);
			}

			depositToAccountWithConn(toAcctId, amt,dbConn);
			dbConn.commit();
		} catch (SQLException ex) {
			dbConn.rollback();
			throw new AccountDbFailure(AccountDbFailure.STMT_FAILED);
		}

		dbConn.close();

	}

	/*
	 * Perform a deposit operation -- call this method if we already have a
	 * connection
	 */
	public static void depositToAccountWithConn(int accountId, double amt,
			Connection dbConn) throws AccountDbFailure, SQLException {
		double curBal;
		double newBalance = 0.0;
		int rowsAffected;
		String updateCurBalSql = "UPDATE account SET balance = ? where id = ? AND balance = ? ;";

		// Get the current balance
		curBal = getAcctBalanceWithConn(accountId, dbConn);

		newBalance = curBal + amt;

		// Update the balance
		try (PreparedStatement updateCurBalStmt = dbConn
				.prepareStatement(updateCurBalSql)) {
			updateCurBalStmt.setDouble(1, newBalance);
			updateCurBalStmt.setInt(2, accountId);
			updateCurBalStmt.setDouble(3, curBal);
			rowsAffected = updateCurBalStmt.executeUpdate();
			if (rowsAffected != 1) { /* Exactly one row should have been updated */
				if (rowsAffected == 0) {
					throw new AccountDbFailure(AccountDbFailure.RETRY);
				}

				/* More than one row modified? */
				throw new AccountDbFailure(AccountDbFailure.STMT_FAILED,
						"Expected only one row to be affected by the deposit operation");
			}
		}
	}

	/*
	 * Call this method if we are not currently using a connection (so we need
	 * to obtain a connection)
	 */
	/*
	 * The method: 1) obtains a connection, 2) starts a transaction, 3) performs
	 * the database work, 4) commits or rolls back the connection, 5) releases
	 * the connection
	 */
	public static void depositToAccount(int accountId, double amt)
			throws AccountDbFailure, SQLException {

		try (Connection dbConn = BankingAppDataSource.getConnection()) {
			// Turn off auto-commit so we can use transactions
			dbConn.setAutoCommit(false);

			/*
			 * We are now operating under a transaction, rollback on an
			 * exception or commit on successful completion
			 */
			try {
				depositToAccountWithConn(accountId, amt, dbConn);
				dbConn.commit(); /*
				 * End of transaction. Since it was successful,
				 * commit it.
				 */
			} catch (AccountDbFailure ex) {
				dbConn.rollback();
				throw ex; /*
				 * rethrow the exception so the caller function can
				 * recover from this failure
				 */
			} catch (Exception ex) {
				/*
				 * This is a catch-all -- rollback transaction and throw a more
				 * useful exception
				 */
				dbConn.rollback();
				throw new AccountDbFailure(AccountDbFailure.STMT_FAILED,
						ex.getMessage());
			}
		}
	}

	/*
	 * If there is a failure in doing the deposit because of other connections
	 * using the same row, retry the operation.
	 */
	public static void doDepositToAcctRetry(int accountId, double amt)
			throws AccountDbFailure, SQLException {
		int numAttempts = 0;

		while (numAttempts < maxTimesToRetry) {
			try {
				depositToAccount(accountId, amt);
				return;
			} catch (AccountDbFailure dbFailureEx) {
				if (dbFailureEx.getFailureReason() != AccountDbFailure.RETRY) {
					/* Rethrow the exception, we can't handle it here. */
					throw dbFailureEx;
				}
			}

			numAttempts++;
		}

		throw new AccountDbFailure(
				AccountDbFailure.RETRY_LIMIT_EXCEEDED,
				maxTimesToRetry
				+ " attempts to perform the update were unsuccessful.  Aborting operation.");
	}

	/*
	 * Given a Statement, execute it to get the result set, then pull the
	 * balance from the result set
	 */
	private static double doAcctBalanceQuery(int accountId,
			PreparedStatement readCurBalStmt) throws AccountDbFailure,
			SQLException {
		double curBal;

		readCurBalStmt.setInt(1, accountId);

		// Get the current balance
		try (ResultSet results = readCurBalStmt.executeQuery();) {
			if (!results.next()) {
				throw new AccountDbFailure(AccountDbFailure.BAD_ACCT_ID,
						"Unknown account id: " + accountId);
			} else {
				curBal = results.getDouble("balance");
			}
		}

		return curBal;
	}

	private static double getAcctBalanceWithConn(int accountId,
			Connection dbConn) throws AccountDbFailure, SQLException {
		double curBal;
		String readCurBalSql = "SELECT balance " + "FROM account "
				+ "WHERE id = ?";

		// Get the current balance
		try (PreparedStatement readCurBalStmt = dbConn
				.prepareStatement(readCurBalSql)) {
			/*
			 * Avoid putting a try-block inside another try block -- it looks
			 * messy. To avoid this, put the try block inside function
			 * doAcctBalanceQuery(). Your code will look simpler, neater, and
			 * more professional!
			 */
			curBal = doAcctBalanceQuery(accountId, readCurBalStmt);
		}

		return curBal;
	}

	/*
	 * Call this method if we are not currently using a connection (so we need
	 * to obtain a connection)
	 */
	public static double getAcctBalance(int accountId) throws AccountDbFailure,
	SQLException {
		double curBal = 0.0;

		try (Connection dbConn = BankingAppDataSource.getConnection()) {
			curBal = getAcctBalanceWithConn(accountId, dbConn);
		}

		return curBal;
	}
}
