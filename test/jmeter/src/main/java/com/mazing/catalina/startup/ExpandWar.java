package com.mazing.catalina.startup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

import org.apache.catalina.Host;
import org.apache.catalina.startup.Constants;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class ExpandWar extends org.apache.catalina.startup.ExpandWar {

	private static final Log log = LogFactory.getLog(ExpandWar.class);

	// 参考org.apache.catalina.startup.ExpandWar.expand(Host, URL, String)方法
	// 去掉了第三个参数String
	// 总共修改了3个地方
	public static String expand(Host host, URL war) throws IOException {

		/*
		 * Obtaining the last modified time opens an InputStream and there is no explicit close method. We have to
		 * obtain and then close the InputStream to avoid a file leak and the associated locked file.
		 */
		JarURLConnection juc = (JarURLConnection) war.openConnection();
		juc.setUseCaches(false);
		URL jarFileUrl = juc.getJarFileURL();
		URLConnection jfuc = jarFileUrl.openConnection();

		boolean success = false;
		// 1: File docBase = new File(host.getAppBaseFile(), pathname);
		File docBase = host.getAppBaseFile();
		// 2: File warTracker = new File(host.getAppBaseFile(), pathname + Constants.WarTracker);
		File warTracker = new File(host.getAppBaseFile(), Constants.WarTracker);
		long warLastModified = -1;

		try (InputStream is = jfuc.getInputStream()) {
			// Get the last modified time for the WAR
			warLastModified = jfuc.getLastModified();
		}

		// Check to see of the WAR has been expanded previously
		if (docBase.exists()) {
			// A WAR was expanded. Tomcat will have set the last modified
			// time of warTracker file to the last modified time of the WAR so
			// changes to the WAR while Tomcat is stopped can be detected
			// 3: if (!warTracker.exists() || warTracker.lastModified() == warLastModified) {
			if (warTracker.exists() && warTracker.lastModified() == warLastModified) {
				log.info(">>>>>>>> Expand War: Exist and are not modified");
				// No (detectable) changes to the WAR
				success = true;
				return (docBase.getAbsolutePath());
			}

			// WAR must have been modified. Remove expanded directory.
			log.info(sm.getString("expandWar.deleteOld", docBase));
			if (!delete(docBase)) {
				throw new IOException(sm.getString("expandWar.deleteFailed", docBase));
			}
		}

		// Create the new document base directory
		if (!docBase.mkdir() && !docBase.isDirectory()) {
			throw new IOException(sm.getString("expandWar.createFailed", docBase));
		}

		// Expand the WAR into the new document base directory
		String canonicalDocBasePrefix = docBase.getCanonicalPath();
		if (!canonicalDocBasePrefix.endsWith(File.separator)) {
			canonicalDocBasePrefix += File.separator;
		}

		// Creating war tracker parent (normally META-INF)
		File warTrackerParent = warTracker.getParentFile();
		if (!warTrackerParent.isDirectory() && !warTrackerParent.mkdirs()) {
			throw new IOException(sm.getString("expandWar.createFailed", warTrackerParent.getAbsolutePath()));
		}

		log.info(">>>>>>>> Expand War: " + war);
		try (JarFile jarFile = juc.getJarFile()) {

			Enumeration<JarEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();
				String name = jarEntry.getName();
				File expandedFile = new File(docBase, name);
				if (!expandedFile.getCanonicalPath().startsWith(canonicalDocBasePrefix)) {
					// Trying to expand outside the docBase
					// Throw an exception to stop the deployment
					throw new IllegalArgumentException(
							sm.getString("expandWar.illegalPath", war, name, expandedFile.getCanonicalPath(), canonicalDocBasePrefix));
				}
				int last = name.lastIndexOf('/');
				if (last >= 0) {
					File parent = new File(docBase, name.substring(0, last));
					if (!parent.mkdirs() && !parent.isDirectory()) {
						throw new IOException(sm.getString("expandWar.createFailed", parent));
					}
				}
				if (name.endsWith("/")) {
					continue;
				}

				try (InputStream input = jarFile.getInputStream(jarEntry)) {
					if (null == input)
						throw new ZipException(sm.getString("expandWar.missingJarEntry", jarEntry.getName()));

					// Bugzilla 33636
					expand(input, expandedFile);
					long lastModified = jarEntry.getTime();
					if ((lastModified != -1) && (lastModified != 0)) {
						expandedFile.setLastModified(lastModified);
					}
				}

				// Create the warTracker file and align the last modified time
				// with the last modified time of the WAR
				warTracker.createNewFile();
				warTracker.setLastModified(warLastModified);
			}
			success = true;
		} catch (IOException e) {
			throw e;
		} finally {
			if (!success) {
				// If something went wrong, delete expanded dir to keep things
				// clean
				deleteDir(docBase);
			}
		}

		// Return the absolute path to our new document base directory
		return docBase.getAbsolutePath();
	}

	// copy to this

	/**
	 * Expand the specified input stream into the specified file.
	 *
	 * @param input InputStream to be copied
	 * @param file The file to be created
	 *
	 * @exception IOException if an input/output error occurs
	 */
	private static void expand(InputStream input, File file) throws IOException {
		try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file))) {
			byte buffer[] = new byte[2048];
			while (true) {
				int n = input.read(buffer);
				if (n <= 0)
					break;
				output.write(buffer, 0, n);
			}
		}
	}

}
