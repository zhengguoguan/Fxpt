<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>公司简介</title>
<link href="${ctx }/about/style/master.css" rel="stylesheet" type="text/css">
</head>

<body>
<header>
<div class="logo"></div>
</header>
<div class="container">
 <div class="aboutbj">
  <div class="title">
    <h1>公司介绍</h1></div>
    ${entity.content}

 </div>
</div>
</body>
</html>
