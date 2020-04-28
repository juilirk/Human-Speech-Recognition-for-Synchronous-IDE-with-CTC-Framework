package com.souce;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DbConnection;

public class GlobalFunction 
{
	
	
	public static boolean storedFile(String fname, String program) {

		try {

			
			File file = new File("C:/"
							+ fname + ".c");

			// if file doesnt exists, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(program);
			bw.close();

			// System.out.println(" file write Done");
			return true;
		} catch (IOException e) 
		{
			System.out.println("Exception" + e);
			e.printStackTrace();
			return false;
		}

	}
	
	
	public static boolean storedFileCpp(String fname, String program) {

		try {

			
			File file = new File("C:/"
							+ fname + ".cpp");

			// if file doesnt exists, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(program);
			bw.close();

			// System.out.println(" file write Done");
			return true;
		} catch (IOException e) 
		{
			System.out.println("Exception" + e);
			e.printStackTrace();
			return false;
		}

	}


	public String getFname(String std_no) 
	{
		String fname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from student where student_no='"+ std_no + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				fname = rs.getString("fname");

			}
			return fname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return fname;

	}

	public String getLname(String std_no) {
		String lname = "";
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from student where student_no='"+ std_no + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				lname = rs.getString("lname");

			}
			return lname;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception " + e);

		}
		return lname;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
