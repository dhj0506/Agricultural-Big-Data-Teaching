<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<c:set var="pageTitle" value="Duke足球联赛: 添加新联赛"/>

<html>
<head>
    <title>${pageTitle}</title>
    <script crossorigin="anonymous" integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh"
            src="https://lib.baomitu.com/jquery/3.4.1/jquery.min.js"></script>
    <script crossorigin="anonymous" integrity="sha384-6UVI3atWyL/qZbDIJb7HW8PyHhFNMiX5rYNY2gAYcaYJjYk5cNIQShSQPBleGMYu"
            src="https://lib.baomitu.com/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script crossorigin="anonymous" integrity="sha384-5NVTHpVUv65u2Krs86ToSDjvacWdu9Gnm24/9cAj6BsidEfZaBM0+RRL0qEhvqiS"
            src="https://lib.baomitu.com/jquery-validate/1.19.1/localization/messages_zh.min.js"></script>
</head>
<body>

<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
        <td><h3>${pageTitle}</h3></td>
    </tr>
</table>

<p>本页面用于创建新联赛</p>

<form action='/admin/addleague' method='POST' id="one">
    年 份：<input type='text' name='year' oninput="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/> <br/><br/>
    季 节：<select name='season'>
    <option value='Spring'>Spring</option>
    <option value='Summer'>Summer</option>
    <option value='Fall'>Fall</option>
    <option value='Winter'>Winter</option>
</select> <br/><br/>
    标 题：<input type='text' name='title'/> <br/><br/>
    <input type='submit' value='添加联赛'/>
</form>
<script type="text/javascript">
    $(document).ready(function () {
        $("#one").validate({
            rules: {
                year: {
                    required: true,
                    range: [1900, 2100]
                },
                season: {
                    required: true
                },
                title: {
                    required: true
                }
            },
            messages: {
                year: {
                    required: "年份必须填写",
                    range: "年份必须介于1900与2100之间"
                },
                season: {
                    required: "季节为必填"
                },
                title: {
                    required: "标题为必填"
                }
            }
        });
    });
</script>
</body>
</html>

