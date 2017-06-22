package com.rhb.tcard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static boolean isExists(String path) {
		File file = new File(path);
		return file.exists();
	}

	public static void writeTextFile(String path, String content, boolean append) {
		try {
			File file = new File(path);

			if (file.exists() == false) { // ����ı��ļ��������򴴽���
				file.createNewFile();
				file = new File(path); // ����ʵ����
				FileWriter filewriter = new FileWriter(file, append);
				BufferedWriter bufwriter = new BufferedWriter(filewriter);
				filewriter.write(content);
				filewriter.flush();
			} else {
				System.out.println(path + " has already exist!");
			}

		} catch (Exception d) {
			d.printStackTrace();
		}
	}

	public static String readTextFile(String path) {
		StringBuffer buffer = new StringBuffer();
		try{
			InputStream is = new FileInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = reader.readLine(); // ��ȡ��һ��
			while (line != null) { // ��� line Ϊ��˵��������
				buffer.append(line); // ��������������ӵ� buffer ��
				// buffer.append("\n"); // ��ӻ��з�
				line = reader.readLine(); // ��ȡ��һ��
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static List<File> getFiles(String path, String suffix,
			boolean isdepth) {
		List<File> files = new ArrayList();
		getListFiles(files, path, suffix, isdepth);
		return files;
	}

	private static void getListFiles(List list, String path, String suffix,
			boolean isdepth) {
		File dir = new File(path);
		listFile(list, dir, suffix, isdepth);
	}

	private static void listFile(List list, File dir, String suffix,
			boolean isdepth) {

		// ��Ŀ¼��ͬʱ��Ҫ������Ŀ¼
		if (dir.isDirectory() && isdepth == true) {
			File[] t = dir.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(list, t[i], suffix, isdepth);
			}
		} else {
			String filePath = dir.getAbsolutePath();
			if (suffix != null) {
				int begIndex = filePath.lastIndexOf(".");// ���һ��.(����׺��ǰ���.)������
				String tempsuffix = "";

				if (begIndex != -1) {// ��ֹ���ļ���ȴû�к�׺���������ļ�
					tempsuffix = filePath.substring(begIndex + 1,
							filePath.length());
				}

				if (tempsuffix.equals(suffix)) {
					list.add(filePath);
				}
			} else {
				// ��׺��Ϊnull��Ϊ�����ļ�
				list.add(filePath);
			}
		}
	}

}
