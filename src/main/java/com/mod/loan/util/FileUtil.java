package com.mod.loan.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 文件操作工具类
 * 
 * @author wugy 2016年7月11日下午5:00:09
 * 
 */
public class FileUtil {

	private FileUtil(){
		throw new Error("can't instance this tool class");
	}
	
	private static FileUtil me = null;

	public static FileUtil getInstance() {
		if (me == null) {
			me = new FileUtil();
		}
		return me;
	}

	public static void uploadPIC(File srcFile, String path, String fileName) {
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream(new File(path + "/"
					+ fileName));
			bos = new BufferedOutputStream(fos);
			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
	}

	public static boolean copyFile(File srcFile, File targetFile) {
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		boolean isCopied = false;
		if (srcFile.exists())
			try {
				bin = new BufferedInputStream(new FileInputStream(srcFile));
				byte[] bf = new byte[1048576];
				bout = new BufferedOutputStream(
						new FileOutputStream(targetFile));
				int len = -1;
				while ((len = bin.read(bf)) != -1) {
					bout.write(bf, 0, len);
				}
				isCopied = true;
			} catch (FileNotFoundException fne) {
				System.out
						.println("原文件[" + srcFile.getName() + "]找不到。。。无法复制文件");
				fne.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("原文件拷贝出错了...");
				ioe.printStackTrace();
			} finally {
				try {
					if (bin != null)
						bin.close();
					if (bout != null)
						bout.close();
				} catch (IOException localIOException3) {
				}
			}
		return isCopied;
	}

	public static void createFile(InputStream in, OutputStream out)
			throws IOException {
		try {
			byte[] bf = new byte[1048576];
			int len = -1;
			while ((len = in.read(bf)) != -1)
				out.write(bf, 0, len);
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw e;
			}
		}
	}

	public static String getFileSizeStr(long fileSize) {
		NumberFormat formater = DecimalFormat.getInstance();
		formater.setMaximumFractionDigits(2);
		formater.setMinimumFractionDigits(2);
		String fileSizeStr;
		if (fileSize < 1048576L)
			fileSizeStr = String.valueOf(formater.format(fileSize / 1024L))
					+ "KB";
		else {
			fileSizeStr = String.valueOf(formater.format(fileSize / 1048576L))
					+ "MB";
		}
		return fileSizeStr;
	}

	public static void createFile(String filePath, String content,
			String charsetName) throws UnsupportedEncodingException,
			IOException {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(filePath),
					charsetName);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathLsatIndex(String fileName) {
		int point = fileName.lastIndexOf('/');
		if (point == -1) {
			point = fileName.lastIndexOf('\\');
		}
		return point;
	}

	/**
	 * 得到路径分隔符在文件路径中指定位置前最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @param fromIndex
	 *            开始查找的位置
	 * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathLsatIndex(String fileName, int fromIndex) {
		int point = fileName.lastIndexOf('/', fromIndex);
		if (point == -1) {
			point = fileName.lastIndexOf('\\', fromIndex);
		}
		return point;
	}

	/**
	 * 得到文件的名字部分。 实际上就是路径中的最后一个路径分隔符后的部分。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的名字部分
	 * @since 0.5
	 */
	public static String getNamePart(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return fileName;
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				if (length == 1) {
					return fileName;
				} else {
					return fileName.substring(0, point);
				}
			} else {
				return fileName.substring(secondPoint + 1, point);
			}
		} else {
			return fileName.substring(point + 1);
		}
	}

	/**
	 * 得到文件的类型。 实际上就是得到文件名中最后一个“.”后面的部分。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的类型部分
	 * @since 0.5
	 */
	public static String getTypePart(String fileName) {
		int point = fileName.lastIndexOf('.');
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			return "";
		} else {
			return fileName.substring(point + 1, length);
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @return
	 */
	public static boolean delFile(String path) {
		return (new File(path)).delete();
	}

	public static String readFile(File file, boolean lineNumber) {

		StringBuffer allrecord = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "gbk");
			char[] chars = new char[256];
			int i = isr.read(chars);
			while (i != -1) {
				allrecord.append(new String(chars, 0, i));
				i = isr.read(chars);
			}
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allrecord.toString();
	}

	/**
	 * 读取文件生成字符串，lineNuber为真，每行添加<BR>
	 * 
	 * @param filename
	 * @param lineNumber
	 * @return
	 */
	public static String readFile(String filename, boolean lineNumber) {
		return readFile(new File(filename), lineNumber);
	}

	// 分析文件目录,以及目录中的文件
	@SuppressWarnings("rawtypes")
	public Map<String, Set> directionFatherMap = new HashMap<String, Set>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Set> searchFile(String filename) {
		Set<String> directionSet = null;
		try {
			File file = new File(filename);
			File result[] = file.listFiles();
			for (int i = 0; i < result.length; i++) {
				if (!result[i].isFile()) {
					searchFile(result[i].toString());// 循环判断文件目录中的文件
				} else {
					String str = result[i].toString();
					// 为了兼容linux,xp(因为“/”这种情况时而可以，时而不行)
					int keyPostion = str.lastIndexOf("/");
					if (keyPostion < 0) {
						keyPostion = str.lastIndexOf("\\");
					}
					String keyDirection = (result[i].toString()).substring(0,
							keyPostion);
					if (directionFatherMap.containsKey(keyDirection)) {// 如果不是首次加载
						directionFatherMap.get(keyDirection).add(result[i]);
					} else {
						directionSet = new HashSet<String>();
						directionSet.add(result[i].toString());
						directionFatherMap.put(keyDirection, directionSet);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ea) {
			System.out.println("Usage: ListFiles <source dir> <target file>");
		}

		return directionFatherMap;
	}

	// 判断文件大小(单位是字节)
	public static Integer getFileByte(String filePath) {
		Integer fileSize = null;
		File f = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			fileSize = fis.available();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileSize;
	}

	/**
	 * 把原文件中的内容输入到新的文件中去
	 * 
	 * @param
	 */
	public void bufferToNewFile(String oldPath, String newPath) {
		long time = System.currentTimeMillis();

		FileInputStream fis;
		FileOutputStream fos;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		int i;
		try {
			fis = new FileInputStream(oldPath); // 文件输入流
			bis = new BufferedInputStream(fis); // 连接带缓冲的输入流
			fos = new FileOutputStream(newPath); // 文件输出流
			bos = new BufferedOutputStream(fos); // 连接带缓冲的输出流
			byte[] tempbytes = new byte[10240];
			i = bis.read(tempbytes); // 读数据
			while (i != -1) {
				bos.write(tempbytes, 0, i); // 写数据
				bos.flush(); // 强制输出
				i = bis.read(tempbytes);
			}
			fis.close();
			fos.close();
			bis.close();
			bos.close();
		} catch (IOException e) {
			System.out.println("do not find the file");
		}
		System.out.println("写入数据的时间：" + (System.currentTimeMillis() - time));
	}

	public static Map<String, String> parseXml(HttpServletRequest request) {
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());
			// 释放资源
			inputStream.close();
			inputStream = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}
	
	public static String parseMap2XmlString(Map<String, String> map){
		StringBuffer sb=new StringBuffer("<xml>");
		for (String key : map.keySet()) {
			sb.append("<"+key+"><![CDATA[");
			sb.append(map.get(key));
			sb.append("]]></"+key+">");
		}
		sb.append("</xml>");
		return sb.toString();	
	}
	
	public static Map<String, String> parseStr2Xml(String xmlStr) {
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xmlStr);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList){
				map.put(e.getName(), e.getText());
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}
}