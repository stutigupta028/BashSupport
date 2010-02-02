#!/bin/sh

commands="
apt
apt-get
ant
awk
bash
bc
bzip2
cat
cp
dd
egrep
file
find
gcc
grep
gs
gunzip
gzip
head
ifconfig
info
java
javac
kill
killall
ld
ldd
less
locate
ls
lsmod
man
make
modprobe
perl
ps
rar
reset
rm
rmdir
rmmod
sed
seq
sleep
su
sudo
tar
tail
tee
test
top
tr
mount
umount
vim
wget
who
whoami
whois
xargs
xsltproc
zip
zless"

for f in $commands; do echo $f; man -E UTF-8 "$f" | txt2html -8 > "$f.html"; done;
