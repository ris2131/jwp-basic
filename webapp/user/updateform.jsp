<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <jsp:include page="/include/header.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/include/navigation.jsp"></jsp:include>

    <div class="container" id="main">
       <div class="col-md-6 col-md-offset-3">
          <div class="panel panel-default content-main">
              <form name="question" method="post" action="/user/update?userId=${user.userId}">
                  <div class="form-group">
                      <label for="updateUserId">사용자 아이디</label>
                      <input class="form-control" id="updateUserId" name="updateUserId" placeholder= "기존 User ID: ${user.userId}">
                  </div>
                  <div class="form-group">
                      <label for="updatePassword">비밀번호</label>
                      <input type="password" class="form-control" id="updatePassword" name="updatePassword" placeholder="보안 Password">
                  </div>
                  <div class="form-group">
                      <label for="updateName">이름</label>
                      <input class="form-control" id="updateName" name="updateName" placeholder="기존 Name: ${user.name}">
                  </div>
                  <div class="form-group">
                      <label for="updateEmail">이메일</label>
                      <input type="email" class="form-control" id="updateEmail" name="updateEmail" placeholder="기존 Email: ${user.email}">
                  </div>
                  <button type="submit" class="btn btn-success clearfix pull-right">수정</button>
                  <div class="clearfix" />
              </form>
            </div>
        </div>
    </div>

    <jsp:include page="/include/footer.jsp"></jsp:include>
	</body>
</html>