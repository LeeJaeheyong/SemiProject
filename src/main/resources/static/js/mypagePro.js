let picture = null;

function previewImage(event) {
    const file = event.target.files[0];  // 선택한 파일
    const reader = new FileReader();
	
    reader.onload = function(e) {
        // 미리보기 이미지 요소 가져오기
        const imagePreview = document.getElementById('imagePreview');
        
        // 이미지 URL 설정
        imagePreview.src = e.target.result;
        
    };

    if (file) {
        reader.readAsDataURL(file);  // 파일을 데이터 URL로 읽어오기
    }
	
	picture = 'edit';
	
	console.log(picture);
}	

function filedelete() {
	const imagePreview = document.getElementById('imagePreview');
	    
	    // 이미지 미리보기 삭제 후 "인간.png"로 설정
	    imagePreview.src = '/img/인간.png';  // 기본 이미지로 설정
	    
	    // 파일 input 초기화 (선택된 파일 초기화)
	    //const fileInput = document.getElementById('fileInput');
	    //fileInput.value = '';  // 파일 선택 초기화
		
		picture = 'remove';
		
		console.log(picture);
}

function submitForm() {
	const pictureEle = document.getElementById("picture");
	pictureEle.value = picture;
    // 폼을 직접 제출하는 JavaScript 함수
    document.getElementById('profileForm').submit();
}