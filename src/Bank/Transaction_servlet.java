package Bank;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transaction_servlet
 */
@WebServlet("/Transaction_servlet")
public class Transaction_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int account_number = Integer.parseInt(request.getParameter("account_number"));
		int total_purchaseCost= Integer.parseInt(request.getParameter("total_purchaseCost"));
		
		BankingModel bank = new BankingModel();
		if(bank.AccountExists(account_number))	{
			int account_balance = bank.getaccount_Balance();
			
			if(account_balance > total_purchaseCost){
				
				int remainingBalance = (account_balance - total_purchaseCost);
			
				try {
					bank.update_accounts(account_number,remainingBalance);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				response.getWriter().write("Transaction Successful");
			}
			else{
				response.getWriter().write("No Funds");
			}
		}
		else{
			response.getWriter().write("No Account");
		}
	}

}
