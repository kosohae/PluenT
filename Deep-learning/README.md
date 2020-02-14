# DeepLearning
- PluenT 솔루션의 발표 지도 기능을 위한 코드입니다.
- 기능 : 시간에 따라 반환합니다.

## face_rcog
- CNN(resnet101)모델 finetuning => abnormal (sad and fear)/ normal (neutral)판단합니다.
- 형태 : Video => video detect extract => images => googlenet or resnet101=> abnormal or normal
- 학습 파일 : https://drive.google.com/open?id=1mHskdjcc1OSGi6TxAVbQxS1YLQz9rjL-

## flask
app.py <=> test.py flask로 띄워서 통신하는 코드입니다.
이미지 path(binary 파일)를 받아서 json형태로 {class id:0, class name:정상}를 반환합니다. 

- utils.py 
번역 품질을 높이기 위한 오타 및 맞춤법 교정 코드입니다.

