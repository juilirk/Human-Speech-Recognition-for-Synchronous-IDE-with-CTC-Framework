package com.souce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DbConnection;

public class NewProgramRun {

	static int flag;
	static String e_program = "";

	public NewProgramRun() {
		flag = 0;

	}

	static String stud_id_global = "";

	static String as_id_global = "";

	public static boolean runEditProgram(String filename, String program, String stud_id, String nas_id) {
		try {
			stud_id_global = stud_id;
			as_id_global = nas_id;
			e_program = program;

			System.out.println("in running" + filename + program);

			runProcess("java "+" -cp F:\\ "+filename);
			return true;
		} catch (Exception e) {
			System.out.println("Exception Occure Main " + e);
			return false;
		}
	}

	private static void runProcess(String command) throws Exception {

		System.out.println("Executing command : " + command);

		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
	}

	private static void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));

		String op = "";
		while ((line = in.readLine()) != null) {
			op = op + line + "  ";
			System.out.println(name + " " + line);
			System.out.println(" verify  " + line);
		}

		System.out.println("Run time Op is " + op);
		op = op.replace("'", "");
		op = op.replace(",", "");
		op = op.replace(";", "");
		System.out.println("flag is " + flag);
		// Done
		if (flag == 0) {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps_r;
			Statement st = con.createStatement();
			System.out.println("1 " + stud_id_global);
			System.out.println("2 " + as_id_global);

			ps_r = con.prepareStatement("update `speech`.`speechdata` SET output=? where Name =? ");
			ps_r.setString(1, op);
			ps_r.setString(2, stud_id_global);

			int r = ps_r.executeUpdate();

			if (r > 0) {
				System.out.println("Run Time Output Data update Succesfully ");
			} else {
				System.out.println("Daat update Failed ");
			}
			flag++;
		}
	}

	public static void main(String[] args) {

	}

}
