<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang = "en">
<head>
    <title>hello,world</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>

</head>
<body >
<?php
$color = $_GET['color'];
if($color == 'red') {
    printf("<div id='val1'>ROSE</div>");
    printf("<div id='val2'>Apple</div>");

} else if($color == 'blue') {
    printf("<div id='val1'>Berry</div>");
    printf("<div id='val2'>Sky</div>");
}
?>
</body>
</html>