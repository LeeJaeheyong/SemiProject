document.querySelectorAll('#username, #password, #passwordcheck, #email, #name, #birthdate, #phone').forEach(function(inputElement) {
    inputElement.addEventListener('input', function() {
        const fieldId = this.id;
        const value = this.value;
        let pattern;

        if (fieldId === 'password') {
            const errorBox = document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(2)');
            pattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}[\]|:;"'<>,.?])[A-Za-z0-9!@#$%^&*()_+{}[\]|:;"'<>,.?]{8,}$/;

            if (value.trim() === '') {
                errorBox.textContent = '비밀번호: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = '';
            }
        }

        if (fieldId === 'passwordcheck') {
            const errorBox = document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(3)');
            const passwordValue = document.querySelector('#password').value;

            if (value.trim() === '') {
                errorBox.textContent = '비밀번호 확인: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (value !== passwordValue) {
                errorBox.textContent = '비밀번호가 일치하지 않습니다.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = '';
            }
        }

        if (fieldId === 'email') {
            const errorBox = document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(4)');
            pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (value.trim() === '') {
                errorBox.textContent = '이메일: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '이메일: 이메일 주소가 정확한지 확인해 주세요.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = ''
            }
        }

        if (fieldId === 'phone') {
            const errorBox = document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(3)');
            pattern = /^(01[0-9])-(\d{3,4})-(\d{4})$/;

            if (value.trim() === '') {
                errorBox.textContent = '휴대전화번호: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '휴대전화번호: 휴대전화번호가 정확한지 확인해 주세요.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = ''
            }
        }
    });
});

document.getElementById('submitButton').addEventListener('click', function(event) {
    event.preventDefault();  // 버튼 클릭 시 폼 제출을 막습니다.

    let allValid = true;  // 모든 입력이 유효한지 여부를 추적

    // 각 필드에 대해 유효성 검사
    document.querySelectorAll('#username, #password, #passwordcheck, #email, #name, #birthdate, #phone').forEach(function(inputElement) {
        const fieldId = inputElement.id;
        const value = inputElement.value;
        let pattern;
        const errorBox = getErrorBox(fieldId);

        if (fieldId === 'password') {
            pattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}[\]|:;"'<>,.?])[A-Za-z0-9!@#$%^&*()_+{}[\]|:;"'<>,.?]{8,}$/;

            if (value.trim() === '') {
                showError(errorBox, '비밀번호: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
            }
        }

        if (fieldId === 'passwordcheck') {
            const passwordValue = document.querySelector('#password').value;

            if (value.trim() === '') {
                showError(errorBox, '비밀번호 확인: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (value !== passwordValue) {
                showError(errorBox, '비밀번호가 일치하지 않습니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
            }
        }

        if (fieldId === 'email') {
            pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (value.trim() === '') {
                showError(errorBox, '이메일: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '이메일: 이메일 주소가 정확한지 확인해 주세요.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
            }
        }
        
        if (fieldId === 'phone') {
            pattern = /^(01[0-9])-(\d{3,4})-(\d{4})$/;

            if (value.trim() === '') {
                showError(errorBox, '휴대전화번호: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '휴대전화번호: 휴대전화번호가 정확한지 확인해 주세요.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
            }
        }
    });

    if (allValid) {
        //alert("인증번호를 발송했습니다. 이메일을 확인해주세요");
        // 폼을 제출하려면 아래 코드를 활성화
        document.querySelector('form').submit();
    }
});

// 에러 메시지를 보여주는 함수
function showError(errorBox, message) {
    errorBox.textContent = message;
    errorBox.style.display = 'block';
}

// 에러 메시지를 숨기는 함수
function hideError(errorBox) {
    errorBox.style.display = 'none';
}

// 각 필드에 대한 errorBox를 가져오는 함수
function getErrorBox(fieldId) {
    if (fieldId === 'username') return document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(1)');
    if (fieldId === 'password') return document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(2)');
    if (fieldId === 'passwordcheck') return document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(3)');
    if (fieldId === 'email') return document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(4)');
    if (fieldId === 'name') return document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(1)');
    if (fieldId === 'birthdate') return document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(2)');
    if (fieldId === 'phone') return document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(3)');
}
