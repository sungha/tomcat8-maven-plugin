package kr.steelheart.maven.plugin.tomcat8;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;




@Mojo(name = "run")
public class RunMojo extends AbstractMojo {

	@Parameter(property="warSourceDirectory", defaultValue="webapp")
	private String warSourceDirectory;

	@Parameter(property="port", defaultValue="8080")
	private int port;

	@Parameter(property="path", defaultValue="/")
	private String path;


	public void execute() {
		try {
			Tomcat tomcat = new Tomcat();

			tomcat.setPort(port);
			tomcat.addWebapp(path, new File(warSourceDirectory).getAbsolutePath());

			tomcat.start();
			tomcat.getServer().await();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
