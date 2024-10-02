function resetdata(){ // 기존 테이블 데이터 삭제
    fetch(`/deletedata`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log('Data reset successfully.');
            } else {
                console.log('Data reset failed.',response.statusText);
            }
        })
        .catch(error => {
            console.error("ERROR",error);
        })
}

function createTable(data) { // 테이블 생성하여 데이터 있으면 데이터 띄우기, 없으면 없다고 띄우기
    let td;
    let row;
    let i;
    console.log("Table creation initiated");

    // 테이블 생성
    var table = document.createElement('table');
    table.id = 'data-table';

    // 테이블 헤더 생성
    var thead = document.createElement('thead');
    var headerRow = document.createElement('tr');
    var headers = ['거리(Km)', '관리번호', '자치구', '와이파이명', '도로명주소', '상세주소', '설치위치(층)', '설치유형', '설치기관', '서비스구분', '망종류', '설치년도', '실내외구분', 'WIFI접속환경', 'X좌표', 'Y좌표', '작업일자'];

    for (i = 0; i < headers.length; i++) {
        var th = document.createElement('th');
        th.innerText = headers[i];
        headerRow.appendChild(th);
    }
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // 테이블 바디 생성
    var tbody = document.createElement('tbody');

    // 데이터로 테이블 행 생성
    if (data.length === 0) {
        console.log("No data available");
        row = document.createElement('tr');
        td = document.createElement('td');
        td.colSpan = headers.length;
        td.innerText = "위치 정보를 입력 후 조회해 주세요.";
        row.appendChild(td);
        tbody.appendChild(row);
    } else {
        console.log(`${data.length} rows to display`);
        for (i = 0; i < data.length; i++) {
            row = document.createElement('tr');

            // 각 객체의 속성에 직접 접근
            td = document.createElement('td');
            td.innerText = data[i].distance_num || ""; // 거리
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].wifi; // 관리번호
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].area; // 자치구
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].wifi_name; // 와이파이명
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].address; // 도로명주소
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].detail_address; // 상세주소
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].floor; // 설치위치(층)
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].wifi_type; // 설치유형
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].wifi_organ; // 설치기관
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].service; // 서비스구분
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].mesh; // 망종류
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].install_year; // 설치년도
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].in_out; // 실내외구분
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].connect; // WIFI접속환경
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].X; // X좌표
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].Y; // Y좌표
            row.appendChild(td);

            td = document.createElement('td');
            td.innerText = data[i].work_year; // 작업일자
            row.appendChild(td);

            tbody.appendChild(row);
        }
    }
    table.appendChild(tbody);

    // 기존 테이블이 있으면 제거
    var existingTable = document.getElementById('data-table');
    if (existingTable) {
        existingTable.remove();
    }

    document.getElementById('table-container').appendChild(table);
}


function getLocation() { // 위치 좌표 가져와서 textbox1,2에 띄움
    navigator.geolocation.getCurrentPosition(function(position) {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;

        //lat,lng 위치
        document.getElementById('textbox1').value = lat;
        document.getElementById('textbox2').value = lng;

/*        // 서버로 데이터 전송
        fetch(`/distance?latitude=${lat}&longitude=${lng}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            /!*body: JSON.stringify({ latitude: lat, longitude: lng })*!/
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });*/
    });
}

function fetchData() { // distance_num 계산해서 가장 최근 데이터 10개 보여주기
    //lat,lng 위치
    var lat = document.getElementById('textbox1').value;
    var lng = document.getElementById('textbox2').value;

    if (lat && lng) {
        // 서버로 데이터 전송
        fetch(`/distance?latitude=${lat}&longitude=${lng}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
            });
    } else {
        alert("위치 정보를 입력한 후에 조회해 주세요.");
    }
    location.reload();
}


/*function fetchData() { // 근처 WIFI 정보 보기 버튼 클릭 시 distance_num 계산해서 가장 최근 데이터 10개 보여주기
    var lat = document.getElementById('textbox1').value;
    var lng = document.getElementById('textbox2').value;

    if (lat && lng) {
        fetch(`/display.jsp?lat=` + lat + '&lng=' + lng)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                // 응답 확인을 위해 response 로그 출력
                console.log(response);
                return response.json();
            })
            .then(data => {
                createTable(data);
            })
            .catch(error => console.error('Error fetching data:', error));
    } else {
        alert("위치 정보를 입력한 후에 조회해 주세요.");
    }
    location.reload();
}*/

/*
function connectapi() {
    fetch('${pageContext.request.contextPath}/connect-api')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('OpenApi response:', data);
            // 서버에서 받은 데이터를 가지고 필요한 처리를 수행
            processData(data);
        })
        .catch(error => console.error('Error fetching OpenApi:', error));
}

function processData(data) {
    // 데이터를 가지고 필요한 처리를 수행
    createTable(data.data); // Display data in the table
    // 기타 필요한 처리
}

function del(){
    fetch(`${pageContext.request.contextPath}/open-api`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('OpenApi response:', data);
            //createTable(data); // Display data in the table
        })
        .catch(error => console.error('Error fetching OpenApi:', error));

    // Handle jsonData from server-side rendering
    var jsonData = '<%= jsonData %>';
    if (jsonData) {
        try {
            var data = JSON.parse(jsonData);
            createTable(data);
        } catch (error) {
            console.error('Error parsing jsonData:', error);
        }
    }
    location.reload();
}*/
