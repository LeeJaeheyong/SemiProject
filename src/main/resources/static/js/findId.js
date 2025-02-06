function findId(event) {
	event.preventDefault();
	
	const name = document.getElementById('name').value;
	const email = document.getElementById('email').value;
	
	if(name == "") {
		alert('이름을 입력해주세요');
	} else if(email == "") {
		alert('이메일을 입력해주세요');
	} else {
		document.getElementById('submitButton').form.submit();
	}	
}


