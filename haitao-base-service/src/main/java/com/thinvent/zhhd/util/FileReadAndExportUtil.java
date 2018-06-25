package com.thinvent.zhhd.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileReadAndExportUtil {  
  
    public static String fileReadByType(String reportName, String type) {
        // 构建指定文件
    	String tmpUrl = System.getenv("TMP");
    	FileInputStream inputStream = null;
    	Scanner sc = null;
    	StringBuilder htmlData = new StringBuilder();
    	try {
    		String htmlUrl = tmpUrl + "/view/" + reportName + type;
    	    inputStream = new FileInputStream(htmlUrl);
    	    sc = new Scanner(inputStream, "UTF-8");
    	    while (sc.hasNextLine()) {
    	        String line = sc.nextLine();
    	         if(line != null){
    	        	 htmlData.append(line);
    	         }
    	    }
    	    if (sc.ioException() != null) {
    	        throw sc.ioException();
    	    }
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
    	    if (inputStream != null) {
    	        try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    	    }
    	    if (sc != null) {
    	        sc.close();
    	    }
    	}
    	String reportData = htmlData.toString();
		return reportData;
    }
    
    public static boolean fileUpload(String host, int port, String username, String password,
            String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = "/";
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            break;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }
    
	@SuppressWarnings("unused")
	public static String fileExport(Map<String, String> params) throws IOException {
		String tmpUrl = System.getenv("TMP");
		String filePath;
		if(isWin()){
			filePath = tmpUrl + "\\view\\" + params.get("reportName");
		}else {
			filePath = tmpUrl + "/view/" + params.get("reportName");
		}
        
        File file;
        if(params.get("exportFormat").equals("Word")){
        	file = new File(filePath + ".doc");
		}else if(params.get("exportFormat").equals("Excel")){
			file = new File(filePath + ".xls");
		}else{
			file = new File(filePath + ".pdf");
		}
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();  
        int readBytes = -1;
        while((readBytes = fis.read(buffer, 0, (int) file.length())) != -1){
        	outputstream.write(buffer, 0, (int) file.length());
        }
        byte[] byteData = outputstream.toByteArray(); 
        String output = new String(byteData,"ISO-8859-1");
        fis.close();
        return output;
	}
    
    @SuppressWarnings("resource")
	public static boolean jarExtract(String jarPath) {
    	String tmpUrl = System.getenv("TMP");
    	JarFile jarFile;
    	JarInputStream jar;
    	try {
    		jarFile = new JarFile(jarPath);
			jar = new JarInputStream(new FileInputStream(jarPath));
			// 实例化对象，指明要进行解压的文件
			JarEntry entry = (JarEntry) jar.getNextEntry();
			while (((entry = (JarEntry) jar.getNextEntry()) != null)) {
				// 如果entry不为空，并不在同一目录下
				File file = new File(tmpUrl + "/" + entry.getName()); // 获取文件目录
				String name = entry.toString();
				if(name.length() > 23){
					name = name.substring(0, 23);
				}
				if (!file.exists() && name.equals("BOOT-INF/classes/report")) { // 如果该文件不存在
					if(entry.isDirectory()){
						file.mkdirs();// 创建文件所在文件夹
					}
					if(!entry.isDirectory()){
						file.createNewFile(); // 创建文件
						OutputStream os = new FileOutputStream(file);
						//通过JipFile的getInputStream方法拿到具体的JipEntry的输入流
						InputStream is = jarFile.getInputStream(entry);
						int len = 0;
						while ((len = is.read()) != -1)
						os.write(len);
						os.close();
						is.close();
					}
				}else if(file.exists() && name.equals("BOOT-INF/classes/report")){
					file.delete();
					if(!entry.isDirectory()){
						file.createNewFile(); // 创建文件
						OutputStream os = new FileOutputStream(file);
						//通过JipFile的getInputStream方法拿到具体的JipEntry的输入流
						InputStream is = jarFile.getInputStream(entry);
						int len = 0;
						while ((len = is.read()) != -1)
						os.write(len);
						os.close();
						is.close();
					}
				}
				jar.closeEntry(); // 关闭当前entry
			}
			jar.close(); // 关闭流
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
    
    public static String getJarPath() {
    	URL url = FileReadAndExportUtil.class.getProtectionDomain().getCodeSource().getLocation();  
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        int index = filePath.indexOf("jar");
        if(index > 0) {
        	filePath = filePath.substring(0,index+3);
        }
        filePath = getPath(filePath);
        File file = new File(filePath);  
          
        filePath = file.getAbsolutePath();//得到正确路径  
        return filePath;  
	}
    
    private static String getPath(String str) {
		int index = str.indexOf("/home");
		if (index > 0) {
			str = str.substring(index, str.length());
		}
		return str;
	}
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	
	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 */
	@SuppressWarnings("unchecked")
	public static void findFiles(String baseDirName, String targetFileName, List fileList) {

		File baseDir = new File(baseDirName); // 创建一个File对象
		if (!baseDir.exists() || !baseDir.isDirectory()) { // 判断目录是否存在
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
			return;
		}
		String tempName = null;
		// 判断目录是否存在
		File tempFile;
		File[] files = baseDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isDirectory()) {
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			} else if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (wildcardMatch(targetFileName, tempName)) {
					// 匹配成功，将文件名添加到结果集
					fileList.add(tempFile.getAbsoluteFile());
				}
			}
		}
	}

	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}

    public static boolean isWin() {
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){
			return true;
		}
		return false;
	}
}  