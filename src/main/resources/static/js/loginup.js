// 회원가입 버튼 누르지 않고 입력만 했을 경우
document.querySelectorAll('#username, #password, #passwordcheck, #email, #name, #birthdate, #phone').forEach(function(inputElement) {
    inputElement.addEventListener('input', function() {  
        const fieldId = this.id;
        const value = this.value;
        let pattern;

        if (fieldId === 'username') {
            const errorBox = document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(1)');
            pattern = /^[a-z0-9_-]{5,20}$/;

            if (value.trim() === '') {
                errorBox.textContent = '아이디: 필수 정보입니다.';
                errorBox.style.display = 'block';
				errorBox.style.color = 'red';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '아이디: 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
				errorBox.style.color = 'red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = '';
				$.ajax({
					type: "POST",
					url: "/game/userIdCheck",
					data: {
						// key  :  value
						"username": value
					},
					success: function(res) {
						console.log("요청성공", res);
						console.log(res);
						if(res == "ok") {
							console.log(errorBox);
							errorBox.textContent = '사용가능한 아이디입니다.';
							errorBox.style.display = 'block';
							errorBox.style.color = 'green';
						} else {
							console.log("이미 사용중인 아이디");
							errorBox.textContent = '중복된 아이디입니다.';
							errorBox.style.display = 'block';
							errorBox.style.color = 'red';
							
						}
					},
					error: function(err) {
						console.log("에러발생");
					}
				});
            }
        }

        else if (fieldId === 'password') {
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

        else if (fieldId === 'passwordcheck') {
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

        else if (fieldId === 'email') {
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

        else if (fieldId === 'name') {
            const errorBox = document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(1)');
            pattern = /^[가-힣a-zA-Z]+$/;

            if (value.trim() === '') {
                errorBox.textContent = '이름: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '이름: 한글, 영문 대/소문자를 사용해 주세요. (특수기호, 공백 사용 불가)';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = ''
            }
        }

        else if (fieldId === 'birthdate') {
            const errorBox = document.querySelectorAll('.error_box')[1].querySelector('.error_text:nth-child(2)');
            pattern = /^\d{8}$/;

            if (value.trim() === '') {
                errorBox.textContent = '생년원일: 필수 정보입니다.';
                errorBox.style.display = 'block';
            } else if (!pattern.test(value)) {
                errorBox.textContent = '생년월일: 생년월일은 8자리 숫자로 입력해 주세요.';
                errorBox.style.display = 'block';
                inputElement.style.border = '1px solid red';
            } else {
                errorBox.style.display = 'none';
                inputElement.style.border = ''
            }
        }

        else if (fieldId === 'phone') {
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
}); //회원가입 버튼 누르지 않고 입력만 했을 경우


// 회원가입 버튼을 눌렀을 때
document.getElementById('submitButton').addEventListener('click', function(event) {
    event.preventDefault();  // 버튼 클릭 시 폼 제출을 막습니다.

    let allValid = true;  // 모든 입력이 유효한지 여부를 추적

    // 각 필드에 대해 유효성 검사
    document.querySelectorAll('#username, #password, #passwordcheck, #email, #name, #birthdate, #phone').forEach(function(inputElement) {
        const fieldId = inputElement.id;
        const value = inputElement.value;
        let pattern;
        const errorBox = getErrorBox(fieldId);

        if (fieldId === 'username') {
            pattern = /^[a-z0-9_-]{5,20}$/;

            if (value.trim() === '') {
                showError(errorBox, '아이디: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
				errorBox.style.color = 'red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '아이디: 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
                inputElement.style.border = '1px solid red';
				errorBox.style.color = 'red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
				$.ajax({
						type: "POST",
						url: "/game/userIdCheck",
						data: {
							// key  :  value
							"username": value
						},
						success: function(res) {
							console.log("요청성공", res);
							console.log(res);
							if(res == "ok") {
								console.log(errorBox);
								errorBox.textContent = '사용가능한 아이디입니다.';
								errorBox.style.display = 'block';
								errorBox.style.color = 'green';
							} else {
								console.log("이미 사용중인 아이디");
								errorBox.textContent = '중복된 아이디입니다.';
								errorBox.style.display = 'block';
								errorBox.style.color = 'red';
											
							}
						},
						error: function(err) {
							console.log("에러발생");
						}
					});
            }
        }

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

        if (fieldId === 'name') {
            pattern = /^[가-힣a-zA-Z]+$/;

            if (value.trim() === '') {
                showError(errorBox, '이름: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '이름: 한글, 영문 대/소문자를 사용해 주세요. (특수기호, 공백 사용 불가)');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else {
                hideError(errorBox);
                inputElement.style.border = '';
            }
        }

        if (fieldId === 'birthdate') {
            pattern = /^\d{8}$/;

            if (value.trim() === '') {
                showError(errorBox, '생년월일: 필수 정보입니다.');
                inputElement.style.border = '1px solid red';
                allValid = false;
            } else if (!pattern.test(value)) {
                showError(errorBox, '생년월일: 생년월일은 8자리 숫자로 입력해 주세요.');
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
	
	const allValid2 = emailCheckFunc('e',true);
    if (allValid && allValid2) {
        // 폼을 제출하려면 아래 코드를 활성화
		console.log("회원가입성공!!")
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

// 이메일 인증 버튼 
function emailcheckButton() {
	console.log("클릭");
	
	const email = document.getElementById("email").value;
	const errorBox = document.querySelectorAll('.error_box')[0].querySelector('.error_text:nth-child(4)');
	const emailcheck = document.getElementById("emailcheck");
	
	if(!email.trim()) {
		showError(errorBox, '이메일: 필수 정보입니다.');
		return;
	}	
		
	const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	    if (!emailPattern.test(email)) {
	        showError(errorBox, '이메일: 이메일 주소가 정확한지 확인해 주세요.');
	        return; // 이메일 형식이 잘못되면 진행하지 않음
	    }
		
		alert("인증번호를 발송했습니다. 이메일을 확인해주세요.");
		emailcheck.removeAttribute('readonly');
		
		$.ajax({
			type: "POST",
			url: "/game/emailConfirm",
			data: {
				"email" : email
			},
			success: function(res) {
				if(res == "ok") {
					console.log('인증번호 발송 성공 성공 성공 성공 성공 성공');
				} else {
					console.log('인증번호 발송 실패 실패 실패 실패 실패 실패');
				}
			},
			error: function(err) {
				console.log('서버 연결 실패');
			}
		})
}

// 이메일 인증번호 입력
	const emailCheckBox = document.getElementById('emailcheck');
	const inputEmail = document.getElementById('email');
	const ecErrorBox = document.getElementById('emailcheck_text');
	let ajaxStart = false; 
	
	emailCheckBox.addEventListener('input',  emailCheckFunc)
	
	console.log(emailCheckFunc()); // undefined
	console.log("--------");
	console.log(emailCheckFunc);
	
	function emailCheckFunc(e, isRegisterBtn) {
		// 회원가입 버튼 누르면 isRegisterBtn : true
		// 안누르고 그냥 입력하면 isRegisterBtn : undefined
		//                isRegisterBtn의 타입이 undefined냐? 맞으면 false, 틀리면 true
		isRegisterBtn = typeof isRegisterBtn === 'undefined' ? false : true;
		let all = true;
		if(emailCheckBox.value.length > 6) {
			emailCheckBox.value = emailCheckBox.value.slice(0, 6);
		}
		const number = emailCheckBox.value;
		const email = inputEmail.value;
		
		if(emailCheckBox.value.length === 6) {
			ajaxStart = true;

			$.ajax({
				type: "POST",
				url: "/game/mailAuthCheck",
				async: false,
				data: {
					"email" : email,
					"authNum" : number
				},
				success: function(res) {
					if(res == "ok") {
						//if(number.length == 6) {
							console.log('인증번호 oooooooo');
							ecErrorBox.style.display = 'block';
							ecErrorBox.textContent = '인증번호가 일치합니다.';
							ecErrorBox.style.color = 'green';
							emailCheckBox.style.border = '';
							console.log('하하하' + ajaxStart)
						//}
					} 
					all = true;
					console.log('인증번호 xxxxxxxx');
				},
				error: function(err) {
					if(ajaxStart) {
						console.log('서버 연결 실패패패패패패패');
						ecErrorBox.style.display = 'block';
						ecErrorBox.textContent = '인증번호가 틀립니다.';
						ecErrorBox.style.color = 'red';
						emailCheckBox.style.border = '1px solid red';
						if(number == '') {
							ecErrorBox.textContent = '인증번호를 입력해주세요.';
							emailCheckBox.style.border = '1px solid red';
						}
						all =  false;
					}	
				} 
			})
		} else if(ajaxStart || isRegisterBtn) {
			console.log('서버 연결 실패패패패패패패');
			ecErrorBox.style.display = 'block';
			ecErrorBox.textContent = '인증번호가 틀립니다.';
			ecErrorBox.style.color = 'red';
			emailCheckBox.style.border = '1px solid red';
			if(number == '') {
				ecErrorBox.textContent = '인증번호를 입력해주세요.';
				emailCheckBox.style.border = '1px solid red';
			} 
			
			all =  false;
		 }
		 
		 return all;
	}
		
	/*- 비동기
	1. emailCheckFunc() 함수 호출 - 스택1 생성
	2. let all = true; - 스택1에 저장
	3. ajax를 만남 (비동기통신이기 때문에 web apis라는 곳으로 빠짐, 서버로 요청) - web apis로 빠짐
	4. 밑으로 쭉 내려와서 return all; - 스택1에서 반환, 함수 종료되면서 스택 반환
	 - ajax가 아직 완료되지 않은 상태이기 떄문에 true
	5. ajax가 요청이 끝남 - 스택2라는 공간으로 다시 올려짐
	6. all = false - 스택2

	- 동기
	1. emailCheckFunc() 함수 호출 - 스택1 생성
	2. let all = true; - 스택 1생성
	3. ajax를 만남 (동기 방식)
	4. 응답을 기다리다가 error로 빠지면
	  - all = false; - 스택1 저장
	5. ajax가 완료되면 그제서야 아래에 있는 코드 실행
	6. return all을 만남 - 스택1	
	  - return false; 	*/	
		
			
	
	
	
