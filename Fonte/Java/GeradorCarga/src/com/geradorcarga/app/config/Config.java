/**
 *
 *
 * Classe para receber configurações do sistema
 *
 * 
 */
package com.geradorcarga.app.config;

/**
 * @author Tarcisio Machado dos Reis
 *
 */
public class Config {

	private String os;
	private String homedrive;
	private String homepath;
	private String dataIn;
	private String dataOut;
	private String extension;
	private String separatormain;
	private String[] separators;
	
	public Config() {}

	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = System.getenv(os);
	}
	
	public String getHomeDrive() {
		return homedrive;
	}
	public void setHomeDrive(String homedrive) {
		this.homedrive = System.getenv(homedrive);
	}

	public String getHomePath() {
		return homepath;
	}
	public void setHomePath(String homepath) {
		this.homepath = System.getenv(homepath);
	}

	public String getDataIn() {
		return dataIn;
	}
	public void setDataIn(String dataIn) {
		this.dataIn = dataIn;
	}

	public String getDataOut() {
		return dataOut;
	}
	public void setDataOut(String dataOut) {
		this.dataOut = dataOut;
	}

	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getSeparatorMain() {
		return separatormain;
	}
	public void setSeparatorMain(String separatormain) {
		this.separatormain = separatormain;
	}

	public String[] getSeparators() {
		return separators;
	}
	public void setSeparators(String[] separators) {
		this.separators = separators;
	}
	
}
