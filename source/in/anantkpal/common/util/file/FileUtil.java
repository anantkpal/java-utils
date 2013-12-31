/*
 * File Common Util
 * Author: Anant Pal -  anantkpal@yahoo.co.in
 * Copyright (c) 2013 Anant Pal(anantkpal@yahoo.co.in).  All rights reserved.
 * The copyrights embodied in the content of this file are licensed
 * by Anant Pal(anantkpal@yahoo.co.in) under the BSD  open source license.
 */

package in.anantkpal.common.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author Anant Pal(anantkpal)
 *
 */
public class FileUtil {

	
	/**
	 * 
	 * @param rootFolder - File object which should be folder
	 * @param recursive - boolean true will return recursive data false file return only corresponding folder file details
	 * @return
	 * 
	 * Will return list of files in the folder as well as sub folders recursively
	 */
	public static List<File> getFileList(File rootFolder, boolean recursive) {
		return getFileList(rootFolder, "(.*)", recursive);
	}

	/**
	 * 
	 * @param rootFolder - String, complete path to the folder
	 * @param recursive - boolean true will return recursive data false file return only corresponding folder file details
	 * @return
	 * 
	 * Will return list of files in the folder as well as sub folders recursively
	 */
	public static List<File> getFileList(String rootFolder, boolean recursive) {
		return getFileList(new File(rootFolder), "(.*)", recursive);
	}

	
	/**
	 * 
	 * @param rootFolder -String, complete path to the folder
	 * @param regex - regular expression for selection file
	 * @param recursive  boolean true will return recursive data false file return only corresponding folder file details
	 * @return
	 * 
	 * Will return list of files in the folder as well as sub folders recursively
	 */
	public static List<File> getFileList(String rootFolder, String regex,boolean recursive) {
		return getFileList(new File(rootFolder), "(.*)", recursive);
	}

	

	/**
	 * 
	 * @param rootFolder -File object which should be folder
	 * @param regex - regular expression for selection file
	 * @param recursive  boolean true will return recursive data false file return only corresponding folder file details
	 * @return
	 * 
	 * Will return list of files in the folder as well as sub folders recursively
	 */
	public static List<File> getFileList(File rootFolder, String regex,	boolean recursive) {
		Pattern pattern = Pattern.compile(regex);
		return getFiles(rootFolder, pattern, recursive);
	}
	
	/**
	 * 
	 * @param rootFolder -File object which should be folder
	 * @param regexPattern - Pattern object 
	 * @param recursive - boolean true will return recursive data false file return only corresponding folder file details
	 * @return
	 * 
	 * Will return list of files in the folder as well as sub folders recursively
	 */
	
	private static List<File> getFiles(File rootFolder, Pattern regexPattern,boolean recursive) {
		List<File> filez = new ArrayList<File>();
		File[] files = rootFolder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory() && recursive) {
					filez.addAll(getFiles(file, regexPattern, recursive));
				} else {
					if (regexPattern.matcher(file.getAbsolutePath()).matches()) {
						filez.add(file);
					}
				}
			}
		}
		return filez;
	}

	/**
	 * 
	 * @param filepath - File path as string
	 * 
	 * if path is a file than its parent folder will be created or else folder is created
	 */
	public static void createDir(String filepath){
		createDir(new File(filepath));
	}
	
	/**
	 * 
	 * @param file - File object
	 * 
	 * if File object is a file than its parent folder will be created or else folder is created
	 */
	public static void createDir(File file){
		File directotyToCreate = file.isDirectory() ? file : file.getParentFile();
		if (!directotyToCreate.exists()) 
		   directotyToCreate.mkdir();  

	}
	




}
