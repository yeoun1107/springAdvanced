/**
 *	File Name			: signup.js
 *	File Description	: 회원가입 페이지 스크립트
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 * 	2026-03-13			yeoun1107				아이디 중복 체크 로직 고도화 및 코드 정비
 * 	2026-03-16			yeoun1107				username -> userId 컬럼 변경 대응
 */

$(function() {
	const signup = {
		
		// 초기화
		init: function() {
			this.bindEvents();
		},

		// 이벤트 바인딩
		bindEvents: function() {
			const _this = this;

			// 1. 아이디 중복 체크 (blur 이벤트 시)
			$("#userId").on("blur", function() {
				_this.validateUserId();
			});

			// 2. 비밀번호 확인 실시간 체크 (키 입력 시)
			$("#confirmPassword, #password").on("keyup", function() {
				_this.validatePasswordMatch();
			});

			// 3. 등록 버튼 클릭
			$("#btnRegister").on("click", function() {
				_this.executeSignup();
			});

			// 4. 엔터키 입력 시 가입 실행
			$("#signupForm input").on("keypress", function(e) {
				if (e.which === 13) {
					_this.executeSignup();
				}
			});
		},

		/**
		 * @Description 아이디 중복 유효성 체크 (API 연동)
		 * @method validateUserId
		 */
		validateUserId: function() {
			const userId = $("#userId").val();
			const $feedback = $("#userIdFeedback");
			const $input = $("#userId");

			// 입력값이 없는 경우 초기화
			if (commUtil.isEmpty(userId)) {
				$feedback.removeClass("valid invalid").text("").hide();
				$input.removeClass("is-valid-field is-invalid-field");
				return;
			}

			// API 호출 (아이디 중복 여부 확인)
			commUtil.ajax({
				url: "/api/login/exist-user-id/" + encodeURIComponent(userId),
				method: "GET",
				success: function(res) {
					if (res.status === "SUCCESS") {
						const isExist = res.data;
						if (isExist) {
							// 중복된 경우
							$feedback.removeClass("valid").addClass("invalid").text(res.message).show();
							$input.removeClass("is-valid-field").addClass("is-invalid-field");
						} else {
							// 사용 가능한 경우
							$feedback.removeClass("invalid").addClass("valid").text(res.message).show();
							$input.removeClass("is-invalid-field").addClass("is-valid-field");
						}
					}
				},
				error: function() {
					console.error("아이디 중복 체크 중 오류 발생");
				}
			});
		},

		/**
		 * @Description 비밀번호 일치 여부 실시간 유효성 체크
		 * @method validatePasswordMatch
		 */
		validatePasswordMatch: function() {
			const password = $("#password").val();
			const confirmPassword = $("#confirmPassword").val();
			const $feedback = $("#passwordFeedback");
			const $input = $("#confirmPassword");

			// 확인란이 비어있는 경우 초기화
			if (commUtil.isEmpty(confirmPassword)) {
				$feedback.removeClass("valid invalid").text("").hide();
				$input.removeClass("is-valid-field is-invalid-field");
				return;
			}

			if (password === confirmPassword) {
				// 비밀번호 일치
				$feedback.removeClass("invalid").addClass("valid").text("비밀번호가 일치합니다.").show();
				$input.removeClass("is-invalid-field").addClass("is-valid-field");
			} else {
				// 비밀번호 불일치
				$feedback.removeClass("valid").addClass("invalid").text("비밀번호가 일치하지 않습니다.").show();
				$input.removeClass("is-invalid-field").addClass("is-invalid-field");
			}
		},

		/**
		 * @Description 회원가입 실행 (최종 유효성 검사 후 가입 신청)
		 * @method executeSignup
		 */
		executeSignup: function() {
			const userId = $("#userId").val();
			const password = $("#password").val();
			const confirmPassword = $("#confirmPassword").val();

			// 1. 필수 입력값 체크
			if (commUtil.isEmpty(userId)) {
				commUtil.alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return;
			}

			// 2. 아이디 중복 여부 최종 확인
			if ($("#userId").hasClass("is-invalid-field")) {
				commUtil.alert("사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
				$("#userId").focus();
				return;
			}

			if (commUtil.isEmpty(password)) {
				commUtil.alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			}

			// 3. 비밀번호 일치 여부 최종 확인
			if (password !== confirmPassword) {
				commUtil.alert("비밀번호가 일치하지 않습니다.");
				$("#confirmPassword").focus();
				return;
			}

			// 4. API 호출 (회원가입 요청)
			const params = commUtil.serializeObject("signupForm");

			commUtil.ajax({
				url: "/api/login/createSignup",
				data: params,
				success: function(res) {
					if (res.status === "success") {
						commUtil.alert(res.message);
						location.href = "/login"; // 로그인 화면으로 이동
					} else {
						// 서버에서 반환한 구체적인 에러 메시지 표시
						commUtil.alert("회원가입 실패 (" + res.code + "): " + res.message);
					}
				},
				error: function(xhr, status, error) {
					commUtil.alert("서버 통신 중 기술적인 오류가 발생했습니다. 관리자에게 문의하세요.");
				}
			});
		}
	};

	// 스크립트 실행 시작
	signup.init();
});
