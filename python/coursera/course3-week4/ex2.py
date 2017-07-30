# To run this, you can install BeautifulSoup
# https://pypi.python.org/pypi/beautifulsoup4

# Or download the file
# http://www.py4e.com/code3/bs4.zip
# and unzip it in the same directory as this file

import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter URL: ')
countStr = input('Enter count: ')
count = int(countStr) + 1
positionStr = input('Enter position: ')
position = int(positionStr)

while count > 0:
    count = count - 1;
    print("Retrieving: ", url)
    if (count == 0):
        break
    html = urllib.request.urlopen(url, context=ctx).read()
    soup = BeautifulSoup(html, 'html.parser')
    tags = soup('a')
    pos = 0;
    for tag in tags:
        if tag.get('href', None) is None:
            continue
        pos = pos + 1
        if pos < position:
            continue
        url = tag.get('href', None)
        break
