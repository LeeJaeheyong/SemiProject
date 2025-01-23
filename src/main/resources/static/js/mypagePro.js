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
}	

function submitForm() {
    // 폼을 직접 제출하는 JavaScript 함수
    document.getElementById('profileForm').submit();
}
