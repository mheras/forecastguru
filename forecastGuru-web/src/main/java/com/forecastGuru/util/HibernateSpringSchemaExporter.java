package com.forecastGuru.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * DDL Exporter utility for Spring/Hibernate, which supports Spring's
 * {@link AnnotationSessionFactoryBean}. It is made to get the same
 * functionality as Maven by using the <code>hibernate3</code> plugin (which has
 * no support for {@link AnnotationSessionFactoryBean}). This is why this
 * utility should be called from Maven using the <code>exec</code> plugin.
 * 
 * @author Martin A. Heras
 * 
 */
public class HibernateSpringSchemaExporter {

	private Log log = LogFactory.getLog(getClass());

	/**
	 * Generates the DDL file by giving the Spring application context filename
	 * where the session factory bean (which has to implement
	 * {@link AnnotationSessionFactoryBean} is declared, and the output
	 * filename.
	 * 
	 * @param args
	 *            the application context file and the output filename and the
	 *            output filename (in that order).
	 */
	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			throw new IllegalArgumentException("Both Spring application context and DDL output file are needed.");
		}

		HibernateSpringSchemaExporter schemaExporter = new HibernateSpringSchemaExporter();
		schemaExporter.exportDDL(args[0], args[1]);
	}

	/**
	 * Generates the DDL file by giving the Spring application context filename
	 * where the session factory bean (which has to implement
	 * {@link AnnotationSessionFactoryBean} is declared, and the output
	 * filename.
	 * 
	 * @param contextFile
	 *            the application context filename.
	 * @param outputFile
	 *            the output filename.
	 * @throws IOException
	 *             if an IO error occurs.
	 */
	public void exportDDL(String contextFile, String outputFile) throws IOException {

		this.log.info("Generating DDL in " + outputFile.replace("/", File.separator));

		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(contextFile);
		AnnotationSessionFactoryBean sessionFactory = (AnnotationSessionFactoryBean) context.getBean("&sessionFactory");
		SchemaExport exporter = new SchemaExport(sessionFactory.getConfiguration());

		// SchemaExport needs the file to exist in the filesystem.
		File f = new File(outputFile);
		if (!f.exists()) {
			f.createNewFile();
		}
		exporter.setOutputFile(outputFile);
		exporter.setDelimiter(";");
		exporter.setFormat(true);
		exporter.create(false, false);
	}
}
