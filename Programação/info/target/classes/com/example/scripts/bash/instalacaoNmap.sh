sudo apt update

dpkg -s nmap > /dev/null 2>&1

if [ $? -ne 0 ]; then
    sudo apt-get install nmap -y
    echo $?
fi

# -ne (not equal) | !=