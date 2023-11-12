#!/bin/bash

# Atualiza a lista de pacotes e atualiza o sistema
sudo apt update && sudo apt upgrade

# Verifica a versão do Java
java -version

sudo apt update

sudo apt install docker.io -y

sudo systemctl start docker

sudo systemctl enable docker

docker run

# Verifica se o Java não está instalado e, se não estiver, solicita a instalação
if [$? = 0];
    then
        echo "Java instalado"
    else
        echo "Java não está instalado. Deseja instalar? (y/n)"
        read get
        if [ $? == 'y' ]; then
            sudo apt install openjdk-17-jre -y
        fi
fi


# Baixa o arquivo .jar do meu grupo de PI
curl -O -L "https://github.com/GuiScarabelli/individual---sprint2/blob/main/teste-looca%20-%20arquivo%20p%20teste/out/artifacts/teste_looca_jar/teste-looca.ja>

# Executa o arquivo .jar do meu grupo de PI
java -jar teste-looca.jar


chmod +x scriptIndividual.sh
