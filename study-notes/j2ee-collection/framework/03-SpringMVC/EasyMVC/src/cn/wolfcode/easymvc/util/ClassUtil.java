package cn.wolfcode.easymvc.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

	// 获取指定包(包括子包)中指定注解的所有类并返回
	public static List<Class<?>> getClassListByAnnotation(String packageName,
			Class<? extends Annotation> annotationClass) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		try {
			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
					.getResources(packageName.replaceAll("\\.", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals("file")) {
						String packagePath = url.getPath();
						addClassByAnnotation(classList, packagePath, packageName, annotationClass);
					} else if (protocol.equals("jar")) {
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						JarFile jarFile = jarURLConnection.getJarFile();
						Enumeration<JarEntry> jarEntries = jarFile.entries();
						while (jarEntries.hasMoreElements()) {
							JarEntry jarEntry = jarEntries.nextElement();
							String jarEntryName = jarEntry.getName();
							if (jarEntryName.endsWith(".class")) {
								String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
										.replaceAll("/", ".");
								Class<?> cls = Class.forName(className);
								if (cls.isAnnotationPresent(annotationClass)) {
									classList.add(cls);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	private static void addClassByAnnotation(List<Class<?>> classList, String packagePath, String packageName,
			Class<? extends Annotation> annotationClass) {
		try {
			File[] files = getClassFiles(packagePath);
			if (files != null) {
				for (File file : files) {
					String fileName = file.getName();
					if (file.isFile()) {
						String className = getClassName(packageName, fileName);
						Class<?> cls = Class.forName(className);
						if (cls.isAnnotationPresent(annotationClass)) {
							classList.add(cls);
						}
					} else {
						String subPackagePath = getSubPackagePath(packagePath, fileName);
						String subPackageName = getSubPackageName(packageName, fileName);
						addClassByAnnotation(classList, subPackagePath, subPackageName, annotationClass);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static File[] getClassFiles(String packagePath) {
		return new File(packagePath).listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
	}

	private static String getClassName(String packageName, String fileName) {
		String className = fileName.substring(0, fileName.lastIndexOf("."));
		if (hasLength(packageName)) {
			className = packageName + "." + className;
		}
		return className;
	}

	private static String getSubPackagePath(String packagePath, String filePath) {
		String subPackagePath = filePath;
		if (hasLength(packagePath)) {
			subPackagePath = packagePath + "/" + subPackagePath;
		}
		return subPackagePath;
	}

	private static String getSubPackageName(String packageName, String filePath) {
		String subPackageName = filePath;
		if (hasLength(packageName)) {
			subPackageName = packageName + "." + subPackageName;
		}
		return subPackageName;
	}

	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean isEmpty(String str) {
		return !hasLength(str);
	}
}
