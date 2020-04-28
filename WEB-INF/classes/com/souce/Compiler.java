package com.souce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

public class Compiler {
	HttpServletRequest request1;
	HttpSession session_r;
	static String stud_id_global = "";
	static String as_id_global = "";
	static String global_rn_op = "";
	static String global_cm_op = "";

	public void abc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request1 = request;
	}

	public static boolean compileProgram(String filename, String program, String stud_id, String nas_id) {

		try {
			stud_id_global = stud_id;
			as_id_global = nas_id;

			runProcess("javac " + filename + ".java");

			runProcess("java " + filename);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occure Main " + e);
			return false;
		}

	}

	private static void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		String op = "";
		boolean a = true;
		while ((line = in.readLine()) != null) {
			op = op + line + "  ";
			System.out.println(name + " " + line);
			System.out.println(" verify  " + line);
		}

		System.out.println("Compile time Op is " + op);
		op = op.replace("'", "");
		op = op.replace(",", "");
		op = op.replace(";", "");

		Connection con = DbConnection.getConnection();
		PreparedStatement ps_r;
		Statement st = con.createStatement();

		// int r =
		// st.executeUpdate("update `compiler`.`solve_assignment` SET output='"+
		// op+ "' where stud_id=" + stud_id_global+ " AND ass_id=" +
		// as_id_global + " ");
		ps_r = con.prepareStatement("update `compiler`.`solve_assignment` SET output=? where stud_id=? AND ass_id=?");
		ps_r.setString(1, op);
		ps_r.setString(2, stud_id_global);
		ps_r.setString(3, as_id_global);

		int r = ps_r.executeUpdate();

		if (r > 0) {

			System.out.println("Compile Time Output Data update Succesfully ");
		} else {
			System.out.println("Daat update Failed ");
		}

	}

	static int ii = 0;
	static int opo = 0;

	private static void printLines1(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		String op = "";
		boolean b = true;
		while ((line = in.readLine()) != null) {
			op = op + line;

		}
		ii++;
		System.out.println("Run Time  op is " + ii + "  " + op + b);

		global_rn_op = op;

		Connection con1 = DbConnection.getConnection();
		Statement st1 = con1.createStatement();
		System.out.println(" Run Time final OP is \n " + op + st1);

		boolean rtr = true;

		if (op.equals("")) {
			System.out.println("Run op is null" + rtr);
			rtr = false;

		} else if (opo == 0) {
			opo++;
			rtr = false;

			Connection con_2 = DbConnection.getConnection();
			Statement stp = con_2.createStatement();
			ResultSet rsp = stp.executeQuery("Select * from `compiler`.`solve_assignment` where stud_id='"
					+ stud_id_global + "' AND ass_id='" + as_id_global + "' ");
			if (rsp.next()) {

				Connection con_1 = DbConnection.getConnection();
				PreparedStatement ps_r1;

				String pr = "Error";

				ps_r1 = con_1.prepareStatement(
						"update `compiler`.`solve_assignment` SET ass_status=?,program_sts=? where stud_id=? AND ass_id=?");
				ps_r1.setString(1, op);
				ps_r1.setString(2, pr);
				ps_r1.setString(3, stud_id_global);
				ps_r1.setString(4, as_id_global);

				int r1 = ps_r1.executeUpdate();

				if (r1 > 0) {
					System.out.println("Run Time op  Data update Succesfully in ass sts column \n");
				} else {
					System.out.println("Daat update Failed ");
				}
			} else {
				System.out.print("Not Upload");

			}

		} else {
			System.out.print("OPo is " + opo);
		}

	}

	private static void runProcess(String command) throws Exception {

		Process pro = Runtime.getRuntime().exec(command);

		printLines(command + " stdout:", pro.getInputStream());
		printLines1(command + " stderr:", pro.getErrorStream());
		pro.waitFor();

		// System.out.println(command + " exitValue() " + pro.exitValue());
	}

	public String global_rn_op_Method() {
		System.out.println("///////////////");
		System.out.println("run output Final OP");
		System.out.println("\n\n " + global_rn_op);

		return global_rn_op;
	}

	public String global_cm_op_Method() {

		System.out.println("///////////////");
		System.out.println("compile output final OP");

		System.out.println("\n\n " + global_cm_op);

		return global_cm_op;
	}

	public static boolean storedFile(String fname, String program) {

		try {

			File file = new File("F:/" + fname + ".java");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(program);
			bw.close();

			System.out.println(" file write Done");
			return true;
		} catch (IOException e) {
			System.out.println("Exception" + e);
			e.printStackTrace();
			return false;
		}

	}

}
