package com.souce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

@WebServlet("/CompilerServlet")
public class CompilerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps;

	public void init(ServletConfig config) throws ServletException {

		try {
			con = DbConnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception inti  method " + e);
		}

	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String current_date = df.format(dateobj);

		String stud_id = session.getAttribute("fname").toString();

		String as_type = request.getParameter("as_type");

		System.out.println("as type " + as_type);

		{
			/* System.out.println("New Assignment Login "); */
			String fname2 = request.getParameter("program_name");
			String nas_id = request.getParameter("nas_id");
			String assignment = request.getParameter("assignment");
			String program = request.getParameter("program");

			String filename = fname2;

			System.out.println("filename :  " + filename);

			try {

				ps = con.prepareStatement(
						"INSERT INTO `solve_assignment` (`stud_as_id`, `ass_id`, `upload_by`, `solve_date`, `stud_id`, `assignment`, `program`, `program_name`, `output`, `ass_status`, `marks`, `program_sts`) "
								+ "														VALUES (NULL, '" + nas_id
								+ "', 'ritesh', '" + current_date + "','" + stud_id + "', '" + assignment + "', '"
								+ program + "','" + filename + "', '', '', '', '')");

				int r = ps.executeUpdate();
				System.out.println("Data insert " + r);

			} catch (Exception e) {
				System.out.println("Exception Occure " + e);

			}

			String mx_as_idd = "";
			try {
				PreparedStatement ps2 = con.prepareStatement("select MAX(stud_as_id) from solve_assignment");
				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					mx_as_idd = rs2.getString(1);
				}

			} catch (Exception e) {
				System.out.println("Exception e " + e);
			}

			// New Assigment

			Compiler c = new Compiler();
			boolean result = c.storedFile(filename, program);

			System.out.println("file write " + result);

			// boolean cmpl_rslt = c.compileProgram(filename, program, stud_id,
			// nas_id);

			// System.out.println("\n Compile Result " + cmpl_rslt);

			NewProgramCompile npc = new NewProgramCompile();

			System.out.println("n as id is " + nas_id);
			System.out.println("mx_as_idd id is " + mx_as_idd);
			boolean npc_result = npc.compileNewProgram(filename, program, stud_id, mx_as_idd);
			System.out.println("new program compile ==> " + npc_result);
			try {

				Connection con1 = DbConnection.getConnection();

				PreparedStatement ps = con1.prepareStatement("Select * from speechdata where Name ='" + stud_id + "'");
				ResultSet rs = ps.executeQuery();

				System.out.println("c_op =========>  ");

				if (rs.next()) {
					String c_op = rs.getString("output");
					System.out.println("c_op db2 " + c_op);
					if (c_op.equals("")) {

						System.out.println("running after compilation for program ===> " + filename);

						NewProgramRun npr = new NewProgramRun();

						Boolean rc_rslt = npr.runEditProgram(filename, program, stud_id, mx_as_idd);

						System.out.println("Rc Result " + rc_rslt);
						System.out.println("run op Store 2");

					} else {
						System.out.println("Compile Time Output Store2 ");
					}

				}

			} catch (Exception e) {
				System.out.println("E " + e);
			}

			// c.abc(request, response);

			// Reurn Latest Assignment ID to studentHome.jsp Page

			String cm_opp = c.global_cm_op_Method();
			String rn_opp = c.global_rn_op_Method();

			System.out.println();
			System.out.println("Compile OP");
			System.out.println();
			System.out.println(cm_opp);
			System.out.println();

			System.out.println("Run OP");
			System.out.println();
			System.out.println(rn_opp);
			System.out.println();

			response.sendRedirect("studentHome.jsp?as_id=" + mx_as_idd);

		}

	}

}
