package io.axual.madurobank;

import io.axual.madurobank.parser.CustomerStatementProcessor;
import io.axual.madurobank.parser.CustomerStatementReport;
import io.axual.madurobank.parser.StatementProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MadurobankApplication {
	public static final Logger logger = LoggerFactory.getLogger(MadurobankApplication.class);

	public static void main(String[] args) {
		if (args.length == 0) {
			logger.info("\r\nusage:\r\n  java -jar madurobank.jar fileA fileB fileC\r\n");
		}
		for(final String fileName : args ) {
			if (fileName.endsWith(".xml") || fileName.endsWith(".csv")) {
				logger.info("processing file:: {}", fileName);
				String extension = fileName.substring(fileName.lastIndexOf('.')+1);
				CustomerStatementProcessor csp = StatementProcessorFactory.getCustomerStatementProcessor(extension);
				csp.setFileName(fileName);
				CustomerStatementReport report = csp.process();
				logger.info(report.getStatusReport());
			} else {
				logger.error("Unable to process input");
			}
		}
	}
}
