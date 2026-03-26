/**
 *	File Name			: login.js
 *	File Description	: 로그인 페이지 스크립트
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-13			yeoun1107				최초작성
 */

$(function() {
	const login = {
		
		// 초기화
		init: function() {
			this.bindEvents();
		},

		// 이벤트 바인딩
		bindEvents: function() {
			const _this = this;

			// 로그인 버튼 클릭
			$("#btnLogin").on("click", function() {
				_this.executeLogin();
			});

			// 엔터키 입력 시 로그인 실행
			$("#password, #userId").on("keypress", function(e) {
				if (e.which === 13) {
					_this.executeLogin();
				}
			});
		},

		// 로그인 실행
		executeLogin: function() {
			const userId = $("#userId").val();
			const password = $("#password").val();

			// 1. 유효성 체크
			if (commUtil.isEmpty(userId)) {
				commUtil.alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return;
			}

			if (commUtil.isEmpty(password)) {
				commUtil.alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			}

			// 2. API 호출
			const params = commUtil.serializeObject("loginForm");

			commUtil.ajax({
				url: "/api/login/retrieve-login",
				data: params,
				success: function(res) {
					if (res.status === "SUCCESS") {
						commUtil.alert(res.message);
						location.href = "/index"; // 로그인 성공 시 인덱스 페이지로 이동
					} else {
						// 로그인 실패 (아이디/비번 불일치 등)
						commUtil.alert(res.message);
					}
				},
				error: function(xhr, status, error) {
					// commUtil.alert("서버 통신 중 오류가 발생했습니다.");
					const errorResponse = xhr.responseJSON;
					const msg = (errorResponse && errorResponse.message) ? errorResponse.message : "서버 통신 중 시스템 오류가 발생했습니다.";
					commUtil.alert(msg);
				}
			});
		}
	};

	login.init();
});
