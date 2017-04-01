//: DirList.java
// Displays directory listing
package com.inms.action.thinking_in_java;

import java.io.File;
import java.io.FilenameFilter;

import com.opensymphony.xwork2.ActionSupport;

public class DirList extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
//			String fileurl = ServletActionContext.getServletContext().getRealPath("/");
			File path = new File("/");
//			String[] list;
//			if (args.length == 0)
//				list = path.list();
//			else
//				list = path.list(new DirFilter(args[0]));
//
//			for (int i = 0; i < list.length; i++)
//				System.out.println(list[i]);
			System.out.println(path.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class DirFilter implements FilenameFilter {
	String afn;

	DirFilter(String afn) {
		this.afn = afn;
	}

	public boolean accept(File dir, String name) {
		// Strip path information:
		String f = new File(name).getName();
		return f.indexOf(afn) != -1;
	}
} // /:~