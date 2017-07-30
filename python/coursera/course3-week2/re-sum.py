import re
import sys

file = open(sys.argv[1], 'r')
content = file.read();

numStrList = re.findall('[0-9]+', content)

sum = 0
for numStr in numStrList:
    sum = sum + int(numStr)

print(sum)
