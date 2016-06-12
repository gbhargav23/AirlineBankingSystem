package Bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bank.JDBCAuthentication;

public class BankingModel {

	
int account_balance;
	

	public boolean AccountExists(int account_number){
		
		JDBCAuthentication jdbc = new JDBCAuthentication("cse.unl.edu", "nihar", "nihar", "K=A$QM");
		boolean accountCheck = false;
		ArrayList<Object> param =  new ArrayList<Object>();
		param.add(account_number);

		ResultSet rs1 = jdbc.queryDB("SELECT * from Accounts where account_id=?", param);
		
		try {
			if(rs1.next()){
					accountCheck = true;
					account_balance = rs1.getInt("balance");
					
					
				}
				 
			else{
				System.out.println("no such account exists");
			}
			jdbc.conn.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return accountCheck;
	}
	
	
	public int getaccount_Balance() {
		return account_balance;
	}
	
	// to update account balance
	public void update_accounts (int account_number,int remainingBalance ) throws SQLException{
		
		JDBCAuthentication jdbc = new JDBCAuthentication("cse.unl.edu", "nihar", "nihar", "K=A$QM");

		ArrayList<Object> param =  new ArrayList<Object>();
		param.add(remainingBalance);
		param.add(account_number);
		
		int rowsUpdated = jdbc.updateDB("UPDATE Accounts SET balance=? WHERE account_id=?",param);
		
		if(rowsUpdated>0){
			System.out.println("Successfully update accounts ");
			}else{
			System.out.println("accounts not updated..something went wrong");
			}
			
			
			jdbc.conn.close();
			}
	
}
