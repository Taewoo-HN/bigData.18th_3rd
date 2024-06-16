// 시간을 출력하기 위한 공간
const col = document.querySelector('#currenTime');

// 시간을 출력하기 위한 변수
const date = new Date();
const year = date.getFullYear();
const month = date.getMonth();
const hours = date.getHours();

// print the current time
col.innerText = `작성 시각 : ${year}년 ${month+1}월 ${hours}시 기준 등록된 연락처입니다.`;
