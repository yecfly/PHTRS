<%-- 
    Document   : index
    Created on : 2013-7-1, 12:25:36
    Author     : Yecfly
--%>
<center>
    <form name = "login" action = "/phtrs/loginhandler" method = "post">
        <table>                      
           <tr>
                         <c:if test="${param.login == 0}">
                             <span style="color: red">wrong username or password! </span>
                         </c:if>
                         </tr>
            <tr>
            <p>User:<input type="text" name="username" size="10"/></p> 
            </tr>
            <tr><p>Password:<input type="password" name="password" size="10"/></p>
            </tr>
            <tr>
                <td><input id="r1" type="radio" name="type" value="Citizen" checked="checked" tabindex="4"/><label for="r1">Citizen</label></td>
                <td><input id="r2" type="radio" name="type" value="Admin" tabindex="4"/><label for="r2">Admin</label></td>
                <td>
                    <input type="submit" name="login" value = "login" />
                </td>
            </tr>
        </table>
    </form>

</center>


