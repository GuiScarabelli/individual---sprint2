FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=123

ADD SCRIPT_DO_BANCO.sql /docker-entrypoint-initdb.d/

EXPOSE 3306


