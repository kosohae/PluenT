import os
import sys
#import requests
import urllib.request

# global variables 
 ## api key and pwd
client_id = ''
client_pwd = ''
#content_type = 'application/x-www-form-urlencoded'

# endpoint url
url = 'https://naveropenapi.apigw.ntruss.com/voice/v1/tts'

# header
request = urllib.request.Request(url)
request.add_header("X-NCP-APIGW-API-KEY-ID",client_id)
request.add_header("X-NCP-APIGW-API-KEY",client_pwd)
#request.add_header('Content-Type',content_type)

# post bondy none
# speaker dictionary
"""
speaker : 
mijin : 한국어, 여성 음색
jinho : 한국어, 남성 음색
clara : 영어, 여성 음색
matt : 영어, 남성 음색
shinji : 일본어, 남성 음색
meimei : 중국어, 여성 음색
liangliang : 중국어, 남성 음색
jose : 스페인어, 남성 음색
carmen : 스페인어, 여성 음색
"""
speakers={ 
    1:'mijin',
    2:'jinho',
    3:'clara',
    4:'matt',
    5:'shinji', 
    6:'meimei',
    7:'liangliang',  
    8:'jose',
    9:'carmen' 
}

def getData(speaker, speed, text):
    data = f"speaker={speaker}&speed={speed}&text=" + text
    return data


###################################
############## TEST ###############

#txt ='I would like to consult my career with a foreign mentor. My interests are data and machine learning.'
txt = open()
text = urllib.parse.quote(txt)
data = getData('clara', '0', text)

#response = requests.post(url, data=data, headers=headers)
response = urllib.request.urlopen(request, data=data.encode('utf-8'))
rescode = response.getcode()

if rescode == 200:
    print('tts MP3 saved....')
    response_body = response.read()
    with open('test.mp3', 'wb') as f:
        f.write(response_body)
else:
    print("Error Code: " + rescode)