<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body  style="color:#c2d8f1;
  background-color: #191970;
  height: 70%;
  background-position: left;
  background-repeat: no-repeat;
  background-size: auto;">
<table width="70%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="center"><br> <br>
            <table width="450" border="0" cellspacing="0" cellpadding="0" style="float:left">
                <tr>
                    <td align="center" valign="top"
                        style=" font-family: Arial, Helvetica, sans-serif; font-size: 25px; padding: 0px 15px 10px 15px;">
                        <div style="font-size: 48px; ">
                            <b>Greetings</b><br/><br/>
                        </div>
                        <div style="text-align:center">
                            Dear <b></b>,<br/><br/>
                            <#if body??>
                                ${body}
                            <#else>
                                <!-- Handle case where body is null or missing -->
                                Welcome to Bizmatch
                            </#if>
                        </div>
                    </td>
                </tr>
            </table> <br/> <br/></td>
    </tr>
</table>
</body>
</html>
