import urllib.request, urllib.parse, urllib.error
import json

while True:
    url = input('Enter location: ')
    if len(url) < 1: break

    print('Retrieving', url)
    uh = urllib.request.urlopen(url)
    data = uh.read()
    print('Retrieved', len(data), 'characters')

    content = json.loads(data)
    comments = content['comments']
    sum = 0
    for comment in comments:
        sum = sum + int(comment['count'])
    print('Count:', len(comments))
    print('Sum:', sum)
