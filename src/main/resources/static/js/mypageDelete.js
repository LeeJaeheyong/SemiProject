function deleteUser() {
	// 1. userId, userPassword value 가져오기
	// userId : islandtim
	// userPassword : qwer1234
	const userId = document.getElementById("username").value;
	const userPassword = document.getElementById("password").value;
	
	console.log(userId);
	console.log(userPassword);
		
	$.ajax({
		type: "POST",
		url: "/game/mypageDelete",
		data: {
			// key : value
			// <input type="text" name="userPassword" value=userPassword>
			// 2. userid, userpassword 작성하기
			//   "userId" : userId
			// requestParam : 위의값
			"userId" : userId,
			"userPassword" : userPassword
		},
		success: function(res) {
			if(res == 1) {
				alert("탈퇴가 완료되었습니다.")
				console.log("성공~!!");
			} else {
				alert("비밀번호가 일치하지 않습니다.")
				console.log("실패!!")
			}
			console.log(res);
		},
		error: function(err) {
			
		}
	})
}