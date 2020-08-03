<?php
function sendTextMsg($mobile , $msg)
{
    $key ="337157AzC1XgIEN5f1fdffcP1";

    $curl = curl_init();
    
    curl_setopt_array($curl, array(
    CURLOPT_URL => "https://api.msg91.com/api/v5/otp?authkey=337157AtkvsOxC5f23b2d7P1&template_id=5f23b133d6fc05291350c330&extra_param={"Param1":"Value1", "Param2":"Value2", "Param3": "Value3"}&mobile=Mobile Number with Country Code&invisible=1&otp=OTP to send and verify. If not sent, OTP will be generated.&userip=IPV4 User IP&email=Email ID
",
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_ENCODING => "",
    CURLOPT_MAXREDIRS => 10,
    CURLOPT_TIMEOUT => 30,
    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
    CURLOPT_CUSTOMREQUEST => "GET",
    CURLOPT_SSL_VERIFYHOST => 0,
    CURLOPT_SSL_VERIFYPEER => 0,
    ));

    $response = curl_exec($curl);
    $err = curl_error($curl);

    curl_close($curl);

    if ($err) {
        //echo "cURL Error #:" . $err;
        return false;
      } else {
        #echo $response;
        return true;
      }
      return false;
}
?>
