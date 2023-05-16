<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .upload-box {
            width: 150px;
            height: 150px;
            border: 3px dashed orange;
            display: flex;
            justify-content: center;
            align-items: center;
            color: red;
            font-weight: 700;
            cursor: pointer;
        }
        #img-input {
            display: none;
        }
    </style>
</head>
<body>
    <h1>파일 업로드 예제</h1>

    <div class="upload-box">파일 첨부</div>

    <form action="/upload-form" method="post" enctype="multipart/form-data"> <!-- enctype="multipart/form-data" 어떤 파일도 업로드 가능 -->
        <input id="img-input" type="file" name="thumbnail" accept="image/*">
        <button type="submit">전송</button>
    </form>

    <script>
        const $box = document.querySelector('.upload-box');
        const $input = document.getElementById('img-input');

        $box.onclick = e => {
            $input.click();
        }
    </script>
</body>
</html>