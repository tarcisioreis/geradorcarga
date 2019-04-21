package com.geradorcarga.app.validator;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.geradorcarga.app.config.Config;

public class Validator implements Serializable{

	private static final long serialVersionUID = 4168658640310696887L;
	
	private static Config config;
	
	public Validator() {}
	
	public Validator(Config config) {
		Validator.config = config;
	}

	public static boolean isFileNameIn(String fileNameIn) {
		if (fileNameIn.contains("proc_")) {
			return false;
		}
		return FilenameUtils.getExtension(fileNameIn).equals(config.getExtension());
	}

	public boolean isFileFormatValid(String line) {
		boolean retorno = false;
		int index = 4;
		
		if ((line.substring(0, 3).equals("001") ||
			 line.substring(0, 3).equals("002") ||
			 line.substring(0, 3).equals("003")) && 
			config.getSeparatorMain().equals(line.substring(3, 4))) {
			if (line.substring(0, 3).equals("001") ||
			    line.substring(0, 3).equals("002")) {
				if (line.substring(0, 3).equals("001")) {
					retorno = Valid001(index,line);
				}
				
				if (line.substring(0, 3).equals("002")) {
					retorno = Valid002(index,line);
				}
			}
			if (line.substring(0, 3).equals("003")) {
				retorno = Valid003(index, line);
			}
		}
		
		return retorno;
	}

	private boolean Valid001(int index, String line) {
		int i = index;
		boolean retorno = false;

		String[] strSeparators = config.getSeparators();

		do {
			if (!StringUtils.isNumeric(line.substring(i, (i + 1)))) {
				retorno = false;
				i = 0;
				break;
			}
			
			do {
				i++;
			} while (StringUtils.isNumeric(line.substring(i, (i + 1))));
			
			if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
				do {
					i++;
					if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
						break;
					}

					if (strSeparators[5].equals(line.substring(i, (i + 1)))) {
						i++;
					}
				} while (StringUtils.isAlpha(line.substring(i, (i + 1))));
				
				if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
					do {
						i++;
						if (strSeparators[3].equals(line.substring(i, (i + 1)))) {
							i++;
						}
					} while (StringUtils.isNumeric(line.substring(i, (i + 1))));

					if (line.substring(i, (i + 1)).equals("\n")) {
						retorno = true;
						i = 0;
						break;
					}
				} else {
					retorno = false;
					i = 0;
					break;
				}
			} else {
				retorno = false;
				i = 0;
				break;
			}

		} while(i > 0);
		
		return retorno;
	}

	private boolean Valid002(int index, String line) {
		int i = index;
		boolean retorno = false;

		String[] strSeparators = config.getSeparators();

		do {
			if (!StringUtils.isNumeric(line.substring(i, (i + 1)))) {
				retorno = false;
				i = 0;
				break;
			}
			
			do {
				i++;
			} while (StringUtils.isNumeric(line.substring(i, (i + 1))));
			
			if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
				do {
					i++;
					if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
						break;
					}
					
					if (strSeparators[5].equals(line.substring(i, (i + 1)))) {
						i++;
					}
				} while (StringUtils.isAlpha(line.substring(i, (i + 1))));
				
				if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
					do {
						i++;
						if (strSeparators[5].equals(line.substring(i, (i + 1)))) {
							i++;
						}
					} while (StringUtils.isAlpha(line.substring(i, (i + 1))));

					if (line.substring(i, (i + 1)).equals("\n")) {
						retorno = true;
						i = 0;
						break;
					}
				} else {
					retorno = false;
					i = 0;
					break;
				}
			} else {
				retorno = false;
				i = 0;
				break;
			}

		} while(i > 0);
		
		return retorno;
	}
	
	private boolean Valid003(int index, String line) {
		int i = index;
		boolean retorno = false;

		String[] strSeparators = config.getSeparators();
		
		do {
			if (!StringUtils.isNumeric(line.substring(i, (i + 1)))) {
				retorno = false;
				i = 0;
				break;
			}

			do {
				i++;
			} while (StringUtils.isNumeric(line.substring(i, (i + 1))));

			if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
				i++;
				if (strSeparators[0].equals(line.substring(i, (i + 1)))) {
					do {
						i++;
						if (strSeparators[1].equals(line.substring(i, (i + 1)))) {
							i++;
						}
						if (strSeparators[2].equals(line.substring(i, (i + 1)))) {
							i++;
						}
						if (strSeparators[3].equals(line.substring(i, (i + 1)))) {
							i++;
						}
						if (strSeparators[4].equals(line.substring(i, (i + 1)))) {
							i++;
							break;
						}
					} while (StringUtils.isNumeric(line.substring(i, (i + 1))));

					if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
						do {
							i++;
							if (strSeparators[5].equals(line.substring(i, (i + 1)))) {
								i++;
							}
						} while (StringUtils.isAlpha(line.substring(i, (i + 1))));

						if (line.substring(i, (i + 1)).equals("\n")) {
							retorno = true;
							i = 0;
							break;
						}
					} else {
						retorno = false;
						i = 0;
						break;
					}
				}
			} else {
				retorno = false;
				i = 0;
				break;
			}
		} while (i > 0);

		return retorno;
	}
	
}
