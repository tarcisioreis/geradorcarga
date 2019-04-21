# GeradorCarga

Descrição:

Aplicação Java Desktop para leitura de arquivo com extensão .dat com formato:

    Ver diretório Documentação.


Fluxo da aplicação:

1) A configuração para funcionamento consiste em criar os diretórios data a partir da variável de ambiente HOMEPATH, DEVE conter no diretório data, os sub-diretório in e out, os arquivos dat devem estar no diretório /data/in/;

2) A leitura é feita de forma automática por meio multthreading, ou seja, ao chamar a aplicação por linha comando ela fica auto-executável, sendo executada a cada 5 segundo para leitura dos próximos arquivos;

3) Após leitura, é feita validação da extensão que deve ser obrigatoriamente .dat, depois valida-se o formato do arquivo;

4) Calcula-se a quantidade de clientes, vendedores, ID da venda mais cara e o pior vendedor que são salvos em outro arquivo com formato flat_filename.done.dat

A aplicação usa Máquina de Turing para reconhecimento do padrão do formato do arquivo além das bibliotecas de validação de arquivo e string do Apache Commons para Java 8, FileUtils e StringUtils.

Executável encontra-se no diretório /bin/GeradorCarga.jar


Dúvidas ou bugs entrar em contato com Tarcisio Machado dos Reis - +55 51 9 8490-4355 ou tarcisio.reis.ti@gmail.com
