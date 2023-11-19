#!/bin/bash

# Atualiza a lista de pacotes e atualiza o sistema
sudo apt update && sudo apt upgrade -y

# Instalação do Docker
sudo apt install docker.io -y
sudo systemctl start docker
sudo systemctl enable docker

# Verifica se o Java não está instalado e, se não estiver, solicita a instalação
if [ $? -eq 0 ]; then
    echo "Java instalado"
else
    echo "Java não está instalado. Deseja instalar? (y/n)"
    read get
    if [ "$get" == 'y' ]; then
        sudo apt install openjdk-17-jre -y
    fi
fi

# Executar o docker
docker docker run -d -p 3306:3306 guiscarabelli/bancodedados:latest

sleep 5

docker run -it guiscarabelli/jarexec:latest