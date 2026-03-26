/**
 *	File Name			: commUtil.js
 *	File Description	: 공통 유틸 함수 모음 (Date, Number, String, Ajax)
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성 및 고도화
 */

const commUtil = {
	
	/* ===================================================================== DateUtils ==================================================================================== */
	/**
	 * 날짜 포맷 변환 (YYYY-MM-DD)
	 * @param {Date|string} date 
	 * @returns {string}
	 */
	formatDate: function(date) {
		const d = new Date(date);
		let month = '' + (d.getMonth() + 1);
		let day = '' + d.getDate();
		const year = d.getFullYear();

		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;

		return [year, month, day].join('-');
	},

	/**
	 * 유효한 날짜 여부 확인
	 * @param {string} dateString 
	 * @returns {boolean}
	 */
	isDate: function(dateString) {
		return !isNaN(Date.parse(dateString));
	},

	/* ===================================================================== NumberUtils ==================================================================================== */
	/**
	 * 천단위 콤마 추가
	 * @param {number|string} num 
	 * @returns {string}
	 */
	formatNumber: function(num) {
		return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	},

	/**
	 * 숫자 여부 확인
	 * @param {any} val 
	 * @returns {boolean}
	 */
	isNumber: function(val) {
		return !isNaN(parseFloat(val)) && isFinite(val);
	},

	/* ===================================================================== StringUtils ==================================================================================== */
	/**
	 * 빈 문자열 여부 확인
	 * @param {string} str 
	 * @returns {boolean}
	 */
	isEmpty: function(str) {
		return (!str || str.length === 0 || str.trim() === "");
	},

	/**
	 * 이메일 형식 유효성 검사
	 * @param {string} email 
	 * @returns {boolean}
	 */
	isEmail: function(email) {
		const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return regExp.test(email);
	},

	/* ===================================================================== AjaxUtils ==================================================================================== */
	/**
	 * 공통 AJAX 호출 함수
	 * @param {object} options { url, method, data, success, error }
	 */
	ajax: function(options) {
		const isGet = (options.method && options.method.toUpperCase() === "GET") || (options.type && options.type.toUpperCase() === "GET");

		const defaultOptions = {
			method: "POST",
			contentType: isGet ? undefined : "application/json",
			dataType: "json",
			async: true,
			beforeSend: function() {
				// 필요 시 로딩 바 표시
			},
			complete: function() {
				// 필요 시 로딩 바 제거
			}
		};

		const userErrorCallback = options.error;
		const finalOptions = $.extend(true, defaultOptions, options);
		delete finalOptions.error;	// jQuery 내부 자동 실행으로 인해 에러 콜백에 사용자가 직접 설정한 메서드가 있을 경우 두 번 실행 방지를 위해

		// JSON 데이터 처리 (POST/PUT 등에서 객체를 보낼 때)
		if (finalOptions.contentType === "application/json" && typeof finalOptions.data === "object") {
			finalOptions.data = JSON.stringify(finalOptions.data);
		}

		$.ajax(finalOptions).fail(function(xhr, status, error) {
			console.error("[AJAX ERROR] > ", error);
			// if (options.error) {
			// 	options.error(xhr, status, error);
			// } else {
			// 	commUtil.alert("데이터 처리 중 오류가 발생했습니다.");
			// }
			if (typeof userErrorCallback === "function") {
				// 사용자가 정의한 에러 콜백이 있으면 그것만 실행 (한 번)
				userErrorCallback(xhr, status, error);
			} else {
				// 없을 때만 공통 alert 출력
				commUtil.alert("데이터 처리 중 오류가 발생했습니다.");
			}
		});
	},

	/**
	 * Form 데이터를 JSON Object로 변환
	 * @param {string} formId 
	 * @returns {object}
	 */
	serializeObject: function(formId) {
		const obj = {};
		const arr = $("#" + formId).serializeArray();
		$.each(arr, function() {
			if (obj[this.name] !== undefined) {
				if (!obj[this.name].push) {
					obj[this.name] = [obj[this.name]];
				}
				obj[this.name].push(this.value || '');
			} else {
				obj[this.name] = this.value || '';
			}
		});
		return obj;
	},

	/**
	 * JSON 데이터를 Form 필드에 자동 바인딩
	 * @param {string} formId 
	 * @param {object} data 
	 */
	bindForm: function(formId, data) {
		if (!data) return;
		const $form = $("#" + formId);
		$.each(data, function(key, value) {
			const $el = $form.find(`[name="${key}"]`);
			if ($el.length > 0) {
				if ($el.is(":checkbox") || $el.is(":radio")) {
					$el.filter(`[value="${value}"]`).prop("checked", true);
				} else {
					$el.val(value);
				}
			}
		});
	},

	/**
	 * 알림창 표시
	 * @param {string} message 
	 */
	alert: function(message) {
		alert(message);
	},

	/**
	 * 로그 기록
	 * @param {string} message 
	 */
	log: function(message) {
		console.log(`[LOG] ${new Date().toLocaleString()} : ${message}`);
	}
};

/* 공통함수 window load 시 세팅 // */
$(function(){
	// 초기화 로직
});
/* // 공통함수 window load 시 세팅 */
