/**
 * 
 * 
 * Classe Principal do sistema
 * 
 * 
 */
package com.geradorcarga.app;

import java.util.logging.Logger;

import com.geradorcarga.app.config.Config;
import com.geradorcarga.app.readfile.ReadFile;

/**
 * @author Tarcisio Machado dos Reis
 *
 */
public class GeradorCarga {

	private static Config config;
	private static ReadFile readFile;
	private static Logger log = Logger.getLogger(GeradorCarga.class.getName());
	
	public static void main(String[] args) throws Exception {

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String[] strSeparators = new String[6];
				
				strSeparators[0] = "[";
				strSeparators[1] = "-";
				strSeparators[2] = ",";
				strSeparators[3] = ".";
				strSeparators[4] = "]";
				strSeparators[5] = " ";
				
				config = new Config();

				config.setOs("OS");
				config.setHomeDrive("HOMEDRIVE");
				config.setHomePath("HOMEPATH");
				config.setDataIn("\\data\\in\\");
				config.setDataOut("\\data\\out\\");
			    config.setExtension("dat");
			    config.setSeparatorMain("ç");
			    config.setSeparators(strSeparators);	    
			    
				readFile = new ReadFile(config);
				
				try {
					if (readFile.openFileInput()) {
						readFile.readFileInput();
					}
					log.info("Executando...");
					Thread.sleep(5000);
					log.info("Aguardando próximo arquivo...");
					run();
				} catch(Exception e) {
					log.info(e.getMessage());
				}
			}
		}).start();
		
	}

}
