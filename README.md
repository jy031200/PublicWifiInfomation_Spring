# PublicWifiInfomation_Spring
서울시 공공 와이파이 위치 정보 웹사이트입니다. 
이 웹사이트는 OpenAPI와 Spring을 활용하여 제작되었습니다. (OpenAPI는 서울 열린데이터광장에서 서울시 공공 와이파이 위치 정보 OpenAPI를 사용했습니다.)

## 주요기능
### 홈
- 웹사이트의 기본 화면으로 돌아가며, 다른 기능을 선택할 수 있습니다.
![image](https://github.com/user-attachments/assets/a4f66ee8-f7b9-44f4-98cf-781b62caa20d)

### Open API 와이파이 정보 가져오기
- 서울시의 공공 와이파이 정보가 포함된 Open API를 호출하여 최신 와이파이 데이터를 가져와 DB에 저장합니다.
![image](https://github.com/user-attachments/assets/2ac3de19-fdad-4fad-840f-03f83fd4bfce)

### 내 위치 가져오기
- 사용자의 현재 위치를 기반으로 위도와 경도 좌표를 자동으로 가져옵니다.
- 이 기능을 통해 사용자는 자신의 현재 위치를 기반으로 데이터를 조회할 수 있습니다.
![image](https://github.com/user-attachments/assets/4b8a8821-b4ee-461a-8dea-57b3db4f7e5a)

### 근처 WIFI 정보 보기
- 사용자가 입력한 X,Y 위치 좌표값을 기준으로 거리를 계산하여 가까운 와이파이 정보 10개를 화면에 표시합니다.
- 이를 통해 사용자에게 가까운 와이파이 핫스팟을 쉽게 찾을 수 있습니다.
![image](https://github.com/user-attachments/assets/ceed66d3-776e-40a1-b9d3-af33346bf5b5)

### 추가 예정 기능
- 위치 히스토리 목록: 사용자가 조회한 위치의 히스토리를 목록으로 제공하는 기능입니다.
- 자세한 정보: 와이파이 정보 클릭 시 자세한 정보를 제공하는 기능입니다.
- 북마크 관련 기능: 사용자가 관심 있는 와이파이 위치를 북마크하고 쉽게 접근할 수 있는 기능입니다.

## +ERD 추가+
![image](https://github.com/user-attachments/assets/63210ec3-e518-4c89-9c11-6162fff73e1d)

