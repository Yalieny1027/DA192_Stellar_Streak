 
<html>
<head>
    <title>Msg91 API for PHP</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">

</head>
<body>

<div class="container">


    <div class="col-lg-offset-4 col-lg-4" id="panel">
        <h2>Msg91 API for PHP</h2><br>

        <form action="<?php echo $_SELF ?>" method="post" >
 

            <div class="group">
                <input type="text" required name="mobile">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Mobile No</label>
            </div>

            <div class="group">
              <textarea class="form-control" rows="5" name="message"></textarea>
                <span class="highlight"></span>
                <span class="bar"></span>
                 
            </div>
            <div class="group">
                <center> <button type="submit" name="submit" class="btn-lg btn-warning">Send <span class="glyphicon glyphicon-send"></span></button></center>
            </div>
        </form>

    </div>
</div>

</body>
</html>
<?php
if(isset($_POST['submit']))

{
$mobileNo=$_POST['mobile'];
$message = urlencode($_POST['message']);
$authKey = "337157AtkvsOxC5f23b2d7P1";
$senderId = "Default";
$route = "4";
$postData = array(
    'authkey' => $authKey,
    'mobiles' => $mobileNo,
    'message' => $message,
    'sender' => $senderId,
    'route' => $route,
    'country'=>'0'
);
$url="https://control.msg91.com/api/sendhttp.php";
$ch = curl_init();
    curl_setopt_array($ch, array(
    CURLOPT_URL => $url,
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_POST => true,
    CURLOPT_POSTFIELDS => $postData
));
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
$output = curl_exec($ch);
 if(curl_errno($ch))
{
    echo 'error:' . curl_error($ch);
}
curl_close($ch);
echo '<script>alert("Message sent Successfully")</script>';
}
?>





