package com.souce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DbConnection;

public class EditProgram 
{

	static String stud_id_global = "";
	static int flag = 0;
	static int flag_c = 0;

	static String as_id_global = "";

	public static boolean compileEditProgram(String filename, String program,
			String stud_id, String nas_id) 
	{
		try {
			stud_id_global = stud_id;
			as_id_global = nas_id;

			runProcess("javac " + filename + ".java");
			runProcess("java " + filename);
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Exception Occure Main " + e);
			return false;
		}
	}

	private static void printLines(String name, InputStream ins)
			throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		String op = "";
		boolean a = true;
		while ((line = in.readLine()) != null) {
			op = op + line + "  ";
		//	System.out.println(name + " " + line);
	//		System.out.println(" verify  " + line);
		}

		// Its No Error
		System.out.println("Compile time Final Op is " + op);
		op = op.replace("'", "");
		op = op.replace(",", "");
		op = op.replace(";", "");

		System.out.println("stude id " + stud_id_global);
		System.out.println("stude id " + as_id_global);
		
		System.out.println("Flag c "+flag_c);
//		flag_c--;
		if (flag_c == 0) 
		{

			Connection con = DbConnection.getConnection();
			PreparedStatement ps_r;
			// Statement st = con.createStatement();

			ps_r = con
					.prepareStatement("update `compiler`.`solve_assignment` SET output=? where stud_id=? AND stud_as_id=?");
			ps_r.setString(1, op);
			ps_r.setString(2, stud_id_global);
			ps_r.setString(3, as_id_global);

			int r = ps_r.executeUpdate();

			if (r > 0) {

				System.out.println("Compile Time Output Data update Succesfully ");
			} else {
				System.out.println("Compile Time Output Daat update Failed ");
			}
			flag_c++;
		}
	}

	static int ii = 0;
	static int opo = 0;

	private static void printLines1(String name, InputStream ins)
			throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		String op = "";
		boolean b = true;
		while ((line = in.readLine()) != null) {
			op = op + line;

		}
		ii++;
		System.out.println("Run Time  op is " + ii + "  " + op);

		Connection con1 = DbConnection.getConnection();
		Statement st1 = con1.createStatement();

		// if Error Occure

		System.out.println(" Run Time final OP is \n " + op);

		System.out.println("stude id r1" + op);
		System.out.println("stude id r1" + stud_id_global);
		System.out.println("stude id r2 " + as_id_global);
		System.out.println("Flag "+flag);

		if (flag == 0) {

			System.out.println("Flag is " + flag);
			Connection con_1 = DbConnection.getConnection();
			PreparedStatement ps_r1;

			String pr = "Error";
			ps_r1 = con_1
					.prepareStatement("update `compiler`.`solve_assignment` SET ass_status=?,program_sts=? where stud_id=? AND stud_as_id=?");
			ps_r1.setString(1, op);
			ps_r1.setString(2, pr);
			ps_r1.setString(3, stud_id_global);
			ps_r1.setString(4, as_id_global);

			int r1 = ps_r1.executeUpdate();

			if (r1 > 0) {
				System.out
						.println("Run Time op  Data update Succesfully in ass sts column \n");
			} else {
				System.out.println("Daat update Failed ");
			}
			flag++;

		}

		boolean rtr = true;

		if (op.equals("")) {
			System.out.println("Run op is null");
			rtr = false;

		} else if (opo == 0) {
			opo++;
			rtr = false;

		} else {
			System.out.print("Not Upload");

		}
	}

	// }

	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);

		printLines(command + " stdout:", pro.getInputStream());
		System.out.println("cmp done ");

		printLines1(command + " stderr:", pro.getErrorStream());

		// System.out.println("err done");

		pro.waitFor();
		String aa = pro.getInputStream().toString();

	}

	public static void main(String[] args) {

		try {
			runProcess("javac Main.java");
			runProcess("java Main");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
