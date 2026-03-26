/**
 *	File Name			: restClientEx.js
 *	File Description	: RestClient 예제 관련 스크립트
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-19			yeoun1107			최초작성
 */

$(function() {
	const restClientEx = {

		/**
		 * @Description		: 초기화 메서드
		 * @method			: init
		 */
		init: function() {
			this.getKrw();
		},

		/**
		 * @Description		: KRW 환율 정보 조회 및 바인딩
		 * @method			: getKrw
		 */
		getKrw: function() {
			const _this = this;
			
			// 1. commUtil.ajax 를 활용한 API 호출
			commUtil.ajax({
				url: "/api/practice/restClientEx",
				method: "GET",
				success: function(res) {
					// 2. 응답 데이터 확인 및 화면 바인딩
					if (res.status === "SUCCESS") {
						// 숫자를 천 단위 콤마 형식으로 포맷팅하여 바인딩
						const formattedKrw = Number(res.krw).toLocaleString(undefined, {
							minimumFractionDigits: 2,
							maximumFractionDigits: 2
						});
						$("#krwValue").text(formattedKrw);
					} else {
						commUtil.alert(res.message || "환율 데이터를 불러오지 못했습니다.");
					}
				},
				error: function() {
					commUtil.alert("환율 정보 조회 중 오류가 발생했습니다.");
				}
			});
		},

	};

	restClientEx.init();
});
