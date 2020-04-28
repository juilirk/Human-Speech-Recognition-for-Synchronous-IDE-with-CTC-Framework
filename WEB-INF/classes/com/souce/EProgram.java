package com.souce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DbConnection;

public class EProgram 
{

	static String stud_id_global = "";
	static String e_program = "";
	static int flag = 0;
	static int flag_c = 0;
	

	static String as_id_global = "";

	public static boolean compileEditProgram(String filename, String program, String stud_id, String nas_id) 
	{
		try 
		{
			stud_id_global = stud_id;
			e_program=program;
			as_id_global = nas_id;

			runProcess("javac " + "F:/"+filename + ".java");
			
			return true;
		}
		catch (Exception e)
		{
	
			System.out.println("Exception Occure Main " + e);
			return false;
		}
	}

	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);

		printLines(command + " stdout:", pro.getInputStream());
		System.out.println("cmp done ");

		printLines(command + " stderr:", pro.getErrorStream());


		pro.waitFor();

	}

	private static void printLines(String name, InputStream ins) throws Exception 
	{
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
		
		System.out.println("1 "+op);
		System.out.println("2 "+stud_id_global);
		System.out.println("3 "+as_id_global);
		
		ps_r = con.prepareStatement("update `compiler`.`solve_assignment` SET output=?,program=? where stud_id=? AND stud_as_id=?");
		
		ps_r.setString(1, op);
		ps_r.setString(2, e_program);
		ps_r.setString(3, stud_id_global);
		ps_r.setString(4, as_id_global);

		int r = ps_r.executeUpdate();

		if (r > 0) 
		{

			System.out.println("Compile Time Output Data update Succesfully ");
		}
		else 
		{
			System.out.println("Daat update Failed ");
		}

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
