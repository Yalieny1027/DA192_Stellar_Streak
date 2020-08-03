# Msg91 
Msg91 API for PHP

#Send SMS
```php
$authKey = "YourAuthKey";
$mobileNumbers = ["99999999","888888888","777777777"];
$senderId = "XXXXX";
$message = urlencode("Hello !! ");
$route="x";
$postData = array('authkey' => $authKey,'mobiles' => $mobileNumbers,'message' => $message,'sender' => $senderId,'route' =>$route);
$url="http://api.msg91.com/api/sendhttp.php";
$ch = curl_init();
curl_setopt_array($ch, array(CURLOPT_URL => $url,CURLOPT_RETURNTRANSFER => true,CURLOPT_POST => true,CURLOPT_POSTFIELDS => $postData));
$output = curl_exec($ch);
if(curl_errno($ch)){
echo 'error:' . curl_error($ch);
}
curl_close($ch);
echo $output;
```
#Route
* 1 - Promotional Route
* 4 - Transactional Route
