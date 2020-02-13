import re
import os
import sys
import json
import requests
from utils import checkSpellKo
# global variables 
 ## api key and pwd
client_id = ''
client_pwd = ''

# header dictionary
headers = {
    'X-NCP-APIGW-API-KEY-ID' :client_id,
    'X-NCP-APIGW-API-KEY' : client_pwd
}
# encpoint url
url = 'https://naveropenapi.apigw.ntruss.com/nmt/v1/translation'

########################################
def getErrorMsg(rescode):
    """return error msg
    """
    errorCode = json.loads(rescode.text)['errorCode']
    if errorCode == 'N2MT05':
        return 'source와 target이 동일합니다.'
    elif errorCode == 'N2MT07':
        return 'text 파라미터가 필요합니다.'
    elif errorCode == 'N2MT08':
        return 'text 파라미터가 최대 용량을 초과했습니다.'
    else:
        return errorCode +': 내부 서버 오류입니다.'

def getData(text,source_lang,target_lang):
    """ return data format
    """
    val = {
    'source' : source_lang,
    'target' : target_lang,
    'text' : text
}
    return val
##########################################


def splitText(org_Text):
    """ split txt
    """
    line ,paragraph ="", []

    for s in org_Text:
        line += s
        if s == '\n' or ' \n':
            paragraph.append(line)
            line =''
    txt, split_paragh='',[]
    for text in paragraph:
        if text =='\n':
            split_paragh.append(txt)
            txt = ''
        else:
            txt += text
    split_paragh.append(txt.strip())
    return split_paragh
    

###################################
############## TEST ###############
# 번역할 text 
file_name = './txt/case3.txt'
text = open(file_name, 'r', encoding='utf-8').read()
val = getData(text, 'ko', 'en')

# response = requests.post(url, data=val, headers=headers)
# rescode = response.status_code
preprocessedTextList = splitText(text)
while '' in preprocessedTextList:
    preprocessedTextList.remove('')
while ' ' in preprocessedTextList:
    preprocessedTextList.remove(' ')

# 오탈자 고침 적용
# e = checkSpellKo(preprocessedTextList) 
# print(e)


responses, rescodes = [], []

for t in preprocessedTextList:
    val['text'] = t
    response = requests.post(url, data=val, headers=headers)
    rescode = response.status_code

    dicts = json.loads(response.text)
    responses.append(dicts['message']['result']['translatedText'])
    rescodes.append(rescode)

if rescode == 200:
    #print(responses)
    # file write
    f = open(file_name[:-4]+'_'+'result.txt','w')
    f.write(dicts['message']['result']['translatedText'])
    f.close()
else:
    print("Error : " + response.text)

    