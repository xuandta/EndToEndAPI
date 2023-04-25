package com.api.auto.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	
	private static String CONFIG_PATH = "./configuration/configs.properties";
	private static String VARIABLE_PATH = "./configuration/variable.properties";

	public static String getProperty (String key) {
		Properties properties = new Properties();
		String value;
		FileInputStream fileInputStream  = null;
		try {
        	fileInputStream = new FileInputStream(CONFIG_PATH);
            properties.load(fileInputStream);
            value = properties.getProperty(key);
            return value;
        } catch (Exception ex) {
            System.out.println("Xảy ra lỗi khi đọc giá trị của  " + key);
            ex.printStackTrace();
        } finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
		return key;
	}
	
	
	
//	public static void saveVariable(String Variable, String Value) {
//    	Properties properties= new Properties();
//    	FileOutputStream fileOutputStream = null;
//    	try {
//    		fileOutputStream = new FileOutputStream(VARIABLE_PATH);
//            properties.setProperty(Variable, Value);
//            properties.store(fileOutputStream, "Set new value in properties");
//    	} catch (IOException ex) {
//            System.out.println("Xảy ra lỗi khi save giá trị của Variable ");
//   	     	ex.printStackTrace();
//    	} finally {
//   	     	if (fileOutputStream != null) {
//   	     		try {
//   	     			fileOutputStream.close();
//   	     		} catch (IOException e) {
//   	     			e.printStackTrace();
//   	     		}
//   	     	}
//    	}
//		
//	}
//	
//	
//	
//	public static String getVariable(String Variable) {
//		Properties properties = new Properties();
//		String value = null;
//		FileInputStream fileInputStream  = null;
//		try {
//        	fileInputStream = new FileInputStream(VARIABLE_PATH);
//            properties.load(fileInputStream);
//            value = properties.getProperty(Variable);
//            return value;
//        } catch (Exception ex) {
//            System.out.println("Xảy ra lỗi khi đọc giá trị của Variable ");
//            ex.printStackTrace();
//        } finally {
//			if (fileInputStream != null) {
//				try {
//					fileInputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//        }
//		return value;
//	}
// -----------------------------------------------
	public static void saveVariable(String Variable, String Value) {
    	Properties properties= new Properties();
    	FileOutputStream fileOutputStream = null;
    	try {
    		fileOutputStream = new FileOutputStream("./Variable/"+Variable+".properties");
            properties.setProperty(Variable, Value);
            properties.store(fileOutputStream, "Set new value in properties");
    	} catch (IOException ex) {
            System.out.println("Xảy ra lỗi khi save giá trị của Variable ");
   	     	ex.printStackTrace();
    	} finally {
   	     	if (fileOutputStream != null) {
   	     		try {
   	     			fileOutputStream.close();
   	     		} catch (IOException e) {
   	     			e.printStackTrace();
   	     		}
   	     	}
    	}
		
	}
	
	
	public static String getVariable(String Variable) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileInputStream  = null;
		try {
        	fileInputStream = new FileInputStream("./Variable/"+Variable+".properties");
            properties.load(fileInputStream);
            value = properties.getProperty(Variable);
            return value;
        } catch (Exception ex) {
            System.out.println("Xảy ra lỗi khi đọc giá trị của Variable ");
            ex.printStackTrace();
        } finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
		return value;
	}
	
	
	
}
