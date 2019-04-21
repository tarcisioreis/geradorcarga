package com.geradorcarga.app.readfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.geradorcarga.app.bean.ItemVenda;
import com.geradorcarga.app.bean.Venda;
import com.geradorcarga.app.config.Config;
import com.geradorcarga.app.validator.Validator;

public class ReadFile {

	private Config config;
	private Validator validator;
	private List<File> listFiles;
	
	private String fileNameIn;
	private String fileNameOut;

	private StringBuilder sb;
	private int contCliente;
	private int contVendedor;
	
	private List<String> listDocVendedor;
	private List<String> listDocCliente;
	private List<Venda> listVendas;
	
	public ReadFile() {}
	
	public ReadFile(Config config) {
		this.config = config;
		this.validator = new Validator(config);
		this.contCliente = 0;
		this.contVendedor = 0;
		this.listDocVendedor = null;
		this.listDocCliente = null;
		this.listVendas = null;
	}

	public ReadFile(String fileNameIn) {
		this.fileNameIn = fileNameIn;
	}
	
	public List<File> getListFiles() {
		return listFiles;
	}
	public void setListFiles(List<File> listFiles) {
		this.listFiles = listFiles;
	}

	public String getFileNameIn() {
		return fileNameIn;
	}
	public void setFileNameIn(String fileNameIn) {
		this.fileNameIn = fileNameIn;
	}

	public String getFileNameOut() {
		return fileNameOut;
	}
	public void setFileNameOut(String fileNameOut) {
		this.fileNameOut = fileNameOut;
	}

	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	public int getContCliente() {
		return contCliente;
	}
	public void setContCliente(int contCliente) {
		this.contCliente = contCliente;
	}

	public int getContVendedor() {
		return contVendedor;
	}
	public void setContVendedor(int contVendedor) {
		this.contVendedor = contVendedor;
	}

	public List<String> getListDocVendedor() {
		return listDocVendedor;
	}
	public void setListDocVendedor(List<String> listDocVendedor) {
		this.listDocVendedor = listDocVendedor;
	}

	public List<String> getListDocCliente() {
		return listDocCliente;
	}
	public void setListDocCliente(List<String> listDocCliente) {
		this.listDocCliente = listDocCliente;
	}
	
	public List<Venda> getListVendas() {
		return listVendas;
	}
	public void setListVendas(Venda venda) {
		this.listVendas.add(venda);
	}
	
