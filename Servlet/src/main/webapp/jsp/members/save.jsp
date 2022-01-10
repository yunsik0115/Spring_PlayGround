<%@ page import="Hello.Servlet.domain.member.Member" %>
<%@ page import="Hello.Servlet.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: noogl
  Date: 10/01/2022
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>

<%
    // request response는 servlet으로 사용하고, jsp도 동일해서 문법상 import 필요 X
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    // request getparameter의 반환은 항상 문자타입.

    Member member = new Member(username, age);
    memberRepository.save(member); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
<a href="/index.html">메인</a>
</body>
</html>
