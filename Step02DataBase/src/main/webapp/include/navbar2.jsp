<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.navbar-brand i {
	    font-size: 1.75rem; /* 아이콘 크기 */
	    color: #7e57c2; /* indigo-300 색상 */
	    margin-left: 10px;
	  }
	  .navbar-brand i:hover {
	    color: #5a3ea1; /* 호버 시 색상 변경 */
	  }
	.navbar-brand {
		font-size: 1.75rem; /* 글꼴 크기 조정 */
		font-weight: bold; /* 글씨 두껍게 */
		color: #7e57c2; /* indigo-300 색상 */
	}
	
	.navbar-brand:hover {
		color: #5a3ea1; /* 호버 시 색상 변경 */
	}
	
</style>
<nav class="navbar bg-body-tertiary fixed-top">
	<div class="container-fluid">
		<!-- 메뉴 버튼 (왼쪽) -->
		<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" 
		        data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" 
		        aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- 홈 버튼 (오른쪽) -->
		<a class="navbar-brand" href="/">
			<i class="bi bi-house-door-fill"></i>
		</a>
		<!-- 중앙 회사 이름 -->
		<span class="navbar-brand mx-auto" style="text-align: center;">햣</span>
		<!-- 왼쪽: 프로필 -->
        <div class="d-flex align-items-center">
            <div class="dropdown">
                <a class="navbar-brand" href="#" id="profileDropdown"
                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-person-circle"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                    <li><a class="dropdown-item" href="/profile/info">개인 정보</a></li>
                    <li><a class="dropdown-item" href="/profile/extra1">기타1</a></li>
                    <li><a class="dropdown-item" href="/profile/extra2">기타2</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item text-danger" href="/logout">로그아웃</a></li>
                </ul>
            </div>
        </div>

		<!-- Offcanvas 메뉴 -->
		<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" 
		     aria-labelledby="offcanvasNavbarLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasNavbarLabel">메뉴</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
					<!-- CEO 메뉴 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="ceoDropdown" 
						   role="button" data-bs-toggle="dropdown" aria-expanded="false">
							CEO 입장
						</a>
						<ul class="dropdown-menu" aria-labelledby="ceoDropdown">
							<li><a class="dropdown-item" href="#">지점별 재무 현황</a></li>
							<li><a class="dropdown-item" href="#">새로고침 (DB에서 가져오기)</a></li>
							<li><a class="dropdown-item" href="#">손익여부</a></li>
							<li><a class="dropdown-item" href="#">가맹점 관리</a></li>
							<li><a class="dropdown-item" href="#">가맹점 추가, 삭제, 수정</a></li>
							<li><a class="dropdown-item" href="#">가맹점 현황 조회</a></li>
						</ul>
					</li>

					<!-- 점장 메뉴 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="managerDropdown" 
						   role="button" data-bs-toggle="dropdown" aria-expanded="false">
							점장 입장
						</a>
						<ul class="dropdown-menu" aria-labelledby="managerDropdown">
							<li><a class="dropdown-item" href="#">매출 관리</a></li>
							<li><a class="dropdown-item" href="#">새로고침 (DB에서 가져오기)</a></li>
							<li><a class="dropdown-item" href="#">직원 관리</a></li>
							<li><a class="dropdown-item" href="#">알바 시간표 (달력)</a></li>
							<li><a class="dropdown-item" href="#">근무 타임 추가, 수정, 삭제</a></li>
							<li><a class="dropdown-item" href="#">재고 및 발주 관리</a></li>
							<li><a class="dropdown-item" href="#">물품 추가</a></li>
						</ul>
					</li>

					<!-- 직원 메뉴 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="employeeDropdown" 
						   role="button" data-bs-toggle="dropdown" aria-expanded="false">
							직원 입장
						</a>
						<ul class="dropdown-menu" aria-labelledby="employeeDropdown">
							<li><a class="dropdown-item" href="#">입사</a></li>
							<li><a class="dropdown-item" href="#">근로계약서 확인</a></li>
							<li><a class="dropdown-item" href="#">입사 지원</a></li>
							<li><a class="dropdown-item" href="#">근태</a></li>
							<li><a class="dropdown-item" href="#">출퇴근</a></li>
							<li><a class="dropdown-item" href="#">근무 시간표 (달력)</a></li>
							<li><a class="dropdown-item" href="#">근무 변경 요청</a></li>
							<li><a class="dropdown-item" href="#">급여</a></li>
							<li><a class="dropdown-item" href="#">시급 계산기 (예상 급여)</a></li>
							<li><a class="dropdown-item" href="#">퇴사 요청</a></li>
						</ul>
					</li>

					<!-- 기타 메뉴 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="othersDropdown" 
						   role="button" data-bs-toggle="dropdown" aria-expanded="false">
							기타
						</a>
						<ul class="dropdown-menu" aria-labelledby="othersDropdown">
							<li><a class="dropdown-item" href="#">익명게시판</a></li>
							<li><a class="dropdown-item" href="#">Q&A 게시판</a></li>
							<li><a class="dropdown-item" href="#">공지사항</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>