	@SuppressWarnings("static-access")
	public boolean openFileInput() throws Exception {
		boolean retorno = false;
		File folder = null;
		
		// Testa qual sistema operacional esta rodando
		if (config.getOs().toUpperCase().contains("WIN")) {
		    folder = new File(config.getHomeDrive()+config.getHomePath()+config.getDataIn());
		} else {
			folder = new File(config.getHomePath()+config.getDataIn());	
		}
		
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	if (validator.isFileNameIn(file.getName())){
		    		
		    		if (this.listFiles == null) {
		    			this.listFiles = new ArrayList<File>();
		    		}
		    		
		    		this.listFiles.add(file);
		    		retorno = true;
		    	}
		    }
		}
		
		return retorno;
	}

	public void readFileInput() throws Exception {
		List<File> lista = this.getListFiles();
		
		BufferedReader br = null;
		String line = "";

		if (this.sb == null) {
			this.sb = new StringBuilder();
		}

		for(int i = 0; i < lista.size(); i++) {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(lista.get(i)), "UTF-8"));
			
		    while ((line = br.readLine()) != null) {
		    	this.sb.append(line).append("\n");
	        }
		
		    br.close();

			setContCliente(0);
			setContVendedor(0);
			setListDocCliente(null);
			setListDocVendedor(null);
		    
		    if (this.sb != null) {
		    	this.setSb(sb);
		    	Contabiliza("VENDEDOR");
		    	Contabiliza("CLIENTE");
		    	Contabiliza("VENDAS");
		    	
		    	CreateFileOutPut(config, FilenameUtils.removeExtension(lista.get(i).getName()));
		    
		    	this.sb = new StringBuilder();
		    }
		    
		    line = "";
		}
	}

	private void CreateFileOutPut(Config config, String fileNameInput) throws IOException {
		String fileInput = null, fileOutput = null;
		Double total = 0.0;
		int id = 0;
		String[] valores = new String[3];
		
		Writer wr = null;
		
				
	    if (config.getOs().toUpperCase().contains("WIN")) {
	    	wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config.getHomeDrive()+
							  													config.getHomePath()+
							  													config.getDataOut()+
							  													"flat_" + 
							  													fileNameInput + 
							  													".done." + 
							  													config.getExtension()), "UTF-8"));
	    	fileInput = config.getHomeDrive()+
						config.getHomePath()+
						config.getDataIn()+
						fileNameInput + "." +
						config.getExtension();
	    	fileOutput = config.getHomeDrive()+
						 config.getHomePath()+
						 config.getDataIn()+
						 "proc_" +
						 fileNameInput + "." +
						 config.getExtension();
		} else {
	    	wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config.getHomePath()+
																				config.getDataOut()+
																				"flat_" + 
																				fileNameInput + 
																				".done." + 
																				config.getExtension()), "UTF-8"));
	    	fileInput = config.getHomePath()+
						config.getDataIn()+
						fileNameInput + "." + 
						config.getExtension();
	    	fileOutput = config.getHomePath()+
						 config.getDataIn()+
						 "proc_" +
						 fileNameInput +  "." +
						 config.getExtension();
		}

	    List<Venda> lista = getListVendas();
	    
	    for(Venda vendas : lista) {
	    	id = vendas.getId();
	    	
	    	for (int i = 0; i < vendas.getListaItensVenda().size(); i++) {
	    		total += vendas.getListaItensVenda().get(i).getQuantidade()*
	    				 vendas.getListaItensVenda().get(i).getPreco();
	    		
	    	}
	    	
	    	if (valores[0] == null) {
	    		valores[0] = String.valueOf(id);
	    		valores[1] = String.valueOf(total);
	    		valores[2] = vendas.getNameSalesMan();
	    	} else if (Double.valueOf(valores[1]) < total) {
	    		valores[0] = String.valueOf(id);
	    		valores[1] = String.valueOf(total);
	    		valores[2] = vendas.getNameSalesMan();
	    	} else if (Double.valueOf(valores[1]) > total) {
	    		valores[2] = vendas.getNameSalesMan();
	    	}
	    	
	    	total = 0.0;
	    }

	    wr.write(getContCliente()+config.getSeparatorMain()+getContVendedor()+
	    		 (valores[0] != null ? config.getSeparatorMain() + valores[0]:"")+
	    		 (valores[2] != null ? config.getSeparatorMain() + valores[2]:"")+"\n");
	    wr.close();
	    
	    FileUtils.moveFile(FileUtils.getFile(fileInput), FileUtils.getFile(fileOutput));
	}

	private void Contabiliza(String param) throws Exception{
		String line = null;
		
		char car = '\u0000';

		if (this.getSb() != null) {
			for (int i = 0; i < this.getSb().length(); i++) {
				car = this.getSb().charAt(i);
				if (line == null) {
					line = new Character(car).toString();
				} else line += car;

				if (car == '\n') {
					if (validator.isFileFormatValid(line)) {
						if (line.substring(0, 3).equals("001") && param.equals("VENDEDOR")) {
							setContVendedor(Retorna(param,line));
						}
						if (line.substring(0, 3).equals("002") && param.equals("CLIENTE")) {
							setContCliente(Retorna(param,line));
						}
						if (line.substring(0, 3).equals("003") && 
							!param.equals("CLIENTE") &&
						    !param.equals("VENDEDOR")) {
							if (getListVendas() == null) {
								this.listVendas = new ArrayList<Venda>();
							}	
							setListVendas(RetornaVendas(line));
						}
					} else throw new Exception("Formato de arquivo inválido. Verifique!");
					
					line = null;
				}
			}
		}
	}
	
	private int Retorna(String param, String line) {
		int contagem = 0;
		
		List<String> listDocumentos = new ArrayList<String>();
		
		if (param.equals("VENDEDOR")) {
			if (getListDocVendedor() == null) {
				listDocumentos.add(line.substring(4, 17));
				setListDocVendedor(listDocumentos);
				contagem = getContVendedor() + 1;
			} else {
				listDocumentos = getListDocVendedor();
				if (!listDocumentos.contains(line.substring(4, 17))) {
					listDocumentos.add(line.substring(4, 17));
					setListDocVendedor(listDocumentos);
					contagem = getContVendedor() + 1;
				}
			}
		}
		
		if (param.equals("CLIENTE")) {
			if (getListDocCliente() == null) {
				listDocumentos.add(line.substring(4, 20));
				setListDocCliente(listDocumentos);
				contagem = getContCliente() + 1;
			} else {
				listDocumentos = getListDocCliente();
				if (!listDocumentos.contains(line.substring(4, 20))) {
					listDocumentos.add(line.substring(4, 20));
					setListDocCliente(listDocumentos);
					contagem = getContCliente() + 1;
				}
			}
		}

		if (contagem == 0) {
			contagem++;
		}
		
		return contagem;
	}
	
	private Venda RetornaVendas(String line) throws Exception {
		Venda venda = new Venda();
		
		venda.setId(Integer.parseInt(retornaIdVenda(line)));
		venda.setListaItensVenda(retornaListaItemVenda(line));
		venda.setNameSalesMan(retornaNameSalesMan(line));

		return venda;
	}
	
	private String retornaNameSalesMan(String line) {
		String aux = null;
		int i = 3;
		char car = '\u0000';

		String[] strSeparators = config.getSeparators();
		
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
						if (line.substring(i, (i + 1)).equals("\n")) {
							break;
						}	
						car = line.charAt(i);
						if (aux == null) {
							aux = new Character(car).toString();
						} else aux += car;
					} while (StringUtils.isAlpha(line.substring(i, (i + 1))));
				}	
			}
		}

		return aux;
	}

	private List<ItemVenda> retornaListaItemVenda(String line) throws Exception {
		List<ItemVenda> lista = new ArrayList<ItemVenda>();

		ItemVenda itemVenda = new ItemVenda();

		String id = null, quantidade = null, preco = null;
		int i = 3;
		char car = '\u0000';

		String[] strSeparators = config.getSeparators();
		
		do {
			i++;
		} while (StringUtils.isNumeric(line.substring(i, (i + 1))));

		if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
			i++;
			if (strSeparators[0].equals(line.substring(i, (i + 1)))) {
				i++;
				do {
					if (StringUtils.isNumeric(line.substring(i, (i + 1)))) {
						if (id == null) {
							do {
								car = line.charAt(i);

								if (id == null) {
									id = new Character(car).toString();
								} else id += car;
								i++;
							} while(StringUtils.isNumeric(line.substring(i, (i + 1))));
							
							if (id != null) {
								itemVenda.setId(Integer.parseInt(id));
								car = '\u0000';
							}
						} else if (quantidade == null) {
							do {
								car = line.charAt(i);

								if (quantidade == null) {
									quantidade = new Character(car).toString();
								} else quantidade += car;
								i++;
							} while(StringUtils.isNumeric(line.substring(i, (i + 1))));
							
							if (quantidade != null) {
								itemVenda.setQuantidade(Integer.parseInt(quantidade));
								car = '\u0000';
							}
						} else if (preco == null) {
							do {
								car = line.charAt(i);

								if (preco == null) {
									preco = new Character(car).toString();
								} else preco += car;
								i++;
								
								if (strSeparators[4].equals(line.substring(i, (i + 1))) ) {
									break;
								}
							} while(!strSeparators[2].equals(line.substring(i, (i + 1))));
							
							if (preco != null) {
								Double pr = Double.parseDouble(preco);
								itemVenda.setPreco(pr);
								
								if (lista != null) {
									lista.add(itemVenda);
									itemVenda = new ItemVenda();
								}
								
								car = '\u0000';
								id = null;
								quantidade = null;
								preco = null;
							}
						}
						
					}
					if (strSeparators[1].equals(line.substring(i, (i + 1)))) {
						i++;
					}
					if (strSeparators[2].equals(line.substring(i, (i + 1)))) {
						i++;
					}
					if (strSeparators[3].equals(line.substring(i, (i + 1)))) {
						i++;
					}
				} while (!strSeparators[4].equals(line.substring(i, (i + 1))));
			}
		}
		
		return lista;
	}

	private String retornaIdVenda(String line) {
		String aux = null;
		int i = 3;
		char car = '\u0000';
		
		do {
			i++;
			if (config.getSeparatorMain().equals(line.substring(i, (i + 1)))) {
				break;
			}
			car = line.charAt(i);
			if (aux == null) {
				aux = new Character(car).toString();
			} else aux += car;

		} while (StringUtils.isNumeric(line.substring(i, (i + 1))));
		
		return aux;
	}
	
}
