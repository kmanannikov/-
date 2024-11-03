# Урок 8. Семинар: Подключение сторонних репозиториев, ручная установка пакетов

## 1. Подключить репозиторий с nginx любым удобным способом, установить nginx и потом удалить nginx, используя утилиту dpkg.
Узнаю версию `Ubuntu`:
```console
duckluckbreakout@ubuntuserver:~$ lsb_release -a
No LSB modules are available.
Distributor ID:	Ubuntu
Description:	Ubuntu 20.04 LTS
Release:	20.04
Codename:	focal
```
Подключение репозитория:
```console
root@ubuntuserver:/etc/apt/sources.list.d# echo "deb http://nginx.org/packages/ubuntu focal nginx" > nginx.list
root@ubuntuserver:/etc/apt/sources.list.d# curl -fsSL https://nginx.org/keys/nginx_signing.key | sudo apt-key add -
OK
root@ubuntuserver:/etc/apt/sources.list.d# sudo apt update
root@ubuntuserver:/etc/apt/sources.list.d# sudo apt install nginx -y
```
## 2. Установить пакет на свой выбор, используя snap.
```console
duckluckbreakout@ubuntuserver:/etc/apt/sources.list.d$ sudo snap install robomongo
robomongo 0.9.0-rc9 from Francesco Banconi (frankban) installed
```